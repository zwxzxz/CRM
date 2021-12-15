package top.zwx.crm.manage.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
/**
 * 线索池实体类
 *
 * @author zwx
 */
public class CluePool {
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty company = new SimpleStringProperty("");
    private final SimpleStringProperty source = new SimpleStringProperty("");
    private final SimpleStringProperty detailed = new SimpleStringProperty("");
    private final SimpleStringProperty pool = new SimpleStringProperty("");
    private LocalDate createtime ;
    private final SimpleLongProperty category = new SimpleLongProperty();


    public CluePool() {
    }

    public CluePool(Long id, String name, String company, String source, String detailed, String pool, LocalDate createtime,Long category) {
        setId(id);
        setName(name);
        setCompany(company);
        setSource(source);
        setDetailed(detailed);
        setPool(pool);
        setCreatetime(createtime);
        setCategory(category);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getSource() {
        return source.get();
    }

    public SimpleStringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getDetailed() {
        return detailed.get();
    }

    public SimpleStringProperty detailedProperty() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed.set(detailed);
    }

    public String getPool() {
        return pool.get();
    }

    public SimpleStringProperty poolProperty() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool.set(pool);
    }

    public long getCategory() {
        return category.get();
    }

    public SimpleLongProperty categoryProperty() {
        return category;
    }

    public void setCategory(long category) {
        this.category.set(category);
    }

    public LocalDate getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public void setCreatetime(String createtime) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.createtime =format1.parse(createtime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
