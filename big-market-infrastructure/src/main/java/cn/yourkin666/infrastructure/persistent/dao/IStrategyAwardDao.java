package cn.yourkin666.infrastructure.persistent.dao;

import cn.yourkin666.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yourkin666
 * @date 2025/01/16/23:54
 * @description 抽奖策略奖品明细配置 - 概率、规则 DAO
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();

}
