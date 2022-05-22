package com.fis.hotelmanagementapp.controllers;

import com.fis.hotelmanagementapp.services.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private Button back_home;

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
    public void updateHotelDetails() throws SQLException {
        String updateQuery = "UPDATE details set parking=?, spa=?, pool=?, food=?, location=?, rooms=? WHERE id=1";
        pst = connection.prepareStatement(updateQuery);
        pst.setString(1, parking.getText());
        pst.setString(2, spa.getText());
        pst.setString(3, pool.getText());
        pst.setString(4, food.getText());
        pst.setString(5, location.getText());
        pst.setString(6, rooms.getText());
        pst.executeUpdate();
    }

    @FXML
    public void handleHomeButton(javafx.event.ActionEvent actionEvent) throws IOException {
        parking.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hotelpageadmin.fxml"));
        Scene scene = new Scene(root);
        home.setTitle("Luxos Resorts");
        home.setScene(scene);
        home.show();


    }
}
