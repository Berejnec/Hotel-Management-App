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
class RegisterClientControllerTest extends ApplicationTest {
    public static final String username = "mani";
    public static final String password = "tractor";
    public static final String repassword = "tractor";
    public static final String firstname = "Marcel";
    public static final String lastname = "Gusca";
    public static final String phone = "0742134";


    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("registerclient.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void registerTest(FxRobot fxRobot) {
        fxRobot.clickOn("#username");
        fxRobot.write(username);
        fxRobot.clickOn("#password");
        fxRobot.write(password);
        fxRobot.clickOn("#reenterPassword");
        fxRobot.write(repassword);
        fxRobot.clickOn("#firstName");
        fxRobot.write(firstname);
        fxRobot.clickOn("#lastName");
        fxRobot.write(lastname);
        fxRobot.clickOn("#phone");
        fxRobot.write(phone);
        fxRobot.clickOn("#register");
    }

    @Test
    public  void backButtonTest(FxRobot fxRobot) {
        fxRobot.clickOn("#back_home");
        FxAssert.verifyThat(window("Luxos Resorts"), WindowMatchers.isShowing());
    }


}