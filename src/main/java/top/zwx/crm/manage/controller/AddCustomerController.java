package top.zwx.crm.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import top.zwx.crm.manage.dao.CustomerDAO;
import top.zwx.crm.manage.dao.SourceCategoryDAO;
import top.zwx.crm.manage.entity.Customer;
import top.zwx.crm.manage.entity.SourceCategory;
import top.zwx.crm.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AddCustomerController
 *
 * @author zwx
 */
public class AddCustomerController implements Initializable {

    @FXML
    private ComboBox<SourceCategory> customerCategory;

    @FXML
    private TextField customerName, customerCompany,customerpool;

    @FXML
    private TextArea customerDetailer;

    @FXML
    private DatePicker datePicker= new DatePicker();;

    private ObservableList<Customer> customerData = FXCollections.observableArrayList();

    public ObservableList<Customer> getCustomerData() {
        return customerData;
    }

    public void setCustomerData(ObservableList<Customer> customerData) {
        this.customerData = customerData;
    }

    private final ObservableList<SourceCategory> categoryData = FXCollections.observableArrayList();

    private final CustomerDAO customerDAO = DaoFactory.getCustomerDAOInstance();

    private final SourceCategoryDAO sourceCategoryDAO = DaoFactory.getSourceCategoryDAOInstance();

    private List<Entity> entityList = null;

    private String categoryId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            entityList = sourceCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        }
        for (Entity entity : entityList) {
            SourceCategory category = new SourceCategory();
            category.setId(entity.getLong("id"));
            category.setSourcename(entity.getStr("sourcename"));
            categoryData.add(category);
        }
        customerCategory.setItems(categoryData);
        customerCategory.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    categoryId = newValue.getSourcename();
                }
        );
    }

    public void addCustomer() throws Exception {
        String name = customerName.getText();
        String company = customerCompany.getText();
        String pool =customerpool.getText();
        String detailer = customerDetailer.getText();

        datePicker.setValue(LocalDate.now());
        LocalDate createDate = datePicker.getValue();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setCompany(company);
        customer.setSource(categoryId);
        customer.setDetailed(detailer);
        customer.setPool(pool);
        customer.setCreatetime(createDate);
        long id = customerDAO.insert(customer);
        customer.setId(id);
        this.getCustomerData().add(customer);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增线索成功!");
        alert.showAndWait();
        Stage stage = (Stage) customerName.getScene().getWindow();
        stage.close();
    }
}
