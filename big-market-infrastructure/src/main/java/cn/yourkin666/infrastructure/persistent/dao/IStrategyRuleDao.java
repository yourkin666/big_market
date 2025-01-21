package cn.yourkin666.infrastructure.persistent.dao;

import cn.yourkin666.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 策略规则 DAO
 * @create 2023-12-16 13:25
 */
@Mapper
public interface IStrategyRuleDao {

    List<StrategyRule> queryStrategyRuleList();

    StrategyRule queryStrategyRule(StrategyRule strategyRuleReq);

    String queryStrategyRuleValue(StrategyRule strategyRule);

}
