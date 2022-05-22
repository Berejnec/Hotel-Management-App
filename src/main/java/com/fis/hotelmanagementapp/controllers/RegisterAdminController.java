package com.fis.hotelmanagementapp.controllers;

import com.fis.hotelmanagementapp.services.DBConnection;
import javafx.event.ActionEvent;
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
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterAdminController implements Initializable {
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

    @FXML
    private TextField staff;

    @FXML
    private Button back_home;

    private DBConnection dbConnection;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String hexToString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    @FXML
    public void handleRegisterAdminAction(ActionEvent event) throws NoSuchAlgorithmException {
        String usernameText = username.getText();
        String passwordText = password.getText();
        String encryptedPass = hexToString(getSHA(passwordText));
        String reenterPasswordText = reenterPassword.getText();
        String encryptedReenterPass = hexToString(getSHA(reenterPasswordText));
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String phoneText = phone.getText();
        String staffIdText = staff.getText();

        assert usernameText != null;
        assert passwordText != null;
        assert firstNameText != null;
        assert lastNameText != null;
        assert phoneText != null;
        assert staffIdText != null;

        if(!staffIdText.equals("2310")) {
            OptionPane("Incorrect Hotel Staff ID", "Error Message");
        } else {

            if (usernameText.equals("") || encryptedPass.equals("") || firstNameText.equals("") || lastNameText.equals("") || phoneText.equals("")) {
                OptionPane("Every field is required", "Error Message");
            } else {
                if(!encryptedPass.equals(encryptedReenterPass)) {
                    OptionPane("Passwords do not match!", "Error Message");
                } else {
                    String insert = "INSERT INTO users (firstName, lastName, username, password, phone, role) VALUES (?, ?, ?, ?, ?, 'admin')";
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
                        preparedStatement.setString(4, encryptedPass);
                        preparedStatement.setString(5, phoneText);
                        preparedStatement.executeUpdate();
                        OptionPane("Register Done !", "Message");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
    }

    @FXML
    public void handleHomeButton(javafx.event.ActionEvent actionEvent) throws IOException {
        username.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("startup.fxml"));
        Scene scene = new Scene(root);
        home.setTitle("Luxos Resorts");
        home.setScene(scene);
        home.show();


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
