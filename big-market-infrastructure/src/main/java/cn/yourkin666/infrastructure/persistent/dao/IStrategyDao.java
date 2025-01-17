package cn.yourkin666.infrastructure.persistent.dao;
import cn.yourkin666.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * @author yourkin666
 * @date 2025/01/16/23:53
 * @description 抽奖策略 DAO
 */

@Mapper
public interface IStrategyDao {

    List<Strategy> queryStrategyList();

}
