package com.fis.hotelmanagementapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RateHotelController implements Initializable {
    @FXML
    private Rating rating;

    @FXML
    private Button back_home;

    @FXML Label stars;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;
    private Statement statement;
    private PreparedStatement pst2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();

        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                String rateQuery = "INSERT INTO rating (rate) VALUES (?)";
                String averageQuery = "SELECT ROUND(AVG(rate), 2) from rating";

                try {
                    pst = connection.prepareStatement(rateQuery);
                    pst.setString(1, String.valueOf(t1));
                    pst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    pst2 = connection.prepareStatement(averageQuery);
                    ResultSet rs = pst2.executeQuery();
                    if(rs.next()) {
                        stars.setText(String.valueOf(rs.getDouble(1)));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                OptionPane("Thank you for rating our hotel!","Rating Message");
            }
        });

    }

    @FXML
    public void handleHomeButton(javafx.event.ActionEvent actionEvent) throws IOException {
        rating.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hotelpage.fxml"));
        Scene scene = new Scene(root);
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
