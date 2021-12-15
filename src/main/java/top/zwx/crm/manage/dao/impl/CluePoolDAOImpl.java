package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import top.zwx.crm.manage.dao.CluePoolDAO;
import top.zwx.crm.manage.entity.CluePool;


import java.sql.SQLException;
import java.util.List;

/**
 * CluePoolDAOImpl 线索池接口实现
 *
 * @author zwx
 */
public class CluePoolDAOImpl implements CluePoolDAO {

    @Override
    public Long insert(CluePool cluePool) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_cluepool")
                        .set("name", cluePool.getName())
                        .set("company", cluePool.getCompany())
                        .set("source", cluePool.getSource())
                        .set("detailed", cluePool.getDetailed())
                        .set("pool", cluePool.getPool())
                        .set("createtime", cluePool.getCreatetime())
                        .set("category",cluePool.getCategory()));
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_cluepool").set("id", id)
        );
    }

    @Override
    public int update(CluePool cluePool, long id) throws SQLException {
        Db.use().insertForGeneratedKey(
                Entity.create("t_clue")
                        .set("name", cluePool.getName())
                        .set("company", cluePool.getCompany())
                        .set("source", cluePool.getSource())
                        .set("detailed", cluePool.getDetailed())
                        .set("pool", cluePool.getPool())
                        .set("createtime", cluePool.getCreatetime())
                        .set("category",cluePool.getCategory())
        );
        return Db.use().del(
                Entity.create("t_cluepool").set("id", id));
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_cluepool ");
    }

    @Override
    public Entity getById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_cluepool WHERE id = ? ", id);
    }

    @Override
    public List<Entity> selectByKeywords(String keywords) throws SQLException {
        return Db.use().findLike("t_cluepool", "pool", keywords, Condition.LikeType.Contains);
    }

    @Override
    public List<Entity> selectByCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_cluepool WHERE category = ? ", categoryId);
    }

    @Override
    public int countByCategory(String source) throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM t_cluepool WHERE source = ? ", source).intValue();
    }
}