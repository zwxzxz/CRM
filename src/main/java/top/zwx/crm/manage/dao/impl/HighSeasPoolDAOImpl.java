package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import top.zwx.crm.manage.dao.HighSeasPoolDAO;
import top.zwx.crm.manage.entity.HighSeasPool;

import java.sql.SQLException;
import java.util.List;

/**
 * HighSeasPoolDAOImpl
 *
 * @author zwx
 */

public class HighSeasPoolDAOImpl implements HighSeasPoolDAO {

    @Override
    public Long insert(HighSeasPool highSeasPool) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_highseaspool")
                        .set("name", highSeasPool.getName())
                        .set("grade", highSeasPool.getGrade())
                        .set("pool", highSeasPool.getPool())
                        .set("source", highSeasPool.getSource())
                        .set("createtime", highSeasPool.getCreatetime())
                        .set("company", highSeasPool.getCompany())
                        .set("details", highSeasPool.getDetails())
                        .set("sourceid",highSeasPool.getSourceid())
                        .set("gradeid",highSeasPool.getGradeid())
                        .set("poolid",highSeasPool.getPoolid())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_highseaspool").set("id", id)
        );
    }


    @Override
    public int update(HighSeasPool highSeasPool, long id) throws SQLException {
        Db.use().insertForGeneratedKey(
                Entity.create("t_consumer")
                        .set("name", highSeasPool.getName())
                        .set("grade", highSeasPool.getGrade())
                        .set("pool", highSeasPool.getPool())
                        .set("source", highSeasPool.getSource())
                        .set("createtime", highSeasPool.getCreatetime())
                        .set("company", highSeasPool.getCompany())
                        .set("details", highSeasPool.getDetails())
                        .set("sourceid",highSeasPool.getSourceid())
                        .set("gradeid",highSeasPool.getGradeid())
                        .set("poolid",highSeasPool.getPoolid())
        );
        return Db.use().del(
                Entity.create("t_highseaspool").set("id", id)
        );
    }


    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_highseaspool ");
    }

    @Override
    public Entity getById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_highseaspool WHERE id = ? ", id);
    }

    @Override
    public List<Entity> selectByKeywords(String keywords) throws SQLException {
        return Db.use().findLike("t_highseaspool", "pool", keywords, Condition.LikeType.Contains);
    }

    @Override
    public List<Entity> selectBySourceCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_highseaspool WHERE sourceid = ? ", categoryId);
    }

    @Override
    public List<Entity> selectByGradeCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_highseaspool WHERE gradeid = ? ", categoryId);
    }

    @Override
    public List<Entity> selectByPoolCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_highseaspool WHERE poolid = ? ", categoryId);
    }
}
