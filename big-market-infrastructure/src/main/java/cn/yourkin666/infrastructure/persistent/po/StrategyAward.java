package cn.yourkin666.infrastructure.persistent.po;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author yourkin666
 * @date 2025/01/16/23:32
 * @description 抽奖策略奖品明细配置 - 概率、规则
 */
@Data
public class StrategyAward {
    /** 自增id*/
    private Long id;
    /** 抽奖策略id*/
    private Long strategyId;
    /** 奖品id 内部流转使用*/
    private Integer awardId;
    /** 奖品标题*/
    private String awardTitle;
    /** 奖品副标题*/
    private String awardSubtitle;
    /** 奖品库存总量*/
    private Integer awardCount;
    /** 奖品库存剩余*/
    private Integer awardCountSurplus;
    /** 中将概率*/
    private BigDecimal awardRate;
    /** 规则模型，rule配置的模型同步到此表，便于使用*/
    private String ruleModels;
    /** 排序*/
    private Integer sort;
    /** */
    private Date createTime;
    /** */
    private Date updateTime;
}
