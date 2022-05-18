package com.fis.hotelmanagementapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ReserveRoomController implements Initializable {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField CNP;

    @FXML
    private TextField email;

    @FXML
    private TextField numberPersons;

    @FXML
    private ComboBox<String> roomType;

    @FXML
    private TextField cnp_check;

    @FXML
    private Label admin_message;

    private PreparedStatement pst;
    private Connection connection;

    private DBConnection dbConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         dbConnection = new DBConnection();
         connection = dbConnection.getConnection();
        insertRoomType();
    }

    private void insertRoomType() {
        roomType.getItems().removeAll(roomType.getItems());
        String query = "SELECT DISTINCT roomType FROM rooms";
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String room_type = rs.getString("roomType");
                roomType.getItems().add(room_type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleSubmitAction(javafx.event.ActionEvent actionEvent) {
        String first_name=firstName.getText();
        String last_name=lastName.getText();
        String cnp_text=CNP.getText();
        String email_text=email.getText();
        String number_persons=numberPersons.getText();
        String room_type= roomType.getSelectionModel().getSelectedItem();

        if(first_name.equals("") || last_name.equals("") || cnp_text.equals("") || email_text.equals("") || number_persons.equals("") || room_type.equals(""))
        {
            OptionPane("Every Field is required", "Error Message");
        }else{
            String request_query="INSERT INTO requests(firstName, lastName, cnp, email, roomType, persons ) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            pst=connection.prepareStatement(request_query);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        try{
            pst.setString(1,first_name);
            pst.setString(2,last_name);
            pst.setString(3,cnp_text);
            pst.setString(4,email_text);
            pst.setString(5,room_type);
            pst.setString(6,number_persons);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            OptionPane("Room Reservation Request Succeed", "Message");
        }

    }
    @FXML
    public void handleCheckStatus(javafx.event.ActionEvent actionEvent) throws SQLException {
        String checkQuery="SELECT adminMessage FROM requests WHERE cnp=?";
        String cnp_check_text=cnp_check.getText();
        pst=connection.prepareStatement(checkQuery);
        pst.setString(1,cnp_check_text);
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
          String message=rs.getString("adminMessage");
          admin_message.setText(message);
        }else {
            OptionPane("Reservation does not exist or it was declined", "Status Message");
        }
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

