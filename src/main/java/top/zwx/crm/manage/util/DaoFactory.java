package top.zwx.crm.manage.util;




import top.zwx.crm.manage.dao.*;
import top.zwx.crm.manage.dao.impl.*;
import top.zwx.crm.manage.entity.Customer;

/**
 * 工厂类，用静态方法来生成各个DAO实例
 *
 * @author zwx
 */
public class DaoFactory {
    /**
     * 静态方法，返回TypeDAO实现类的对象
     *
     * @return CustomerDAO
     */
    public static CustomerDAO getCustomerDAOInstance(){
        return new CustomerDAOImpl();
    }

    /**
     * 静态方法，返回SourceCategoryDAO实现类的对象
     *
     * @return SourceCategoryDAO
     */
    public static SourceCategoryDAO getSourceCategoryDAOInstance() {
        return new SourceCategoryDAOImpl();
    }



    /**
     * 静态方法，返回CategoryDAO实现类的对象
     *
     * @return CategoryDAO
     */
    public static CategoryDAO getCategoryDAOInstance() {
        return new CategoryDAOImpl();
    }

    /**
     * 静态方法，返回TypeDAO实现类的对象
     *
     * @return BookDAO
     */
    public static BookDAO getBookDAOInstance() {
        return new BookDAOImpl();
    }


    /**
     * 静态方法，返回UserDAO实现类的对象
     *
     * @return UserDAO
     */
    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }

    /**
     * 静态方法，返回AdminDAO实现类的对象
     *
     * @return AdminDAO
     */
    public static AdminDAO getAdminDAOInstance() {
        return new AdminDAOImpl();
    }
}
