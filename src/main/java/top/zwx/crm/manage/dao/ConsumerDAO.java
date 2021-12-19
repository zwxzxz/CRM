package top.zwx.crm.manage.dao;

import cn.hutool.db.Entity;
import top.zwx.crm.manage.entity.Consumer;
import top.zwx.crm.manage.entity.HighSeasPool;

import java.sql.SQLException;
import java.util.List;

/**
 * HighSeasPoolDAO 客户接口
 *
 * @author zwx
 */
public interface ConsumerDAO {

    /**
     * 新增客户，返回自增主键
     *
     * @param consumer consumer
     * @return Long
     */
    Long insert(Consumer consumer) throws SQLException;

    /**
     * 根据id删除客户
     *
     * @param id id
     * @return int
     */
    int deleteById(long id) throws SQLException;


    /**
     * 查询所有客户
     *
     * @return 客户集合
     */
    List<Entity> selectAll() throws SQLException;


    /**
     * 根据id查询客户
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
     * @param categoryId 来源
     * @return 查询结果
     */
    List<Entity> selectBySourceCategoryId(long categoryId) throws SQLException;

    /**
     * 根据客户等级类别查询客户
     *
     * @param categoryId 等级
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

    /**
     * 根据来源统计客户数量
     *
     * @param categoryId 来源
     * @return int
     */
    int countBySourceCategory(Long categoryId) throws SQLException;

    /**
     * 根据客户等级统计客户数量
     *
     * @param categoryId 等级
     * @return int
     */
    int countByGradeCategory(Long categoryId) throws SQLException;


    /**
     * 根据所属公海池统计客户数量
     *
     * @param categoryId 所属公海池
     * @return int
     */
    int countByPoolCategory(Long categoryId) throws SQLException;
}
