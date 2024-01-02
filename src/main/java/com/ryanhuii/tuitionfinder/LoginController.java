package com.ryanhuii.tuitionfinder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginController {
    @FXML
    private Button btn_login;

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
    }
}
