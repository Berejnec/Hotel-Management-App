package com.fis.hotelmanagementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelPageAdminController {

    @FXML
    private Button hotelDetails;

    @FXML
    private Button manageRooms;

    @FXML
    private Button reservationRequests;

    @FXML
    public void handleHotelDetailsButton(ActionEvent event) throws IOException {
        manageRooms.getScene().getWindow().hide();
        Stage hotelStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hoteldetails.fxml"));
        Scene scene = new Scene(root);
        hotelStage.setScene(scene);
        hotelStage.show();
    }

    @FXML
    public void handleManageRoomsButton(ActionEvent event) throws IOException {
        manageRooms.getScene().getWindow().hide();
        Stage rooms = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("managerooms.fxml"));
        Scene scene = new Scene(root);
        rooms.setScene(scene);
        rooms.show();
    }

    @FXML
    public void handleReservationRequestsButton(ActionEvent event) throws IOException {
        manageRooms.getScene().getWindow().hide();
        Stage requests = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("reservationrequests.fxml"));
        Scene scene = new Scene(root);
        requests.setScene(scene);
        requests.show();
    }

}
