package top.zwx.crm.manage.dao;

import cn.hutool.db.Entity;
import top.zwx.crm.manage.entity.HighSeasPool;

import java.sql.SQLException;
import java.util.List;

/**
 * HighSeasPoolDAO 公海池接口
 *
 * @author zwx
 */
public interface HighSeasPoolDAO {

    /**
     * 新增公海池，返回自增主键
     *
     * @param highSeasPool highSeasPool
     * @return Long
     */
    Long insert(HighSeasPool highSeasPool) throws SQLException;

    /**
     * 根据id删除公海池客户
     *
     * @param id id
     * @return int
     */
    int deleteById(long id) throws SQLException;

    /**
     * 公海池客户领取到公海
     *
     * @param highSeasPool highSeasPool
     * @return int
     */
    int update(HighSeasPool highSeasPool, long id) throws SQLException;


    /**
     * 查询所有公海池客户
     *
     * @return 公海池集合
     */
    List<Entity> selectAll() throws SQLException;


    /**
     * 根据id查询公海池
     *
     * @param id id
     * @return Entity
     */
    Entity getById(long id) throws SQLException;

    /**
     * 根据公海池模糊查询对象
     *
     * @param keywords 关键字
     * @return 查询结果
     */
    List<Entity> selectByKeywords(String keywords) throws SQLException;

    /**
     * 根据来源类别查询客户
     *
     * @param categoryId 公海池
     * @return 查询结果
     */
    List<Entity> selectBySourceCategoryId(long categoryId) throws SQLException;

    /**
     * 根据等级类别查询客户
     *
     * @param categoryId 公海池
     * @return 查询结果
     */
    List<Entity> selectByGradeCategoryId(long categoryId) throws SQLException;

    /**
     * 根据Pool类别查询客户
     *
     * @param categoryId 公海池
     * @return 查询结果
     */
    List<Entity> selectByPoolCategoryId(long categoryId) throws SQLException;


}
