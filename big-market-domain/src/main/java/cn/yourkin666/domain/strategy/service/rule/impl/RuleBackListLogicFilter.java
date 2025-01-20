package cn.yourkin666.domain.strategy.service.rule.impl;

import cn.yourkin666.domain.strategy.model.entity.RuleActionEntity;
import cn.yourkin666.domain.strategy.model.entity.RuleMatterEntity;
import cn.yourkin666.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.yourkin666.domain.strategy.repository.IStrategyRepository;
import cn.yourkin666.domain.strategy.service.annotation.LogicStrategy;
import cn.yourkin666.domain.strategy.service.rule.ILogicFilter;
import cn.yourkin666.domain.strategy.service.rule.factory.DefaultLogicFactory;
import cn.yourkin666.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description 【抽奖前规则】黑名单用户过滤规则
 * @create 2024-01-06 13:19
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_BLACKLIST)
public class RuleBackListLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository repository;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-黑名单 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

        String userId = ruleMatterEntity.getUserId();

        // 在strategy_rule表中查询rule_value值。查询条件包括strategy_id和rule_model，并且如果传入的awardId属性不为null，还会进一步根据award_id进行过滤
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());

        //如果ruleValue是"1001:user1,user2,user3"，分割后的数组splitRuleValue将会是["1001", "user1,user2,user3"]
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);//取出splitRuleValue[0]也就是给黑名单用户的固定awardId

        // 取出splitRuleValue[1]也就是给黑名单用户id
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);

        //一个个挨着确认该用户是否在黑名单内，在黑名单就给他接管流程了
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                        .ruleModel(DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode())
                        .data(RuleActionEntity.RaffleBeforeEntity.builder()
                                .strategyId(ruleMatterEntity.getStrategyId())
                                .awardId(awardId)
                                .build())
                        .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                        .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                        .build();
            }
        }

        //不在黑名单的情况：放行流程
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }

}
