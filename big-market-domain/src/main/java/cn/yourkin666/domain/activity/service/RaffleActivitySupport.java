package cn.yourkin666.domain.activity.service;

import cn.yourkin666.domain.activity.model.entity.ActivityCountEntity;
import cn.yourkin666.domain.activity.model.entity.ActivityEntity;
import cn.yourkin666.domain.activity.model.entity.ActivitySkuEntity;
import cn.yourkin666.domain.activity.repository.IActivityRepository;
import cn.yourkin666.domain.activity.service.rule.factory.DefaultActivityChainFactory;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 抽奖活动的支撑类
 * @create 2024-03-23 09:27
 */
public class RaffleActivitySupport {

    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivitySupport(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        this.activityRepository = activityRepository;
        this.defaultActivityChainFactory = defaultActivityChainFactory;
    }

    //从raffle_activity_sku表中查询返回sku、activity_id、activity_count_id、stock_count, stock_count_surplus
    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySku(sku);
    }

    //raffle_activity表中查询返回activity_id, activity_name, activity_desc, begin_date_time, end_date_time, strategy_id, state
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    //raffle_activity_count表中查询返回activity_count_id, total_count, day_count, month_count
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }

}
