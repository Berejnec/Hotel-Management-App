package com.fis.hotelmanagementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterClientController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField reenterPassword;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phone;

    private DBConnection dbConnection;
    private Connection connection;
    private PreparedStatement preparedStatement;

    @FXML
    public void handleRegisterAction(ActionEvent event) {
        String usernameText = username.getText();
        String passwordText = password.getText();
        String reenterPasswordText = reenterPassword.getText();
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String phoneText = phone.getText();

        assert usernameText != null;
        assert passwordText != null;
        assert reenterPasswordText != null;
        assert firstNameText != null;
        assert lastNameText != null;
        assert phoneText != null;

        if(usernameText.equals("") || passwordText.equals("") || firstNameText.equals("") || lastNameText.equals("") || phoneText.equals("")) {
            OptionPane("Every field is required", "Error Message");
        } else {
            if(!passwordText.equals(reenterPasswordText)) {
                OptionPane("Passwords do not match!", "Error Message");
            } else {
                String insert = "INSERT INTO users (firstName, lastName, username, password, phone, role) VALUES (?, ?, ?, ?, ?, 'client')";
                connection = dbConnection.getConnection();
                try {
                    preparedStatement = connection.prepareStatement(insert);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    preparedStatement.setString(1, firstNameText);
                    preparedStatement.setString(2, lastNameText);
                    preparedStatement.setString(3, usernameText);
                    preparedStatement.setString(4, passwordText);
                    preparedStatement.setString(5, phoneText);
                    preparedStatement.executeUpdate();
                    OptionPane("Register Done !", "Message");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
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
