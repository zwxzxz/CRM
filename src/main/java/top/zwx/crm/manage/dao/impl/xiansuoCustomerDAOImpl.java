package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import top.zwx.crm.manage.dao.CustomerDAO;
import top.zwx.crm.manage.dao.xiansuoCustomerDAO;
import top.zwx.crm.manage.entity.Customer;
import top.zwx.crm.manage.entity.xiansuoCustomer;

import java.sql.SQLException;
import java.util.List;

/**
 * customerDAOImpl
 *
 * @author zwx
 */
public class xiansuoCustomerDAOImpl implements xiansuoCustomerDAO {

    @Override
    public Long insert(xiansuoCustomer xiansuocustomer) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_xiansuo")
                        .set("name",xiansuocustomer.getName())
                        .set("company",xiansuocustomer.getCompany())
                        .set("source",xiansuocustomer.getSource())
                        .set("detailed",xiansuocustomer.getDetailed())
                        .set("pool",xiansuocustomer.getPool())
                        .set("createtime",xiansuocustomer.getCreatetime())
        );
    }


    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_xiansuo").set("id", id)
        );
    }

    @Override
    public int update(xiansuoCustomer xiansuocustomer) throws SQLException {
        return 0;
    }


    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_xiansuo ");
    }

    @Override
    public Entity getById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_xiansuo WHERE id = ? ", id);
    }

    @Override
    public List<Entity> selectByKeywords(String keywords) throws SQLException {
        return Db.use().findLike("t_xiansuo", "pool", keywords, Condition.LikeType.Contains);
    }


    @Override
    public List<Entity> selectByCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_xiansuo WHERE category = ? ", categoryId);
    }

    @Override
    public int countByCategory(String source) throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM t_xiansuo WHERE source = ? ", source).intValue();
    }
}
