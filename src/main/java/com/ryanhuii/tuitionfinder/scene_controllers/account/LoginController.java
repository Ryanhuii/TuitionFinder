package com.ryanhuii.tuitionfinder.scene_controllers.account;
import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.AccountService;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.service.TutorService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @Autowired
    AccountService accountService;
    @Autowired
    TutorService tutorService;
    @Autowired
    ParentService parentService;

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
    void onKeyTyped(KeyEvent event) {
        btn_login.setDisable(txt_username.getText().isBlank() || txt_password.getText().isBlank());
    }
    @FXML
    void onLogin(ActionEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText();
        // login time.
        Account account = accountService.loginAccount(username,password);
        if (account != null) {
            //System.out.println("we found the account!");
            //System.out.println(account);

            // account successfully found. Can remove the error messages, if there's any
            error_username.setVisible(false);
            error_password.setVisible(false);

            switch (account.getAccountType()) {
                case "Parent":
                    Parent parent = parentService.getParentByUid(account.getUid());
                    System.out.println((parent != null) ? "We found the parent!" + parent: "Uh oh no parent found");
                    LoginUtils.goToParentHomePage(event, getClass(),account,parent);
                    break;
                case "Tutor":
                    Tutor tutor = tutorService.getTutorByUid(account.getUid());
                    System.out.println((tutor != null) ? "We found the tutor!" + tutor: "Uh oh no tutor found");
                    LoginUtils.goToTutorHomePage(event,getClass(),account,tutor);
                    break;
                default:
                    System.out.println("This shouldn't happen...how did account have no type??");
                    break;
            }
        } else {
            // account not found. So either the username doesn't exist, or that the password is wrong.
            Account accountError = accountService.checkIfUsernameExists(username);
            if (accountError != null) {
                if (!accountError.getPassword().equals(password)) {
                    System.out.println("Password is wrong. The correct password is: " + accountError.getPassword());
                    error_username.setVisible(false);
                    error_password.setVisible(true);
                } else {
                    //System.out.println("There's nothing wrong...but somehow I am here.");
                }
            } else {
                //System.out.println("Account is null. We couldn't find a username that matched");
                error_username.setVisible(true);
                error_password.setVisible(false);
            }
        }
    }

    @FXML
    void createNewAccount(MouseEvent event) {
        //System.out.println("creating new account");
        LoginUtils.switchScene("/account/create-new-account.fxml", event, getClass());
    }
}
