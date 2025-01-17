package cn.yourkin666.infrastructure.persistent.dao;
import cn.yourkin666.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * @author yourkin666
 * @date 2025/01/16/23:52
 * @description奖品表DAO
 */
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
