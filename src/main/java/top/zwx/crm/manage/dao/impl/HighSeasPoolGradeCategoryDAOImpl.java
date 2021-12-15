package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import top.zwx.crm.manage.dao.HighSeasPoolGradeCategoryDAO;
import top.zwx.crm.manage.dao.HighSeasPoolGradeCategoryDAO;
import top.zwx.crm.manage.entity.HighSeasPoolGradeCategory;
import top.zwx.crm.manage.entity.HighSeasPoolGradeCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * GradeTypeDAOImpl
 *
 * @author zwx
 */
public class HighSeasPoolGradeCategoryDAOImpl implements HighSeasPoolGradeCategoryDAO {

    @Override
    public Long insert(HighSeasPoolGradeCategory highSeasPoolGradeCategory) throws SQLException {
        //采用了另一种新增方法，可以返回插入记录的主键（Long类型）
        return Db.use().insertForGeneratedKey(
                Entity.create("t_hspgradecategory")
                        .set("gradename", highSeasPoolGradeCategory.getGradename())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_hspgradecategory").set("id", id)
        );
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_hspgradecategory ");
    }

    @Override
    public Entity getById(int id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        return Db.use().queryOne("SELECT * FROM t_hspgradecategory WHERE id = ? ", id);
    }
}
