package com.fis.hotelmanagementapp.controllers;

import com.fis.hotelmanagementapp.services.DBConnection;
import com.fis.hotelmanagementapp.models.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageRoomsController implements Initializable {

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, String> roomNumber;

    @FXML
    private TableColumn<Room, String> roomType;

    @FXML
    private TableColumn<Room, String> roomStatus;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;

    public static final ObservableList<Room> rooms = FXCollections.observableArrayList();

    public static final List<Room> roomList = new ArrayList<>();

    @FXML
    private TextField number;

    @FXML
    private TextField type;

    @FXML
    private TextField deleteNumber;

    @FXML
    private Button deleteButton;

    @FXML
    private Button back_home;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        roomStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        try {
            initializeRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }
        roomTable.setItems(rooms);
    }

    public void initializeRooms() {
        roomList.clear();
        rooms.clear();

        String query = "SELECT * from rooms";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                int room_number = Integer.parseInt(rs.getString("roomNumber"));
                String room_type = rs.getString("roomType");
                String room_status = rs.getString("roomStatus");
                roomList.add(new Room(room_number, room_type, room_status));
                rooms.add(new Room(room_number, room_type, room_status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void handleAddRoom(ActionEvent event) {
        String query = "INSERT INTO rooms (roomNumber, roomType) VALUES (?, ?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, number.getText());
            pst.setString(2, type.getText());
            roomList.add(new Room(Integer.parseInt(number.getText()), type.getText(), "Not Booked"));
            rooms.add(new Room(Integer.parseInt(number.getText()), type.getText(), "Not Booked"));
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleDeleteRoom(ActionEvent event) throws SQLException {
        String deleteQuery = "DELETE FROM rooms WHERE roomNumber=?";
        try {
            pst = connection.prepareStatement(deleteQuery);
            pst.setString(1, deleteNumber.getText());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeRooms();
    }

    @FXML
    public void handleHomeButton(javafx.event.ActionEvent actionEvent) throws IOException {
        roomTable.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hotelpageadmin.fxml"));
        Scene scene = new Scene(root);
        home.setScene(scene);
        home.show();


    }
}
