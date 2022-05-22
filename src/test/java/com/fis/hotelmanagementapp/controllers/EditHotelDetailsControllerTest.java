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
class EditHotelDetailsControllerTest extends ApplicationTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("hoteldetails.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void EditDetailsTest(FxRobot fxRobot) {

            fxRobot.clickOn("#parking");
            fxRobot.clickOn("#spa");
            fxRobot.clickOn("#pool");
            fxRobot.clickOn("#food");
            fxRobot.clickOn("#location");
            fxRobot.clickOn("#rooms");
    }

    @Test
    public void LoadButtonTest(FxRobot fxRobot){
        fxRobot.clickOn("#load");
    }

    @Test
    public void UpdateButtonTest(FxRobot fxRobot){
        fxRobot.clickOn("#update");
    }

    @Test
    public void BackButtonTest(FxRobot fxRobot){
        fxRobot.clickOn("#back_home");
        FxAssert.verifyThat(window("Luxos Resorts"), WindowMatchers.isShowing());
    }
}