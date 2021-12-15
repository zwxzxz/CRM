package top.zwx.crm.manage.dao;

import cn.hutool.db.Entity;
import top.zwx.crm.manage.entity.Clue;

import java.sql.SQLException;
import java.util.List;

/**
 * ClueDAO 线索接口
 *
 * @author zwx
 */
public interface ClueDAO {


    /**
     * 根据id删除线索客户
     *
     * @param id id
     * @return int
     */
    int deleteById(long id) throws SQLException;

    /**
     * 查询所有线索客户
     *
     * @return 线索集合
     */
    List<Entity> selectAll() throws SQLException;


    /**
     * 根据id查询线索客户
     *
     * @param id id
     * @return Entity
     */
    Entity getById(long id) throws SQLException;

    /**
     * 根据所在线索池类别模糊查询线索
     *
     * @param keywords 关键字
     * @return 查询结果
     */
    List<Entity> selectByKeywords(String keywords) throws SQLException;

    /**
     * 根据线索来源类别查询线索
     *
     * @param
     * @return 查询结果
     */
    List<Entity> selectByCategoryId(long categoryId) throws SQLException;

    /**
     * 更新线索客户
     *
     * @param
     * @return int
     */
    int update(Clue clue) throws SQLException;

    /**
     * 根据线索来源统计客户数量
     *
     * @param categoryId 线索来源
     * @return int
     */
    int countByCategory(Long categoryId) throws SQLException;

    /**
     * 新增线索，返回自增主键
     *
     * @param clue clue
     * @return Long
     */
    Long insert(Clue clue) throws SQLException;
}
