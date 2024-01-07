package com.ryanhuii.tuitionfinder;
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

    // all the pages that I'll be switching between
    @Value("classpath:/pages/create-new-account.fxml")
    private Resource createNewAccountResource;

    @FXML
    private Button btn_login;

    @FXML
    private Label error_password;

    @FXML
    private Label error_username;

    @FXML
    private Label txt_new_account;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private VBox vBoxParent;

    @FXML
    private VBox vBoxTutor;

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
        try {
            root = FXMLLoader.load(createNewAccountResource.getURL());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void highlightParentOff(MouseEvent event) {
        vBoxParent.setStyle("-fx-background-color: transparent; -fx-background-radius: 10; -fx-border-color:  #D9D9D9; -fx-border-radius: 10;");
    }

    @FXML
    void highlightParentOn(MouseEvent event) {
        vBoxParent.setStyle("-fx-background-color: #F4F4F4; -fx-background-radius: 10; -fx-border-color:  #D9D9D9; -fx-border-radius: 10; ");
    }

    @FXML
    void highlightTutorOff(MouseEvent event) {
        vBoxTutor.setStyle("-fx-background-color: transparent; -fx-background-radius: 10; -fx-border-color:  #D9D9D9; -fx-border-radius: 10;");
    }

    @FXML
    void highlightTutorOn(MouseEvent event) {
        vBoxTutor.setStyle("-fx-background-color: #F4F4F4; -fx-background-radius: 10; -fx-border-color:  #D9D9D9; -fx-border-radius: 10; ");
    }
}
