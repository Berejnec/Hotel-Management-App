package com.fis.hotelmanagementapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ViewHotelDetailsController implements Initializable {

    @FXML
    private Label parkingDetails;

    @FXML
    private Label spaDetails;

    @FXML
    private Label poolDetails;

    @FXML
    private Label foodDetails;

    @FXML
    private Label locationDetails;

    @FXML
    private Label roomsDetails;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        String query = "SELECT parking, spa, pool, food, location, rooms  FROM details WHERE id=1";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                String parking_text = rs.getString("parking");
                String spa_text = rs.getString("spa");
                String pool_text = rs.getString("pool");
                String food_text = rs.getString("food");
                String location_text = rs.getString("location");
                String rooms_text = rs.getString("rooms");

                parkingDetails.setText(parking_text);
                spaDetails.setText(spa_text);
                poolDetails.setText(pool_text);
                foodDetails.setText(food_text);
                locationDetails.setText(location_text);
                roomsDetails.setText(rooms_text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
