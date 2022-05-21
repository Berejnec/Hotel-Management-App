package com.fis.hotelmanagementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ReservationRequestsController implements Initializable {

    @FXML
    private Label id;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label cnp;

    @FXML
    private Label email;

    @FXML
    private Label roomType;

    @FXML
    private Label persons;

    @FXML
    private Button back_home;

    @FXML
    private TextField adminMessage;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;

    private PreparedStatement pst2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    @FXML
    public void handleLoadRequest(ActionEvent event) throws SQLException {
        String lastLowRequestQuery = "SELECT id, firstName, lastName, cnp, email, roomType, persons FROM requests WHERE confirmed='Not Confirmed'";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(lastLowRequestQuery);
            if(!resultSet.next()) {
                OptionPane("No reservation requests at this moment!", "Message");
            } else {
                do {
                    String id_text = resultSet.getString("id");
                    String firstName_text = resultSet.getString("firstName");
                    String lastName_text = resultSet.getString("lastName");
                    String cnp_text = resultSet.getString("cnp");
                    String email_text = resultSet.getString("email");
                    String roomType_text = resultSet.getString("roomType");
                    String persons_text = resultSet.getString("persons");

                    id.setText(id_text);
                    firstName.setText(firstName_text);
                    lastName.setText(lastName_text);
                    cnp.setText(cnp_text);
                    email.setText(email_text);
                    roomType.setText(roomType_text);
                    persons.setText(persons_text);
                } while(resultSet.next());
            }
        }
    }

    @FXML
    public void handleConfirmRequest(ActionEvent event) throws SQLException {
        String confirmedRequestQuery = "UPDATE requests SET confirmed='Confirmed', adminMessage=? WHERE id=?";
        String adminMessage_text = adminMessage.getText();
        String updateStatusQuery = "UPDATE rooms SET roomStatus='Booked' WHERE roomType=? LIMIT 1";
        String roomTypeText = roomType.getText();
        pst2 = connection.prepareStatement(updateStatusQuery);
        pst2.setString(1, roomTypeText);
        pst = connection.prepareStatement(confirmedRequestQuery);
        pst.setString(1, adminMessage_text);
        pst.setString(2, id.getText());
        pst.executeUpdate();
        pst2.executeUpdate();
        OptionPane("Reservation confirmed successfully!", "Reservation Message");
    }

    @FXML
    public void handleDeclineRequest(ActionEvent event) throws SQLException {
        String deleteRequestQuery = "DELETE FROM requests WHERE id=?";
        pst = connection.prepareStatement(deleteRequestQuery);
        pst.setString(1, id.getText());
        pst.executeUpdate();
        OptionPane("Reservation declined!", "Message");
    }

    @FXML
    public void handleHomeButton(javafx.event.ActionEvent actionEvent) throws IOException {
        firstName.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hotelpageadmin.fxml"));
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
