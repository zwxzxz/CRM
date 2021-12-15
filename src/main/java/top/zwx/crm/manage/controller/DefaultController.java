package top.zwx.crm.manage.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import top.zwx.crm.manage.Main;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @program: crm-manage
 * @description: 默认页
 * @author: zwx
 * @create: 2021-12-13 21:51
 **/
public class DefaultController implements Initializable {
    @FXML
    private WebView webView1;

    @FXML
    private WebView webView2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = webView1.getEngine();
        WebEngine webEngine2 = webView2.getEngine();
        String url1 = Objects.requireNonNull(Main.class.getResource("other/bar-simple1.html")).toExternalForm();
        String url2 = Objects.requireNonNull(Main.class.getResource("other/gauge-clock1.html")).toExternalForm();
        webEngine.load(url1);
        webEngine2.load(url2);
    }
}