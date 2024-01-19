package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.utils.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsController implements AccountDetailsUpdater {

    Account account;

    @FXML
    private VBox vBoxFocus;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnCheese;
    @FXML
    private Button btnNext;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private PasswordField textFieldConfirmPassword;
    @FXML
    private Label errorMessage;


    public void initialize() {
        // Removes the autofocus
        Platform.runLater(() -> vBoxFocus.requestFocus());
        LoginUtils.setUpBackButton(btnBack, btnCheese, getClass());

        // if coming back from a page ahead, repopulate the fields
        Platform.runLater(() ->
        {
            if (account.getPassword() != null) {
                populateFields();
                btnNext.setDisable(false);
            }
        });
    }


    @FXML
    void onBackClicked(ActionEvent event) {
        LoginUtils.switchScene("/account/create-new-account.fxml", event, getClass());
    }

    @FXML
    void onNextClicked(MouseEvent event) {
        if (textFieldPassword.getText().equals(textFieldConfirmPassword.getText())) {
            errorMessage.setText("");
            if (textFieldPassword.getText().length() < 8) {
                errorMessage.setText("Password must be 8 characters or longer");
                textFieldPassword.setStyle("-fx-border-color: #e80f0f; -fx-border-radius: 5");
                textFieldConfirmPassword.setStyle("-fx-border-color: #e80f0f; -fx-border-radius: 5");
            } else {
                // account details verified. updating account class properties, generating uid and passing it on to next page
                String uid = LoginUtils.generateUID();
                account.setUid(uid);
                account.setUsername(textFieldUsername.getText());
                account.setEmail(textFieldEmail.getText());
                account.setPassword(textFieldPassword.getText());

                // depending on account type, send them to either page
                switch (account.getAccountType()) {
                    case "Parent":
                        LoginUtils.nextSetupPage(event, getClass(), "/account/setup-parent.fxml", account);
                        break;
                    case "Tutor":
                        LoginUtils.nextSetupPage(event, getClass(), "/account/setup-tutor-1.fxml", account);
                        break;
                }
            }
        } else {
            errorMessage.setText("Passwords do not match");
            textFieldPassword.setStyle("-fx-border-color: #e80f0f; -fx-border-radius: 5");
            textFieldConfirmPassword.setStyle("-fx-border-color: #e80f0f; -fx-border-radius: 5");
        }
    }

    @FXML
    void checkFields(KeyEvent event) {
        if (!textFieldUsername.getText().isBlank() && !textFieldEmail.getText().isBlank() &&
                !textFieldPassword.getText().isBlank() && !textFieldConfirmPassword.getText().isBlank()) {
            btnNext.setDisable(false);
        } else {
            btnNext.setDisable(true);
        }
    }
    private void populateFields() {
        textFieldUsername.setText(account.getUsername());
        textFieldEmail.setText(account.getEmail());
        textFieldPassword.setText(account.getPassword());
        textFieldConfirmPassword.setText(account.getPassword());
    }
    @Override
    public void transferAccountDetails(Account account) {
        this.account = account;
    }

}
