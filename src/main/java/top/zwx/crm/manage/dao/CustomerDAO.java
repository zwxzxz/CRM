package top.zwx.crm.manage.dao;

import cn.hutool.db.Entity;
import top.zwx.crm.manage.entity.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * CustomerDAO
 *
 * @author mqxu
 */
public interface CustomerDAO {

    /**
     * 新增线索客户，返回自增主键
     *
     * @param customer customer
     * @return Long
     */
    Long insert(Customer customer) throws SQLException;

    /**
     * 根据id删除线索池客户
     *
     * @param id id
     * @return int
     */
    int deleteById(long id) throws SQLException;

    /**
     * 更新线索池客户
     *
     * @param customer customer
     * @return int
     */
    int update(Customer customer,long id) throws SQLException;


    /**
     * 查询所有线索池客户
     *
     * @return 图书集合
     */
    List<Entity> selectAll() throws SQLException;


    /**
     * 根据id查询线索池客户
     *
     * @param id id
     * @return Entity
     */
    Entity getById(long id) throws SQLException;

    /**
     * 根据线索池模糊查询图书
     *
     * @param keywords 关键字
     * @return 查询结果
     */
    List<Entity> selectByKeywords(String keywords) throws SQLException;

    /**
     * 根据线索来源类别查询客户
     *
     * @param source 线索来源
     * @return 查询结果
     */
    List<Entity> selectByCategoryId(long categoryId) throws SQLException;

    /**
     * 根据线索来源统计客户数量
     *
     * @param source 线索来源
     * @return int
     */
    int countByCategory(String source) throws SQLException;
}
