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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import top.zwx.crm.manage.Main;
import top.zwx.crm.manage.dao.*;
import top.zwx.crm.manage.entity.*;
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
 * ConsumerController
 *
 * @author zwx
 */
public class ConsumerController implements Initializable {
    /**
     * 布局文件中的表格视图对象，用来显示数据库中读取的所有客户信息
     */
    @FXML
    private TableView<Consumer> consumerTable;

    /**
     * 布局文件中的下拉框组件对象，用来显示数据库中读取的所有类别
     */
    @FXML
    private ComboBox<HighSeasPoolSourceCategory> sourceCategoryComboBox;
    @FXML
    private ComboBox<HighSeasPoolGradeCategory> gradeCategoryComboBox;
    @FXML
    private ComboBox<HighSeasPoolPoolCategory> poolCategoryComboBox;

    /**
     * 布局文件中的输入文本框对象，用来输入搜索关键词
     */
    @FXML
    private TextField keywordsField;

    /**
     * 公海池客户模型数据集合，可以实时相应数据变化，无需刷新
     */
    private final ObservableList<Consumer> consumerData = FXCollections.observableArrayList();

    /**
     * 公海池类型模型数据集合
     */
    private final ObservableList<HighSeasPoolSourceCategory> sourceCategoryData = FXCollections.observableArrayList();
    private final ObservableList<HighSeasPoolGradeCategory> gradeCategoryData = FXCollections.observableArrayList();
    private final ObservableList<HighSeasPoolPoolCategory> poolCategoryData = FXCollections.observableArrayList();



    /**
     * 公海池客户DAO对象，从DAO工厂通过静态方法获得
     */
    private final ConsumerDAO consumerDAO = DaoFactory.getConsumerDAOInstance();

    /**
     * 公海池类型DAO对象
     */
    private final HighSeasPoolSourceCategoryDAO sourceCategoryDAO = DaoFactory.getHighSeasPoolSourceCategoryDAOInstance();
    private final HighSeasPoolGradeCategoryDAO gradeCategoryDAO = DaoFactory.getHighSeasPoolGradeCategoryDAOInstance();
    private final HighSeasPoolPoolCategoryDAO poolCategoryDAO = DaoFactory.getHighSeasPoolPoolCategoryDAOInstance();


    /**
     * 公海池客户实体集合，存放数据库consumer表各种查询的结果
     */
    private List<Entity> consumerList = null;




    /**
     * 类别实体集合，存放数据库类别表查询结果
     */
    private List<Entity> categoryList = null;

    /**
     * 表格中的领取列
     */
    private final TableColumn<Consumer,Consumer> editCol = new TableColumn<>("操作1");

    /**
     * 表格中的删除列
     */
    private final TableColumn<Consumer,Consumer> delCol = new TableColumn<>("操作2");

    /**
     * 初始化方法，通过调用对公海池表格和列表下拉框的两个封装方法，实现数据初始化
     *
     * @param location  location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initComBox();
        initComBox1();
        initComBox2();
    }

    /**
     * 表格初始化方法
     */
    private void initTable() {
        //水平方向不显示滚动条，表格的列宽会均匀分布
        consumerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //1.调用底层查询所有公海池的方法，
        try {
            consumerList = consumerDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将实体集合作为参数，调用显示数据的方法，可以在界面的表格中显示公海池模型集合的值
        showConsumerData(consumerList);

        //3.删除列的相关设置
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = ComponentUtil.getButton("删除", "warning-theme");

            @Override
            protected void updateItem(Consumer consumer, boolean empty) {
                super.updateItem(consumer, empty);
                if (consumer == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("客户：" + consumer.getName());
                    alert.setContentText("确定要删除这行记录吗?");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        consumerData.remove(consumer);
                        try {

                            consumerDAO.deleteById(consumer.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        //将除列加入图书表格
        consumerTable.getColumns().add(delCol);

        //4.公海池表格双击事件,双击弹出显示客户详情的界面
        consumerTable.setRowFactory(tv -> {
            TableRow<Consumer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                //判断鼠标双击了一行
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //获得该行的客户ID属性
                    long id = row.getItem().getId();
                    Consumer consumer = new Consumer();
                    try {
                        //通过ID到数据库查询到该公海池对象的完整信息，因为表格中没有显示全，
                        // 但是在详情界面需要这些数据，如果不查出完整信息，会有空值异常
                        Entity entity = consumerDAO.getById(id);
                        consumer.setId(entity.getLong("id"));
                        consumer.setName(entity.getStr("name"));
                        consumer.setGrade(entity.getStr("grade"));
                        consumer.setPool(entity.getStr("pool"));
                        consumer.setSource(entity.getStr("source"));

                        DatePicker datePicker = new DatePicker();
                        datePicker.setValue(LocalDate.now());
                        LocalDate createDate = datePicker.getValue();
                        consumer.setCreatetime(createDate);
                        consumer.setCompany(entity.getStr("company"));
                        consumer.setDetails(entity.getStr("details"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    //创建一个新的公海池详情界面窗口
                    Stage consumerInfoStage = new Stage();
                    consumerInfoStage.setTitle("客户详情界面");
                    //用VBox显示具体客户信息
                    VBox vBox = new VBox();
                    vBox.setSpacing(10);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPrefSize(600, 400);
                    vBox.setPadding(new Insets(10, 10, 10, 10));
                    Label nameLabel = new Label("姓名：" +consumer.getName());
                    nameLabel.getStyleClass().add("font-title");
                    Label gradeLable = new Label("等级："+consumer.getGrade());
                    Label poolLabel = new Label("所属公海：" +consumer.getPool());
                    Label sourceLabel = new Label("来源：" + consumer.getSource());
                    Label companyLabel = new Label("公司：" + consumer.getCompany());
                    Label datailsLabel = new Label("客户详情：" + consumer.getDetails());

                    datailsLabel.setPrefWidth(400);
                    datailsLabel.setWrapText(true);
                    datailsLabel.getStyleClass().add("box");
                    vBox.getChildren().addAll(nameLabel, gradeLable,poolLabel,sourceLabel,companyLabel,datailsLabel);
                    Scene scene = new Scene(vBox, 640, 480);
                    //因为是一个新的窗口，需要重新读入一下样式表，这个界面就可以使用style.css样式表中的样式了
                    scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());
                    consumerInfoStage.setScene(scene);
                    consumerInfoStage.show();
                }
            });
            return row;
        });
    }

    /**
     * 下拉框来源类别初始化方法
     */
    private void initComBox() {
        //1.到数据库查询所有的来源类别
        try {
            categoryList = sourceCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.遍历categoryList集合，将其加入categoryData模型数据集合
        for (Entity entity : categoryList) {
            HighSeasPoolSourceCategory category = new HighSeasPoolSourceCategory();
            category.setId(entity.getLong("id"));
            category.setSourcename(entity.getStr("sourcename"));
            sourceCategoryData.add(category);
        }
        sourceCategoryComboBox.setItems(sourceCategoryData);

        //4.下拉框选择事件监听，根据选择不同的类别，表格中会过滤出该类别的客户
        sourceCategoryComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    System.out.println(newValue.getId() + "," + newValue.getSourcename());
                    //移除掉之前的数据
                    consumerTable.getItems().removeAll(consumerData);
                    try {
                        //根据选中的类别查询该类别所有图书
                        consumerList = consumerDAO.selectBySourceCategoryId(newValue.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                  //重新显示数据
                  showConsumerData(consumerList);
                }
        );
    }

    /**
     * 下拉框公海池类别初始化方法
     */
    private void initComBox1() {
        //1.到数据库查询所有的来源类别
        try {
            categoryList = gradeCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.遍历categoryList集合，将其加入categoryData模型数据集合
        for (Entity entity : categoryList) {
            HighSeasPoolGradeCategory category = new HighSeasPoolGradeCategory();
            category.setId(entity.getLong("id"));
            category.setGradename(entity.getStr("gradename"));
            gradeCategoryData.add(category);
        }
        gradeCategoryComboBox.setItems(gradeCategoryData);

        //4.下拉框选择事件监听，根据选择不同的类别，表格中会过滤出该类别的客户
        gradeCategoryComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    System.out.println(newValue.getId() + "," + newValue.getGradename());
                    //移除掉之前的数据
                    consumerTable.getItems().removeAll(consumerData);
                    try {
                        //根据选中的类别查询该类别所有图书
                        consumerList = consumerDAO.selectByGradeCategoryId(newValue.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //重新显示数据
            showConsumerData(consumerList);
                }
        );
    }

    /**
     * 下拉框公海池类别初始化方法
     */
    private void initComBox2() {
        //1.到数据库查询所有的来源类别
        try {
            categoryList = poolCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.遍历categoryList集合，将其加入categoryData模型数据集合
        for (Entity entity : categoryList) {
            HighSeasPoolPoolCategory category = new HighSeasPoolPoolCategory();
            category.setId(entity.getLong("id"));
            category.setPoolname(entity.getStr("poolname"));
            poolCategoryData.add(category);
        }
        poolCategoryComboBox.setItems(poolCategoryData);

        //4.下拉框选择事件监听，根据选择不同的类别，表格中会过滤出该类别的客户
        poolCategoryComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    System.out.println(newValue.getId() + "," + newValue.getPoolname());
                    //移除掉之前的数据
                    consumerTable.getItems().removeAll(consumerData);
                    try {
                        //根据选中的类别查询该类别所有图书
                        consumerList = consumerDAO.selectByPoolCategoryId(newValue.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //重新显示数据
            showConsumerData(consumerList);
                }
        );
    }

    /**
     * 显示公海池客户表格数据的方法
     *
     * @param consumerist 客户列表
     */
    private void showConsumerData(List<Entity> consumerist) {
        for (Entity entity : consumerist) {
            Consumer consumer = new Consumer();
            consumer.setId(entity.getLong("id"));
            consumer.setName(entity.getStr("name"));
            consumer.setGrade(entity.getStr("grade"));
            consumer.setPool(entity.getStr("pool"));
            consumer.setSource(entity.getStr("source"));
            consumer.setCreatetime(entity.getDate("createtime").toString());
            consumer.setCompany(entity.getStr("company"));
            consumer.setDetails(entity.getStr("details"));
            consumerData.add(consumer);
        }
        consumerTable.setItems(consumerData);
    }

    /**
     * 根据关键词搜索方法
     */
    public void search() {
        consumerTable.getItems().removeAll(consumerData);
        //获得输入的查询关键字
        String keywords = keywordsField.getText().trim();
        try {
            consumerList = consumerDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showConsumerData(consumerList);
    }

    /**
     * 数据导出方法，采用hutool提供的工具类
     */
    public void export() {
        ExcelExport.export3(consumerList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("公海池已导出!");
        alert.showAndWait();
    }
}
