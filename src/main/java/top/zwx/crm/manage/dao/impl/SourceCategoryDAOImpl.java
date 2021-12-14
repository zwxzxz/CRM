package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import top.zwx.crm.manage.dao.SourceCategoryDAO;
import top.zwx.crm.manage.entity.SourceCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * TypeDAOImpl
 *
 * @author zwx
 */
public class SourceCategoryDAOImpl implements SourceCategoryDAO {


    @Override
    public Long insert(SourceCategory sourceCategory) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
        return Db.use().insertForGeneratedKey(
                Entity.create("t_sourcecategory")
                        .set("sourcename", sourceCategory.getSourcename())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_sourcecategory").set("id", id)
        );
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        //1.采用默认的查询
        //return Db.use().findAll("t_category");
        //2.采用自定义查询语句查询
        return Db.use().query("SELECT * FROM t_sourcecategory ");
    }

    @Override
    public Entity getById(int id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        return Db.use().queryOne("SELECT * FROM t_sourcecategory WHERE id = ? ", id);
    }
}
