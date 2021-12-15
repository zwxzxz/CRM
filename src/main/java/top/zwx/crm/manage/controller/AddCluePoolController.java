package top.zwx.crm.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import top.zwx.crm.manage.dao.ClueSourceCategoryDAO;
import top.zwx.crm.manage.dao.CluePoolDAO;
import top.zwx.crm.manage.entity.CluePool;
import top.zwx.crm.manage.entity.ClueSourceCategory;
import top.zwx.crm.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AddCluePoolController
 *
 * @author zwx
 */
public class AddCluePoolController implements Initializable {

    @FXML
    private ComboBox<ClueSourceCategory> customerCategory;

    @FXML
    private TextField customerName, customerCompany,customerpool;

    @FXML
    private TextArea customerDetailer;

    @FXML
    private DatePicker datePicker= new DatePicker();;

    private ObservableList<CluePool> cluePoolData = FXCollections.observableArrayList();

    public ObservableList<CluePool> getCluePoolData() {
        return cluePoolData;
    }

    public void setCluePoolData(ObservableList<CluePool> cluePoolData) {
        this.cluePoolData = cluePoolData;
    }

    private final ObservableList<ClueSourceCategory> categoryData = FXCollections.observableArrayList();

    private final CluePoolDAO cluePoolDAO = DaoFactory.getCluePoolDAOInstance();

    private final ClueSourceCategoryDAO clueSourceCategoryDAO = DaoFactory.getClueSourceCategoryDAOInstance();

    private List<Entity> entityList = null;

    private Long categoryId ;
    private String categoryName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            entityList = clueSourceCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        }
        for (Entity entity : entityList) {
            ClueSourceCategory category = new ClueSourceCategory();
            category.setId(entity.getLong("id"));
            category.setSourcename(entity.getStr("sourcename"));
            categoryData.add(category);
        }
        customerCategory.setItems(categoryData);
        customerCategory.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            categoryId = newValue.getId();
            categoryName = newValue.getSourcename();
                }
        );

    }

    public void addCluePool() throws Exception {
        String name = customerName.getText();
        String company = customerCompany.getText();
        String pool =customerpool.getText();
        String detailer = customerDetailer.getText();

        datePicker.setValue(LocalDate.now());
        LocalDate createDate = datePicker.getValue();

        CluePool cluePool = new CluePool();
        cluePool.setName(name);
        cluePool.setCompany(company);
        cluePool.setSource(categoryName);
        cluePool.setDetailed(detailer);
        cluePool.setPool(pool);
        cluePool.setCategory(categoryId);
        cluePool.setCreatetime(createDate);
        long id = cluePoolDAO.insert(cluePool);
        cluePool.setId(id);
        this.getCluePoolData().add(cluePool);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增线索成功!");
        alert.showAndWait();
        Stage stage = (Stage) customerName.getScene().getWindow();
        stage.close();
    }
}
