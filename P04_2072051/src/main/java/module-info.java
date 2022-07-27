module com.example.p04_2072051 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.p04_2072051 to javafx.fxml;
    exports com.example.p04_2072051;
    exports com.example.p04_2072051.utility;
    exports com.example.p04_2072051.entity;
    exports com.example.p04_2072051.controllers;
    opens com.example.p04_2072051.controllers to javafx.fxml;
    exports com.example.p04_2072051.dao;
}