package top.zwx.crm.manage.util;




import top.zwx.crm.manage.dao.*;
import top.zwx.crm.manage.dao.impl.*;

/**
 * 工厂类，用静态方法来生成各个DAO实例
 *
 * @author zwx
 */
public class DaoFactory {
    /**
     * 静态方法，返回CluePoolDAO实现类的对象
     *
     * @return CluePoolDAO
     */
    public static CluePoolDAO getCluePoolDAOInstance(){
        return new CluePoolDAOImpl();
    }

    /**
     * 静态方法，返回ClueSourceCategoryDAO实现类的对象
     *
     * @return ClueSourceCategoryDAO
     */
    public static ClueSourceCategoryDAO getClueSourceCategoryDAOInstance() {
        return new ClueSourceCategoryDAOImpl();
    }

    /**
     * 静态方法，返回clueDAO实现类的对象
     *
     * @return ClueDAO
     */
    public static ClueDAO getclueDAOInstance(){
        return new clueDAOImpl();
    }

    /**
     * 静态方法，返回HighSeasPoolDAO实现类的对象
     *
     * @return HighSeasPoolDAO
     */
    public static HighSeasPoolDAO getHighSeasPoolDAOInstance(){
        return new HighSeasPoolDAOImpl();
    }

    /**
     * 静态方法，返回HighSeasPoolSourceCategoryDAO实现类的对象
     *
     * @return HighSeasPoolSourceCategoryDAO
     */
    public static HighSeasPoolSourceCategoryDAO getHighSeasPoolSourceCategoryDAOInstance() {
        return new HighSeasPoolSourceCategoryDAOImpl();
    }

    /**
     * 静态方法，返回HighSeasPoolGradeCategoryDAO实现类的对象
     *
     * @return HighSeasPoolGradeCategoryDAO
     */
    public static HighSeasPoolGradeCategoryDAO getHighSeasPoolGradeCategoryDAOInstance() {
        return new HighSeasPoolGradeCategoryDAOImpl();
    }

    /**
     * 静态方法，返回HighSeasPoolSourceCategoryDAO实现类的对象
     *
     * @return HighSeasPoolSourceCategoryDAO
     */
    public static HighSeasPoolPoolCategoryDAO getHighSeasPoolPoolCategoryDAOInstance() {
        return new HighSeasPoolPoolCategoryDAOImpl();
    }

    /**
     * 静态方法，返回ConsumerDAO实现类的对象
     *
     * @return ConsumerDAO
     */
    public static ConsumerDAO getConsumerDAOInstance() {
        return new ConsumerDAOImpl();
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
