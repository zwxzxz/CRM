module top.zwx.crmmanage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires hutool.all;
    requires java.sql;
    requires lombok;


    opens top.zwx.crm.manage to javafx.fxml;
    exports top.zwx.crm.manage;

}