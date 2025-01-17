package cn.yourkin666.infrastructure.persistent.dao;

import cn.yourkin666.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yourkin666
 * @date 2025/01/16/23:54
 * @description 策略规则 DAO
 */
@Mapper
public interface IStrategyRuleDao {

    List<StrategyRule> queryStrategyRuleList();

}
