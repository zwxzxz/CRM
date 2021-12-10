module top.zwx.crmmanage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires hutool.all;
    requires java.sql;
    requires lombok;


    opens top.zwx.crm.manage to javafx.fxml;
    exports top.zwx.crm.manage;

    opens top.zwx.crm.manage.controller to javafx.fxml;
    exports top.zwx.crm.manage.controller;

    opens top.zwx.crm.manage.dao to javafx.fxml;
    exports top.zwx.crm.manage.dao;

    opens top.zwx.crm.manage.entity to javafx.fxml;
    exports top.zwx.crm.manage.entity;

    opens top.zwx.crm.manage.util to javafx.fxml;
    exports top.zwx.crm.manage.util;
}