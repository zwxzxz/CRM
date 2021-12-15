
package top.zwx.crm.manage.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 公海池客户等级类别实体类
 *
 * @author zwx
 */
public class HighSeasPoolGradeCategory {
    /**
     * 以JavaFX属性绑定的形式，定义和数据表字段id和type_name对应的属性，注意命名规范
     */
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty gradename = new SimpleStringProperty("");

    public HighSeasPoolGradeCategory() {
    }

    public HighSeasPoolGradeCategory(long id, String gradename) {
        setId(id);
        setGradename(gradename);
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

    public String getGradename() {
        return gradename.get();
    }

    public SimpleStringProperty gradenameProperty() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename.set(gradename);
    }

    @Override
    public String toString() {
        return gradename.get();
    }
}
