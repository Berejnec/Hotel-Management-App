package com.fis.hotelmanagementapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RateHotelController implements Initializable {
    @FXML
    private Rating rating;

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
            }
        });
    }

}
