package top.zwx.crm.manage.dao;

import cn.hutool.db.Entity;
import top.zwx.crm.manage.entity.HighSeasPoolGradeCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * 公海客户等级类别DAO接口
 *
 * @author zwx
 */
public interface HighSeasPoolGradeCategoryDAO {

    /**
     * 新增公海池客户等级类别, 返回自增主键(Long)
     *
     * @param highSeasPoolGradeCategory 公海池类别对象
     * @return 自增主键
     */
    Long insert(HighSeasPoolGradeCategory highSeasPoolGradeCategory) throws SQLException;

    /**
     * 根据id删除类别
     *
     * @param id id
     * @return int
     */
    int deleteById(long id) throws SQLException;

    /**
     * 查询所有类别
     *
     * @return 查询结果
     */
    List<Entity> selectAll() throws SQLException;


    /**
     * 根据id查询类别信息
     *
     * @param id id
     * @return 查询结果
     */
    Entity getById(int id) throws SQLException;
}
