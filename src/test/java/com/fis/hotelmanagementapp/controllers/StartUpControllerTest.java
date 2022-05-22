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
class StartUpControllerTest extends ApplicationTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("startup.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void registerButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#registerButton");
        FxAssert.verifyThat(window("Register choose"), WindowMatchers.isShowing());

    }

    @Test
    public void loginButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#loginButton");
        FxAssert.verifyThat(window("Login page"), WindowMatchers.isShowing());

    }
}