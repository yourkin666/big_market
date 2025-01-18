package cn.yourkin666.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yourkin666
 * @date 2025/01/17/20:10
 * @description 策略奖品实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardEntity {
    /** 抽奖策略id*/
    private Long strategyId;
    /** 奖品id 内部流转使用*/
    private Integer awardId;
    /** 奖品库存总量*/
    private Integer awardCount;
    /** 奖品库存剩余*/
    private Integer awardCountSurplus;
    /** 中将概率*/
    private BigDecimal awardRate;
}
