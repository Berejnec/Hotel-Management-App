package com.fis.hotelmanagementapp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class HotelPageAdminControllerTest extends ApplicationTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("hotelpageadmin.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }
    @Test
    public void editHotelDetailsButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#hotelDetails");
        FxAssert.verifyThat(window("Edit Details"), WindowMatchers.isShowing());
    }

    @Test
    public void manageRoomsButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#manageRooms");
        FxAssert.verifyThat(window("Manage Rooms"), WindowMatchers.isShowing());
    }

    @Test
    public void RequestsButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#reservationRequests");
        FxAssert.verifyThat(window("Reservation Requests"), WindowMatchers.isShowing());
    }

    @Test
    public void backButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#back_home");
        FxAssert.verifyThat(window("Luxos Resorts"), WindowMatchers.isShowing());
    }
}