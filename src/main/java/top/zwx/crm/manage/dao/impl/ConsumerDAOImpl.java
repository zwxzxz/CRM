package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import top.zwx.crm.manage.dao.ConsumerDAO;
import top.zwx.crm.manage.entity.Consumer;

import java.sql.SQLException;
import java.util.List;

/**
 * ConsumerDAOImpl
 *
 * @author zwx
 */

public class ConsumerDAOImpl implements ConsumerDAO {

    @Override
    public Long insert(Consumer consumer) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_consumer")
                        .set("name", consumer.getName())
                        .set("grade", consumer.getGrade())
                        .set("pool", consumer.getPool())
                        .set("source", consumer.getSource())
                        .set("createtime", consumer.getCreatetime())
                        .set("company", consumer.getCompany())
                        .set("details", consumer.getDetails())
                        .set("sourceid",consumer.getSourceid())
                        .set("gradeid",consumer.getGradeid())
                        .set("poolid",consumer.getPoolid())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_consumer").set("id", id)
        );
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_consumer ");
    }

    @Override
    public Entity getById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_consumer WHERE id = ? ", id);
    }

    @Override
    public List<Entity> selectByKeywords(String keywords) throws SQLException {
        return Db.use().findLike("t_consumer", "pool", keywords, Condition.LikeType.Contains);
    }

    @Override
    public List<Entity> selectBySourceCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_consumer WHERE sourceid = ? ", categoryId);
    }

    @Override
    public List<Entity> selectByGradeCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_consumer WHERE gradeid = ? ", categoryId);
    }

    @Override
    public List<Entity> selectByPoolCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_consumer WHERE poolid = ? ", categoryId);
    }
}
