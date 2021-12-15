package top.zwx.crm.manage.dao;

import cn.hutool.db.Entity;
import top.zwx.crm.manage.entity.ClueSourceCategory;
import top.zwx.crm.manage.entity.HighSeasPoolGradeCategory;
import top.zwx.crm.manage.entity.HighSeasPoolSourceCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * 公海类别DAO接口
 *
 * @author zwx
 */
public interface HighSeasPoolSourceCategoryDAO {

    /**
     * 新增公海池来源类别, 返回自增主键(Long)
     *
     * @param highSeasPoolSourceCategory 公海池类别对象
     * @return 自增主键
     */
    Long insert(HighSeasPoolSourceCategory highSeasPoolSourceCategory) throws SQLException;

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
