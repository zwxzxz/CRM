package top.zwx.crm.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import top.zwx.crm.manage.dao.HighSeasPoolDAO;
import top.zwx.crm.manage.dao.HighSeasPoolGradeCategoryDAO;
import top.zwx.crm.manage.dao.HighSeasPoolPoolCategoryDAO;
import top.zwx.crm.manage.dao.HighSeasPoolSourceCategoryDAO;
import top.zwx.crm.manage.entity.HighSeasPool;
import top.zwx.crm.manage.entity.HighSeasPoolGradeCategory;
import top.zwx.crm.manage.entity.HighSeasPoolPoolCategory;
import top.zwx.crm.manage.entity.HighSeasPoolSourceCategory;
import top.zwx.crm.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AddHighSeasPoolController
 *
 * @author zwx
 */
public class AddHighSeasPoolController implements Initializable {

    @FXML
    private ComboBox<HighSeasPoolSourceCategory> highSeasPoolSource;

    @FXML
    private ComboBox<HighSeasPoolGradeCategory> highSeasPoolGrade;

    @FXML
    private ComboBox<HighSeasPoolPoolCategory> highSeasPoolPool;

    @FXML
    private TextField highSeasPoolName, highSeasPoolCompany;

    @FXML
    private TextArea highSeasPoolDetailer;

    @FXML
    private DatePicker datePicker= new DatePicker();;

    private ObservableList<HighSeasPool> highSeasPoolData = FXCollections.observableArrayList();


    public ObservableList<HighSeasPool> getHighSeasPoolData() {
        return highSeasPoolData;
    }

    public void setHighSeasPoolData(ObservableList<HighSeasPool> highSeasPoolData) {
        this.highSeasPoolData = highSeasPoolData;
    }


    private final ObservableList<HighSeasPoolSourceCategory> sourceCategoryData = FXCollections.observableArrayList();
    private final ObservableList<HighSeasPoolGradeCategory> gradeCategoryData = FXCollections.observableArrayList();
    private final ObservableList<HighSeasPoolPoolCategory> poolCategoryData = FXCollections.observableArrayList();

    private final HighSeasPoolDAO highSeasPoolDAO = DaoFactory.getHighSeasPoolDAOInstance();

    private final HighSeasPoolSourceCategoryDAO sourceCategoryDAO = DaoFactory.getHighSeasPoolSourceCategoryDAOInstance();
    private final HighSeasPoolGradeCategoryDAO gradeCategoryDAO = DaoFactory.getHighSeasPoolGradeCategoryDAOInstance();
    private final HighSeasPoolPoolCategoryDAO poolCategoryDAO = DaoFactory.getHighSeasPoolPoolCategoryDAOInstance();

    private List<Entity> entityList = null;
    private List<Entity> entityList1 = null;
    private List<Entity> entityList2 = null;

    private String categoryName;
    private String categoryName1;
    private String categoryName2;

    private Long categoryId;
    private Long categoryId1;
    private Long categoryId2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            entityList = sourceCategoryDAO.selectAll();
            entityList1 = gradeCategoryDAO.selectAll();
            entityList2 = poolCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        }
        //来源按钮
        for (Entity entity : entityList) {
            HighSeasPoolSourceCategory sourceCategory = new HighSeasPoolSourceCategory();
            sourceCategory.setId(entity.getLong("id"));
            sourceCategory.setSourcename(entity.getStr("sourcename"));
            sourceCategoryData.add(sourceCategory);
        }
        //等级按钮
        for (Entity entity : entityList1) {
            HighSeasPoolGradeCategory gradeCategory = new HighSeasPoolGradeCategory();
            gradeCategory.setId(entity.getLong("id"));
            gradeCategory.setGradename(entity.getStr("gradename"));
            gradeCategoryData.add(gradeCategory);
        }
        //所属池按钮
        for (Entity entity : entityList2) {
            HighSeasPoolPoolCategory poolCategory = new HighSeasPoolPoolCategory();
            poolCategory.setId(entity.getLong("id"));
            poolCategory.setPoolname(entity.getStr("poolname"));
            poolCategoryData.add(poolCategory);
        }

        highSeasPoolSource.setItems(sourceCategoryData);
        highSeasPoolSource.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    categoryName = newValue.getSourcename();
                    categoryId = newValue.getId();
                }
        );

        highSeasPoolGrade.setItems(gradeCategoryData);
        highSeasPoolGrade.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    categoryName1 = newValue.getGradename();
                    categoryId1 = newValue.getId();
                }
        );

        highSeasPoolPool.setItems(poolCategoryData);
        highSeasPoolPool.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    categoryName2 = newValue.getPoolname();
                    categoryId2 = newValue.getId();
                }
        );

    }
    public void addHighSeasPool() throws Exception {
        System.out.println(categoryId +" "+categoryId1+" "+categoryId2);

        String name = highSeasPoolName.getText();
        String company = highSeasPoolCompany.getText();
        String detailer = highSeasPoolDetailer.getText();

        datePicker.setValue(LocalDate.now());
        LocalDate createDate = datePicker.getValue();

        HighSeasPool highSeasPool = new HighSeasPool();
        highSeasPool.setName(name);
        highSeasPool.setCompany(company);
        highSeasPool.setSource(categoryName);
        highSeasPool.setGrade(categoryName1);
        highSeasPool.setPool(categoryName2);
        highSeasPool.setDetails(detailer);
        highSeasPool.setCreatetime(createDate);
        highSeasPool.setSourceid(categoryId);
        highSeasPool.setGradeid(categoryId1);
        highSeasPool.setPoolid(categoryId2);

        long id = highSeasPoolDAO.insert(highSeasPool);
        highSeasPool.setId(id);
        this.getHighSeasPoolData().add(highSeasPool);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增客户成功!");
        alert.showAndWait();
        Stage stage = (Stage)
        highSeasPoolName.getScene().getWindow();
        stage.close();
    }
}
