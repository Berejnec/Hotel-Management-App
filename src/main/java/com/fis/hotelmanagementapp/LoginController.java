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
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public void handleLoginAction(javafx.event.ActionEvent actionEvent) throws IOException {
        connection = dbConnection.getConnection();
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            String encryptedpassword=hexToString(getSHA(password.getText()));
            pst = connection.prepareStatement(query);
            pst.setString(1, username.getText());
            pst.setString(2, encryptedpassword);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                if (("" + rs.getString("role")).equals("client")) {
                    registerpage.getScene().getWindow().hide();
                    Stage signup = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("hotelpage.fxml"));
                    Scene scene = new Scene(root);
                    signup.setScene(scene);
                    signup.show();
                } else {
                    if (("" + rs.getString("role")).equals("admin")) {
                        registerpage.getScene().getWindow().hide();
                        Stage signup = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("hotelpageadmin.fxml"));
                        Scene scene = new Scene(root);
                        signup.setScene(scene);
                        signup.show();
                    }
                    else {
                        OptionPane("Username or Password is not Correct", "Error Message");
                    }
                }


            } else {
                OptionPane("Username or Password is not Correct", "Error Message");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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





