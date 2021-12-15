package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import top.zwx.crm.manage.dao.HighSeasPoolSourceCategoryDAO;
import top.zwx.crm.manage.entity.HighSeasPoolSourceCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * SourceTypeDAOImpl
 *
 * @author zwx
 */
public class HighSeasPoolSourceCategoryDAOImpl implements HighSeasPoolSourceCategoryDAO {

    @Override
    public Long insert(HighSeasPoolSourceCategory highSeasPoolSourceCategory) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
        return Db.use().insertForGeneratedKey(
                Entity.create("t_hspsourcecategory")
                        .set("sourcename", highSeasPoolSourceCategory.getSourcename())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_hspsourcecategory").set("id", id)
        );
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_hspsourcecategory ");
    }

    @Override
    public Entity getById(int id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        return Db.use().queryOne("SELECT * FROM t_hspsourcecategory WHERE id = ? ", id);
    }
}
