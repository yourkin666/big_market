package cn.yourkin666.infrastructure.persistent.dao;

import cn.yourkin666.domain.activity.model.entity.ActivityCountEntity;
import cn.yourkin666.infrastructure.persistent.po.RaffleActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 抽奖活动次数配置表Dao
 * @create 2024-03-09 10:07
 */
@Mapper
public interface IRaffleActivityCountDao {

    RaffleActivityCount queryRaffleActivityCountByActivityCountId(Long activityCountId);

}
