package com.fis.hotelmanagementapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EditHotelDetailsController implements Initializable {

    @FXML
    private TextArea parking;

    @FXML
    private TextArea spa;

    @FXML
    private TextArea pool;

    @FXML
    private TextArea food;

    @FXML
    private TextArea location;

    @FXML
    private TextArea rooms;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public void initializeTextAreas() {
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

                parking.setText(parking_text);
                spa.setText(spa_text);
                pool.setText(pool_text);
                food.setText(food_text);
                location.setText(location_text);
                rooms.setText(rooms_text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void updateHotelDetails() {

    }

    @FXML
    public void editHotelDetails(ActionEvent event) throws SQLException {
        String query = "UPDATE details set parking=?, spa=?, pool=?, food=?, location=?, rooms=? WHERE id=1";
        connection = dbConnection.getConnection();
        pst = connection.prepareStatement(query);

        pst.setString(1, parking.getText() + " UPDATE !");
        pst.setString(2, spa.getText() + " UPDATE !");
        pst.setString(3, pool.getText() + " UPDATE !");
        pst.setString(4, food.getText() + " UPDATE !");
        pst.setString(5, location.getText() + " UPDATE !");
        pst.setString(6, rooms.getText() + " UPDATE !");
        pst.executeUpdate();
    }
}
