package com.fis.hotelmanagementapp.controllers;

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
    private Button back_home;

    @FXML
    public void handleHotelDetailsButton(ActionEvent event) throws IOException {
        manageRooms.getScene().getWindow().hide();
        Stage hotelStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hoteldetails.fxml"));
        Scene scene = new Scene(root);
        hotelStage.setTitle("Edit Details");
        hotelStage.setScene(scene);
        hotelStage.show();
    }

    @FXML
    public void handleManageRoomsButton(ActionEvent event) throws IOException {
        manageRooms.getScene().getWindow().hide();
        Stage rooms = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("managerooms.fxml"));
        Scene scene = new Scene(root);
        rooms.setTitle("Manage Rooms");
        rooms.setScene(scene);
        rooms.show();
    }

    @FXML
    public void handleReservationRequestsButton(ActionEvent event) throws IOException {
        manageRooms.getScene().getWindow().hide();
        Stage requests = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("reservationrequests.fxml"));
        Scene scene = new Scene(root);
        requests.setTitle("Reservation Requests");
        requests.setScene(scene);
        requests.show();
    }

    @FXML
    public void handleHomeButton(javafx.event.ActionEvent actionEvent) throws IOException {
        hotelDetails.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("startup.fxml"));
        Scene scene = new Scene(root);
        home.setTitle("Luxos Resorts");
        home.setScene(scene);
        home.show();


    }

}
