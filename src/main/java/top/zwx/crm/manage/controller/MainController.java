package top.zwx.crm.manage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import top.zwx.crm.manage.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainController
 *
 * @author mqxu
 */
public class MainController implements Initializable {
    @FXML
    private StackPane mainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane anchorPane = new FXMLLoader(Main.class.getResource("fxml/default.fxml")).load();
            mainContainer.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void listcluepool() throws Exception {
        switchView("cluepool.fxml");
    }
    public void listclue() throws Exception {
        switchView("clue.fxml");
    }

    public void cluesourcecategory() throws Exception {
        switchView("cluesourcecategory.fxml");
    }

    public void listDefault() throws Exception {
        switchView("default.fxml");
    }

    public void listhighseaspool() throws Exception {
        switchView("highseaspool.fxml");
    }
    public void listconsumer() throws Exception {
        switchView("consumer.fxml");
    }




    public void viewBook() throws Exception {
        switchView("view_book.fxml");
    }

    public void listClueAnalysis() throws Exception {
        switchView("clue_analysis.fxml");
    }

    public void listAdmin() throws Exception {
        switchView("admin.fxml");
    }

    public void listUser() throws Exception {
        switchView("user.fxml");
    }

    public void listUserAnalysis() throws Exception {
        switchView("user_analysis.fxml");
    }

    private void switchView(String fileName) throws Exception {
        //清空原有内容
        mainContainer.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(Main.class.getResource("fxml/" + fileName)).load();
        mainContainer.getChildren().add(anchorPane);
    }

    public void exit(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainContainer.getScene().getWindow();
        stage.close();
    }
}
