package top.zwx.crm.manage.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import top.zwx.crm.manage.dao.BookDAO;
import top.zwx.crm.manage.dao.CustomerDAO;
import top.zwx.crm.manage.entity.Book;
import top.zwx.crm.manage.entity.Customer;


import java.sql.SQLException;
import java.util.List;

/**
 * customerDAOImpl
 *
 * @author zwx
 */
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Long insert(Customer customer) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_xiansuochi")
                        .set("name",customer.getName())
                        .set("company",customer.getCompany())
                        .set("source",customer.getSource())
                        .set("detailed",customer.getDetailed())
                        .set("pool",customer.getPool())
                        .set("createtime",customer.getCreatetime())
        );
    }

    @Override
    public int deleteById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_xiansuochi").set("id", id)
        );
    }

    @Override
    public int update(Customer customer) throws SQLException {
        return 0;
    }

    //@Override
    //public int update(Customer customer) throws SQLException {
    //    //只修改了图书的价格和库存
    //    return Db.use().update(
    //            Entity.create().set("price", book.getPrice())
    //                    .set("stock", book.getStock()),
    //            Entity.create("t_book").set("id", book.getId())
    //    );
    //}

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_xiansuochi ");
    }

    @Override
    public Entity getById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_xiansuochi WHERE id = ? ", id);
    }

    @Override
    public List<Entity> selectByKeywords(String keywords) throws SQLException {
        return Db.use().findLike("t_xiansuochi", "pool", keywords, Condition.LikeType.Contains);
    }


    @Override
    public List<Entity> selectByCategoryId(long categoryId) throws SQLException {
        return Db.use().query("SELECT * FROM t_xiansuochi WHERE category = ? ", categoryId);
    }

    @Override
    public int countByCategory(String source) throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM t_xiansuochi WHERE source = ? ", source).intValue();
    }
}
