package cn.yourkin666.domain.strategy.service.rule.chain.impl;

import cn.yourkin666.domain.strategy.repository.IStrategyRepository;
import cn.yourkin666.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.yourkin666.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.yourkin666.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 黑名单责任链
 * @create 2024-01-20 10:23
 */
@Slf4j
@Component("rule_blacklist")
public class BlackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        log.info("抽奖责任链-黑名单开始 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());

        // strategy_rule表中根据strategy_id和rule_model查询rule_value，如101:user001,user002,user003
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        //从rule_value中取出给黑名单的奖品awardId
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);

        // 一个个挨着判断该抽奖用户在不在黑名单中
            //在黑名单中的话，就直接把这个固定的黑名单奖品包装返回
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                log.info("抽奖责任链-黑名单接管 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
                return DefaultChainFactory.StrategyAwardVO.builder()
                        .awardId(awardId)
                        .logicModel(ruleModel())
                        .build();
            }
        }

        // 不在黑名单中的话，过滤其他责任链
        log.info("抽奖责任链-黑名单放行 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_BLACKLIST.getCode();
    }

}
