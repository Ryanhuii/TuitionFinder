package com.ryanhuii.tuitionfinder.scene_controllers.account;
import com.ryanhuii.tuitionfinder.tools.TuitionFinderTools;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    // my variables for switching scenes
    private Stage stage;
    private Scene scene;
    private Parent root;

    // The page that I'll be switching between.
    // Ok update: this gives problems. Sometimes it doesn't load, giving me null resource errors.
    // I've chosen to instead make my own function that directly takes the fxml file name. Way easier.
    // @Value("classpath:/pages/unused-create-new-account.fxml")
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
    private VBox vBox;

    public void initialize() {
        Platform.runLater( () -> vBox.requestFocus() );
    }

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
        //System.out.println("creating new account");
        TuitionFinderTools.switchScene("/account/create-new-account.fxml", event, getClass());
    }
}
