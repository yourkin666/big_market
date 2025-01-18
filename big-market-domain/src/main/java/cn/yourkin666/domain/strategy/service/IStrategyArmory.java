package cn.yourkin666.domain.strategy.service;

/**
 * @author yourkin666
 * @date 2025/01/17/19:58
 * @description 策略装配库(兵工厂)，负责初始化策略计算
 */
public interface IStrategyArmory {
    //根据策略id 装配抽奖策略
    /**
     * 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    boolean assembleLotteryStrategy(Long strategyId);

    /**
     * 获取抽奖策略装配的随机结果
     *
     * @param strategyId 策略ID
     * @return 抽奖结果
     */
    Integer getRandomAwardId(Long strategyId);
}
