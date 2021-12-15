package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import top.zwx.crm.manage.dao.HighSeasPoolPoolCategoryDAO;
import top.zwx.crm.manage.entity.HighSeasPoolPoolCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * PoolTypeDAOImpl
 *
 * @author zwx
 */
public class HighSeasPoolPoolCategoryDAOImpl implements HighSeasPoolPoolCategoryDAO {

    @Override
    public Long insert(HighSeasPoolPoolCategory highSeasPoolPoolCategory) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
        return Db.use().insertForGeneratedKey(
                Entity.create("t_hsppoolcategory")
                        .set("poolname", highSeasPoolPoolCategory.getPoolname())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_hsppoolcategory").set("id", id)
        );
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_hsppoolcategory ");
    }

    @Override
    public Entity getById(int id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        return Db.use().queryOne("SELECT * FROM t_hsppoolcategory WHERE id = ? ", id);
    }
}
