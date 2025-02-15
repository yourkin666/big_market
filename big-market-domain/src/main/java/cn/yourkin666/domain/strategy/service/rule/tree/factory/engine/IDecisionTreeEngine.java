package cn.yourkin666.domain.strategy.service.rule.tree.factory.engine;

import cn.yourkin666.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 规则树组合接口
 * @create 2024-01-27 11:33
 */
public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId);

}
