package com.fis.hotelmanagementapp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.service.support.Capture;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class ReservationRequestsControllerTest extends ApplicationTest {

    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("reservationrequests.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void adminMessageTextTest(FxRobot fxRobot){
        fxRobot.clickOn("#adminMessage");
    }

    @Test
    public void loadButtonTest(FxRobot fxRobot){
        fxRobot.clickOn("#load");
    }

    @Test
    public void backButtonTest(FxRobot fxRobot){
        fxRobot.clickOn("#back_home");
    }
}