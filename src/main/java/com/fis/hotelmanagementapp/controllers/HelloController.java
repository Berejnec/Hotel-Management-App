package com.fis.hotelmanagementapp.controllers;

import com.fis.hotelmanagementapp.services.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void connectButton(ActionEvent event) {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT firstName FROM users";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()) {
                welcomeText.setText(queryOutput.getString("firstName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}