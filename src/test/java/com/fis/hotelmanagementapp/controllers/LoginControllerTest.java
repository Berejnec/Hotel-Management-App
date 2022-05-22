package com.fis.hotelmanagementapp.controllers;

import com.fis.hotelmanagementapp.HelloApplication;
import com.fis.hotelmanagementapp.controllers.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest extends ApplicationTest {


    public static final String username = "asdf";
    public static final String password = "1234";

    @Before
    public void setUpClass() throws Exception {
        ApplicationTest.launch(HelloApplication.class);
    }

    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    public void loginTest(FxRobot fxRobot) {
        fxRobot.clickOn("#username");
        fxRobot.write(username);
        fxRobot.clickOn("#password");
        fxRobot.write(password);
        fxRobot.clickOn("#loginButton");
    }

    @Test
    public void registerButtonTest(FxRobot fxRobot){
        fxRobot.clickOn("#registerpage");
        FxAssert.verifyThat(window("Register Page"), WindowMatchers.isShowing());
    }

}