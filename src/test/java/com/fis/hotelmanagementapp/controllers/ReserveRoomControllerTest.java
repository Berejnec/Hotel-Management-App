package com.fis.hotelmanagementapp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
class ReserveRoomControllerTest extends ApplicationTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("reserveroom.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }
    @Test
    public void reserveFormTest(FxRobot fxRobot){
        fxRobot.clickOn("#firstName");
        fxRobot.write("Mircea");
        fxRobot.clickOn("#lastName");
        fxRobot.write("cel Batran");
        fxRobot.clickOn("#CNP");
        fxRobot.write("734383843");
        fxRobot.clickOn("#email");
        fxRobot.write("mircea@yahoo.com");
        fxRobot.clickOn("#roomType");
        fxRobot.type(KeyCode.DOWN);
        fxRobot.type(KeyCode.ENTER);
        fxRobot.clickOn("#numberPersons");
        fxRobot.write("1");
        fxRobot.clickOn("#reserve");
    }
    @Test
    public void checkStatusTest(FxRobot fxRobot){
        fxRobot.clickOn("#cnp_check");
        fxRobot.write("734383843");
        fxRobot.clickOn("#check");
    }

    @Test
    public  void backButtonTest(FxRobot fxRobot){
        fxRobot.clickOn("#back_home");
        FxAssert.verifyThat(window("Luxos Resorts"), WindowMatchers.isShowing());
    }
}