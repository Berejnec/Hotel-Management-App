package com.fis.hotelmanagementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;



public class HotelPageController {

    @FXML
    private Button details;

    @FXML
    private Button reserve;

    @FXML
    private Button rate;

    @FXML
    public void handleViewHotelDetailsButton(ActionEvent event) throws IOException {
        reserve.getScene().getWindow().hide();
        Stage hotelStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewhoteldetails.fxml"));
        Scene scene = new Scene(root);
        hotelStage.setScene(scene);
        hotelStage.show();
    }

    @FXML
    public void handleReserveRoomButton(ActionEvent event) throws IOException {
        details.getScene().getWindow().hide();
        Stage hotelStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("reserveroom.fxml"));
        Scene scene = new Scene(root);
        hotelStage.setScene(scene);
        hotelStage.show();
    }

    @FXML
    public void handleRateHotelButton(ActionEvent event) throws IOException {
        reserve.getScene().getWindow().hide();
        Stage hotelStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ratehotel.fxml"));
        Scene scene = new Scene(root);
        hotelStage.setScene(scene);
        hotelStage.show();
    }

}
