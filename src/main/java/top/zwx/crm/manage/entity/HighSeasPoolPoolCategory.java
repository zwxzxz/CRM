
package top.zwx.crm.manage.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 公海池池类别实体类
 *
 * @author zwx
 */
public class HighSeasPoolPoolCategory {
    /**
     * 以JavaFX属性绑定的形式，定义和数据表字段id和type_name对应的属性，注意命名规范
     */
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty poolname = new SimpleStringProperty("");

    public HighSeasPoolPoolCategory() {
    }

    public HighSeasPoolPoolCategory(long id, String poolname) {
        setId(id);
        setPoolname(poolname);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getPoolname() {
        return poolname.get();
    }

    public SimpleStringProperty poolnameProperty() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname.set(poolname);
    }

    @Override
    public String toString() {
        return poolname.get();
    }
}
