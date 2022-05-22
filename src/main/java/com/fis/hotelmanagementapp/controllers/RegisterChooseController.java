package com.fis.hotelmanagementapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterChooseController {

    public Button clientButton;
    @FXML
    private Button adminButton;

    public void onClientChoose(ActionEvent event) throws IOException {
        adminButton.getScene().getWindow().hide();
        Stage registerClient = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("registerclient.fxml"));
        Scene scene = new Scene(root);
        registerClient.setTitle("Client Registration");
        registerClient.setScene(scene);
        registerClient.show();
    }

    public void onAdminChoose(ActionEvent event) throws IOException {
        clientButton.getScene().getWindow().hide();
        Stage registerAdmin = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("registeradmin.fxml"));
        Scene scene = new Scene(root);
        registerAdmin.setTitle("Admin Registration");
        registerAdmin.setScene(scene);
        registerAdmin.show();
    }

}
