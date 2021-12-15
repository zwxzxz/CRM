package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import top.zwx.crm.manage.dao.ClueDAO;
import top.zwx.crm.manage.entity.Clue;

import java.sql.SQLException;
import java.util.List;

/**
 * customerDAOImpl
 *
 * @author zwx
 */
public class clueDAOImpl implements ClueDAO {

    @Override
    public Long insert(Clue clue) throws SQLException {
        return null;
        //        Db.use().insertForGeneratedKey(
        //        Entity.create("t_clue")
        //                .set("name",clue.getName())
        //                .set("company",clue.getCompany())
        //                .set("source",clue.getSource())
        //                .set("detailed",clue.getDetailed())
        //                .set("pool",clue.getPool())
        //                .set("createtime",clue.getCreatetime())
        //);
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_clue").set("id", id)
        );
    }

    @Override
    public int update(Clue clue) throws SQLException {
        return 0;
    }


    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_clue ");
    }

    @Override
    public Entity getById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_clue WHERE id = ? ", id);
    }

    @Override
    public List<Entity> selectByKeywords(String keywords) throws SQLException {
        return Db.use().findLike("t_clue", "pool", keywords, Condition.LikeType.Contains);
    }


    @Override
    public List<Entity> selectByCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_clue WHERE category = ? ", categoryId);
    }

    @Override
    public int countByCategory(Long categoryId) throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM t_clue WHERE category = ? ", categoryId).intValue();
    }
}
