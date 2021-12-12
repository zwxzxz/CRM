package top.zwx.crm.manage.controller;

import cn.hutool.db.Entity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import top.zwx.crm.manage.Main;
import top.zwx.crm.manage.dao.BookDAO;
import top.zwx.crm.manage.dao.CategoryDAO;
import top.zwx.crm.manage.dao.CustomerDAO;
import top.zwx.crm.manage.dao.SourceCategoryDAO;
import top.zwx.crm.manage.entity.Book;
import top.zwx.crm.manage.entity.Category;
import top.zwx.crm.manage.entity.Customer;
import top.zwx.crm.manage.entity.SourceCategory;
import top.zwx.crm.manage.util.ComponentUtil;
import top.zwx.crm.manage.util.DaoFactory;
import top.zwx.crm.manage.util.ExcelExport;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * BookController
 *
 * @author mqxu
 */
public class xiansuochiController implements Initializable {
    /**
     * 布局文件中的表格视图对象，用来显示数据库中读取的所有客户信息
     */
    @FXML
    private TableView<Customer> customerTable;

    /**
     * 布局文件中的下拉框组件对象，用来显示数据库中读取的所有线索来源类别
     */
    @FXML
    private ComboBox<SourceCategory> categoryComboBox;

    /**
     * 布局文件中的输入文本框对象，用来输入搜索关键词
     */
    @FXML
    private TextField keywordsField;

    /**
     * 线索池客户模型数据集合，可以实时相应数据变化，无需刷新
     */
    private final ObservableList<Customer> customerData = FXCollections.observableArrayList();

    /**
     * 线索来源类型模型数据集合
     */
    private final ObservableList<SourceCategory> categoryData = FXCollections.observableArrayList();

    /**
     * 线索池客户DAO对象，从DAO工厂通过静态方法获得
     */
    private final CustomerDAO customerDAO = DaoFactory.getCustomerDAOInstance();

    /**
     * 线索来源类别DAO对象
     */
    private final SourceCategoryDAO categoryDAO = DaoFactory.getSourceCategoryDAOInstance();

    /**
     * 线索池客户实体集合，存放数据库xianshuochi表各种查询的结果
     */
    private List<Entity> customerList = null;

    /**
     * 类别实体集合，存放数据库类别表查询结果
     */
    private List<Entity> categoryList = null;

    /**
     * 表格中的编辑列
     */
    //private final TableColumn<Customer,Customer> editCol = new TableColumn<>("操作");

    /**
     * 表格中的删除列
     */
    private final TableColumn<Customer,Customer> delCol = new TableColumn<>("操作");

    /**
     * 初始化方法，通过调用对线索池表格和列表下拉框的两个封装方法，实现数据初始化
     *
     * @param location  location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initComBox();
    }

    /**
     * 表格初始化方法
     */
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //1.调用底层查询所有图书的方法，
        try {
            customerList = customerDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将实体集合作为参数，调用显示数据的方法，可以在界面的表格中显示图书模型集合的值
        showBookData(customerList);

        //2.编辑列的相关设置
        //editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        //
        //editCol.setCellFactory(param -> new TableCell<>() {
        //    //通过ComponentUtil工具类的静态方法，传入按钮文字和样式，获得一个按钮对象
        //    private final Button editButton = ComponentUtil.getButton("编辑", "blue-theme");
        //
        //    @Override
        //    protected void updateItem(Customer customer, boolean empty) {
        //        super.updateItem(customer, empty);
        //        if (customer == null) {
        //            setGraphic(null);
        //            return;
        //        }
        //        setGraphic(editButton);
        //        //点击编辑按钮，弹出窗口，输入需要修改的图书价格
        //        editButton.setOnAction(event -> {
        //            TextInputDialog dialog = new TextInputDialog("请输入价格");
        //            dialog.setTitle("图书修改界面");
        //            dialog.setHeaderText("书名：" + book.getName());
        //            dialog.setContentText("请输入新的价格:");
        //            Optional<String> result = dialog.showAndWait();
        //            //确认输入了内容，避免NPE
        //            if (result.isPresent()) {
        //                //获取输入的新价格并转化成Double数据
        //                String priceString = result.get();
        //                book.setPrice(Double.parseDouble(priceString));
        //                try {
        //                    //更新图书信息
        //                    bookDAO.update(book);
        //                } catch (SQLException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        });
        //    }
        //});
        ////将编辑列加入图书表格
        //bookTable.getColumns().add(editCol);

        //3.删除列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = ComponentUtil.getButton("删除", "warning-theme");

            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (customer == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("客户：" + customer.getName());
                    alert.setContentText("确定要删除这行记录吗?");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        customerData.remove(customer);
                        try {
                            customerDAO.deleteById(customer.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        //将除列加入图书表格
        customerTable.getColumns().add(delCol);

        //4.线索池表格双击事件,双击弹出显示客户详情的界面
        customerTable.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                //判断鼠标双击了一行
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //获得该行的客户ID属性
                    long id = row.getItem().getId();
                    Customer customer = new Customer();
                    try {
                        //通过ID到数据库查询到该图书对象的完整信息，因为表格中没有显示全，如
                        //图书封面、摘要等，但是在详情界面需要这些数据，如果不查出完整信息，会有空值异常
                        Entity entity = customerDAO.getById(id);
                        customer.setId(entity.getLong("id"));
                        customer.setName(entity.getStr("name"));
                        customer.setCompany(entity.getStr("company"));
                        customer.setSource(entity.getStr("source"));
                        customer.setDetailed(entity.getStr("detailed"));
                        customer.setPool(entity.getStr("pool"));
                        DatePicker datePicker = new DatePicker();
                        datePicker.setValue(LocalDate.now());
                        LocalDate createDate = datePicker.getValue();
                        customer.setCreatetime(createDate);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    //创建一个新的线索池详情界面窗口
                    Stage customerInfoStage = new Stage();
                    customerInfoStage.setTitle("线索详情界面");
                    //用VBox显示具体线索信息
                    VBox vBox = new VBox();
                    vBox.setSpacing(10);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPrefSize(600, 400);
                    vBox.setPadding(new Insets(10, 10, 10, 10));
                    Label nameLabel = new Label("姓名：" +customer.getName());
                    nameLabel.getStyleClass().add("font-title");
                    Label authorLabel = new Label("公司：" +customer.getCompany());
                    Label priceLabel = new Label("线索来源：" + customer.getSource());
                    Label summaryLabel = new Label("线索详细：" + customer.getDetailed());
                    summaryLabel.setPrefWidth(400);
                    summaryLabel.setWrapText(true);
                    summaryLabel.getStyleClass().add("box");
                    vBox.getChildren().addAll(nameLabel, authorLabel, priceLabel, summaryLabel);
                    Scene scene = new Scene(vBox, 640, 480);
                    //因为是一个新的窗口，需要重新读入一下样式表，这个界面就可以使用style.css样式表中的样式了
                    scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());
                    customerInfoStage.setScene(scene);
                    customerInfoStage.show();
                }
            });
            return row;
        });
    }

    /**
     * 下拉框初始化方法
     */
    private void initComBox() {
        //1.到数据库查询所有的类别
        try {
            categoryList = categoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.遍历categoryList集合，将其加入categoryData模型数据集合
        for (Entity entity : categoryList) {
            SourceCategory category = new SourceCategory();
            category.setId(entity.getLong("id"));
            category.setSourcename(entity.getStr("sourcename"));
            categoryData.add(category);
        }
        categoryComboBox.setItems(categoryData);

        //4.下拉框选择事件监听，根据选择不同的类别，图书表格中会过滤出该类别的图书
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    System.out.println(newValue.getId() + "," + newValue.getSourcename());
                    //移除掉之前的数据
                    customerTable.getItems().removeAll(customerData);
                    try {
                        //根据选中的类别查询该类别所有图书
                        customerList = customerDAO.selectByCategoryId(newValue.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //重新显示数据
                    showBookData(customerList);
                }
        );
    }

    /**
     * 显示图书表格数据的方法
     *
     * @param bookList 图书列表
     */
    private void showBookData(List<Entity> bookList) {
        for (Entity entity : bookList) {
            Customer customer = new Customer();
            customer.setId(entity.getLong("id"));
            customer.setName(entity.getStr("name"));
            customer.setCompany(entity.getStr("company"));
            customer.setSource(entity.getStr("source"));
            customer.setDetailed(entity.getStr("detailed"));
            customer.setPool(entity.getStr("pool"));
            DatePicker datePicker = new DatePicker();
            datePicker.setValue(LocalDate.now());
            LocalDate createDate = datePicker.getValue();
            customer.setCreatetime(createDate);
            customerData.add(customer);
        }
        customerTable.setItems(customerData);
    }

    /**
     * 弹出新增图书界面方法
     */
    public void newCustomerStage() throws Exception {
        Stage addCustomerStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/add_customer_xiansuochi.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());
        AddCustomerController addCustomerController = fxmlLoader.getController();
        addCustomerController.setCustomerData(customerData);
        addCustomerStage.setTitle("新增线索界面");
        addCustomerStage.setResizable(false);
        addCustomerStage.setScene(scene);
        addCustomerStage.show();
    }

    /**
     * 根据关键词搜索方法
     */
    public void search() {
        customerTable.getItems().removeAll(customerData);
        //获得输入的查询关键字
        String keywords = keywordsField.getText().trim();
        try {
            customerList = customerDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showBookData(customerList);
    }

    /**
     * 数据导出方法，采用hutool提供的工具类
     */
    public void export() {
        ExcelExport.export(customerList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("图书数据已导出!");
        alert.showAndWait();
    }
}
