package com.fis.hotelmanagementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartUpController {
    @FXML
    public Button loginButton;
    private DBConnection dbConnection;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection.getConnection();
    }

    public void handleRegisterButton(ActionEvent event) throws IOException {

        loginButton.getScene().getWindow().hide();
        Stage register = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        register.setScene(scene);
        register.show();
    }

    public void handleLoginButton(ActionEvent event) {

    }
}
