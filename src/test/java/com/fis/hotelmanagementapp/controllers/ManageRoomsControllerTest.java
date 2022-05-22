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
class ManageRoomsControllerTest extends ApplicationTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("managerooms.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void addDeleteRoomsTest(FxRobot fxRobot){
        fxRobot.clickOn("#number");
        fxRobot.write("105");
        fxRobot.clickOn("#type");
        fxRobot.write("Double");
        fxRobot.clickOn("#addRoomButton");
        fxRobot.clickOn("#deleteNumber");
        fxRobot.write("105");
        fxRobot.clickOn("#deleteButton");
    }

    @Test
    public void TableTest(FxRobot fxRobot){
        fxRobot.clickOn("#roomTable");
    }

    @Test
    public void backButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#back_home");
        FxAssert.verifyThat(window("Luxos Resorts"), WindowMatchers.isShowing());
    }


}