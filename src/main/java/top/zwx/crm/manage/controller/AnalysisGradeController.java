package top.zwx.crm.manage.controller;

import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import top.zwx.crm.manage.dao.ConsumerDAO;
import top.zwx.crm.manage.dao.HighSeasPoolGradeCategoryDAO;
import top.zwx.crm.manage.util.DaoFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AnalysisgradeController
 *
 * @author zwx
 */
public class AnalysisGradeController implements Initializable {
    @FXML
    private StackPane pieChartPane, barChartPane;

    private final HighSeasPoolGradeCategoryDAO gradeCategoryDAO = DaoFactory.getHighSeasPoolGradeCategoryDAOInstance();
    private final ConsumerDAO consumerDAO = DaoFactory.getConsumerDAOInstance();

    private final ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle regrades) {
        initPieChart();
        initBarChart();
    }

    private void initPieChart() {
        List<Entity> gradeCategoryList = null;
        try {
            gradeCategoryList = gradeCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert gradeCategoryList != null;
        for (Entity entity : gradeCategoryList) {
            try {
                int count = consumerDAO.countByGradeCategory(entity.getLong("id"));
                pieChartData.add(new PieChart.Data(entity.getStr("gradename"), count));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("按所属公海统计饼图");
        pieChartPane.getChildren().add(chart);
    }

    private void initBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("根据所属公海统计柱形图");
        xAxis.setLabel("所属公海");
        yAxis.setLabel("客户数量");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("统计数据");
        List<Entity> gradeCategoryList = null;
        try {
            gradeCategoryList = gradeCategoryDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert gradeCategoryList != null;
        for (Entity entity : gradeCategoryList) {
            try {
                int count = consumerDAO.countByGradeCategory(entity.getLong("id"));
                series1.getData().add(new XYChart.Data(entity.getStr("gradename"), count));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        bc.getData().addAll(series1);
        barChartPane.getChildren().add(bc);

    }
}
