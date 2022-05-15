package com.fis.hotelmanagementapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RateHotelController {
    @FXML
    private Rating rating;

    @FXML
    private Label hotelAverageRating;

    @FXML Label stars;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;
    private Statement statement;
    private PreparedStatement pst2;

}
