package top.zwx.crm.manage.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * 公海池实体类
 *
 * @author zwx
 */
public class HighSeasPool {
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty grade = new SimpleStringProperty("");
    private final SimpleStringProperty pool = new SimpleStringProperty("");
    private final SimpleStringProperty source = new SimpleStringProperty("");
    private LocalDate createtime ;
    private final SimpleStringProperty company = new SimpleStringProperty("");
    private final SimpleStringProperty details = new SimpleStringProperty("");
    private final SimpleLongProperty sourceid = new SimpleLongProperty();
    private final SimpleLongProperty gradeid = new SimpleLongProperty();
    private final SimpleLongProperty poolid = new SimpleLongProperty();


    public HighSeasPool() {
    }

    public HighSeasPool(Long id, String name, String grade, String pool, String source, LocalDate createtime,
                        String company,String details,Long sourceid, Long gradeid, Long poolid) {
        setId(id);
        setName(name);
        setGrade(grade);
        setCompany(company);
        setSource(source);
        setDetails(details);
        setPool(pool);
        setCreatetime(createtime);
        setSourceid(sourceid);
        setGradeid(gradeid);
        setPoolid(poolid);
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

    public String getGrade() {
        return grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
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

    public String getSource() {
        return source.get();
    }

    public SimpleStringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
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

    public String getDetails() {
        return details.get();
    }

    public SimpleStringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
    }

    public long getSourceid() {
        return sourceid.get();
    }

    public SimpleLongProperty sourceidProperty() {
        return sourceid;
    }

    public void setSourceid(long sourceid) {
        this.sourceid.set(sourceid);
    }

    public long getGradeid() {
        return gradeid.get();
    }

    public SimpleLongProperty gradeidProperty() {
        return gradeid;
    }

    public void setGradeid(long gradeid) {
        this.gradeid.set(gradeid);
    }

    public long getPoolid() {
        return poolid.get();
    }

    public SimpleLongProperty poolidProperty() {
        return poolid;
    }

    public void setPoolid(long poolid) {
        this.poolid.set(poolid);
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
