package cn.yourkin666.domain.strategy.service.rule.tree.factory.engine.impl;

import cn.yourkin666.domain.strategy.model.valobj.*;
import cn.yourkin666.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.yourkin666.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.yourkin666.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 决策树引擎
 * @create 2024-01-27 11:34
 */
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId) {
        DefaultTreeFactory.StrategyAwardVO strategyAwardData = null;

        // 获取rootNode信息
        String nextNode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();

        // 获取起始节点「根节点记录了第一个要执行的规则」
        RuleTreeNodeVO ruleTreeNode = treeNodeMap.get(nextNode);

        //遍历规则树
        while (null != nextNode) {
            // 获取决策节点
            ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(ruleTreeNode.getRuleKey());
            String ruleValue = ruleTreeNode.getRuleValue();

            // 决策节点计算
            DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId, strategyId, awardId, ruleValue);
            RuleLogicCheckTypeVO ruleLogicCheckTypeVO = logicEntity.getRuleLogicCheckType();
            strategyAwardData = logicEntity.getStrategyAwardVO();
            log.info("决策树引擎【{}】treeId:{} node:{} code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(), nextNode, ruleLogicCheckTypeVO.getCode());

            // 获取下个节点
            nextNode = nextNode(ruleLogicCheckTypeVO.getCode(), ruleTreeNode.getTreeNodeLineVOList());
            ruleTreeNode = treeNodeMap.get(nextNode);
        }

        // 返回最终结果
        return strategyAwardData;
    }

    //根据当前决策结果（matterValue）和当前节点的连线信息（treeNodeLineVOList），确定下一个节点
    public String nextNode(String matterValue, List<RuleTreeNodeLineVO> treeNodeLineVOList) {
        //如果连线列表为空或为 null，直接返回 null
        if (null == treeNodeLineVOList || treeNodeLineVOList.isEmpty()) return null;
        //对每一条连线调用 decisionLogic 方法，判断是否符合决策条件
        for (RuleTreeNodeLineVO nodeLine : treeNodeLineVOList) {
            if (decisionLogic(matterValue, nodeLine)) {
                return nodeLine.getRuleNodeTo();
            }
        }
        return null;
    }

    //据当前决策值（matterValue）和连线规则（nodeLine）判断是否满足条件
    public boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            // 以下规则暂时不需要实现
            case GT:
            case LT:
            case GE:
            case LE:
            default:
                return false;
        }
    }

}
