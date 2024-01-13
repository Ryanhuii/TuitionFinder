package com.ryanhuii.tuitionfinder.scene_controllers;
import com.ryanhuii.tuitionfinder.tools.SwitchScenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    // my variables for switching scenes
    private Stage stage;
    private Scene scene;
    private Parent root;

    // The page that I'll be switching between.
    // Ok update: this gives problems. Sometimes it doesn't load, giving me null resource errors.
    // I've chosen to instead make my own function that directly takes the fxml file name. Way easier.
    // @Value("classpath:/pages/create-new-account.fxml")
    // private Resource createNewAccountResource;

    @FXML
    private Button btn_login;

    @FXML
    private Label error_password;

    @FXML
    private Label error_username;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void onLogin(ActionEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText();
        System.out.println(
                "Username: " + username +
                " Password: " + password);
        if (username.equals("error") && password.equals("error")) {
            error_username.setVisible(true);
            error_password.setVisible(true);
        } else {
            error_username.setVisible(false);
            error_password.setVisible(false);
        }
    }

    @FXML
    void createNewAccount(MouseEvent event) {
        System.out.println("creating new account");
        SwitchScenes.switchScenesWithinSameWindow("create-new-account.fxml", event, getClass());
//        try {
//            root = FXMLLoader.load(createNewAccountResource.getURL());
//            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}