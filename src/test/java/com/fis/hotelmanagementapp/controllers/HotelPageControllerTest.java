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
class HotelPageControllerTest extends ApplicationTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("hotelpage.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void viewHotelDetailsButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#details");
        FxAssert.verifyThat(window("Hotel Details"), WindowMatchers.isShowing());
    }

    @Test
    public void reserveRoomButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#reserve");
        FxAssert.verifyThat(window("Reserve Room"), WindowMatchers.isShowing());
    }

    @Test
    public void rateHotelButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#rate");
        FxAssert.verifyThat(window("Rate Hotel"), WindowMatchers.isShowing());
    }

    @Test
    public void backButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#back_login");
        FxAssert.verifyThat(window("Luxos Resorts"), WindowMatchers.isShowing());
    }

}