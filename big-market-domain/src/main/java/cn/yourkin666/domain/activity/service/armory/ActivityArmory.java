package cn.yourkin666.domain.activity.service.armory;

import cn.yourkin666.domain.activity.model.entity.ActivitySkuEntity;
import cn.yourkin666.domain.activity.model.valobj.ActivitySkuStockKeyVO;
import cn.yourkin666.domain.activity.repository.IActivityRepository;
import cn.yourkin666.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.yourkin666.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 活动sku预热
 * @create 2024-03-30 09:12
 */
@Slf4j
@Service
public class ActivityArmory implements IActivityArmory, IActivityDispatch {

    @Resource
    private IActivityRepository activityRepository;

    @Override
    public boolean assembleActivitySku(Long sku) {
        // 1. 查询SKU信息
        ActivitySkuEntity skuEntity = activityRepository.queryActivitySku(sku);
        // 2. 缓存库存
        cacheActivitySkuStockCount(sku, skuEntity.getStockCount());
        // 3. 预热活动信息
        activityRepository.queryRaffleActivityByActivityId(skuEntity.getActivityId());
        // 4. 预热活动次数配置
        activityRepository.queryRaffleActivityCountByActivityCountId(skuEntity.getActivityCountId());

        return true;
    }
    private void cacheActivitySkuStockCount(Long sku, Integer stockCount) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        activityRepository.cacheActivitySkuStockCount(cacheKey, stockCount);
    }


    @Override
    public boolean subtractionActivitySkuStock(Long sku, Date endDateTime) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        return activityRepository.subtractionActivitySkuStock(sku, cacheKey, endDateTime);
    }

}
