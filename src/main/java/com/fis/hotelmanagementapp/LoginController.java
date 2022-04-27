package com.fis.hotelmanagementapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;

    @FXML
    private Button registerpage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
    }

    @FXML
    public void handleLoginAction(javafx.event.ActionEvent actionEvent) throws IOException {
        connection = dbConnection.getConnection();
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = 1;
            }
            if (count == 1) {
                registerpage.getScene().getWindow().hide();
                Stage signup = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("hotelpage.fxml"));
                Scene scene = new Scene(root);
                signup.setScene(scene);
                signup.show();
            } else {
                OptionPane("Username or Password is not Correct", "Error Message");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRegisterButton(javafx.event.ActionEvent actionEvent) throws IOException {
        loginButton.getScene().getWindow().hide();
        Stage register = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("registerchoose.fxml"));
        Scene scene = new Scene(root);
        register.setScene(scene);
        register.show();


    }

    private void OptionPane(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}





