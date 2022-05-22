module com.fis.hotelmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.fis.hotelmanagementapp to javafx.fxml;
    exports com.fis.hotelmanagementapp;
    exports com.fis.hotelmanagementapp.controllers;
    opens com.fis.hotelmanagementapp.controllers to javafx.fxml;
    exports com.fis.hotelmanagementapp.services;
    opens com.fis.hotelmanagementapp.services to javafx.fxml;
    exports com.fis.hotelmanagementapp.models;
    opens com.fis.hotelmanagementapp.models to javafx.fxml;
}