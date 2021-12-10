module top.zwx.crmmanage {
    requires javafx.controls;
    requires javafx.fxml;


    opens top.zwx.crm.manage to javafx.fxml;
    exports top.zwx.crm.manage;
}