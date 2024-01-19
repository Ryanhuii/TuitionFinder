package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.utils.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SetupParentController implements AccountDetailsUpdater {
    Account account;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCheese;

    @FXML
    private Button btnNext;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField txtBlockNumber;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtStreetName;

    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        // Removes the autofocus
        Platform.runLater( () -> vBoxFocus.requestFocus() );
        LoginUtils.setUpBackButton(btnBack,btnCheese,getClass());
    }
    @FXML
    void checkFields(KeyEvent event) {
        btnNext.setDisable(txtPostalCode.getText().isBlank() || txtStreetName.getText().isBlank() ||
                txtBlockNumber.getText().isBlank());
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        // Proper UX design - saving previous data so user doesn't have to start all over again
        LoginUtils.nextSetupPage(event,getClass(),"/account/account-details.fxml",account);
    }

    @FXML
    void onNextClicked(MouseEvent event) {
        if (!btnNext.isDisabled()) {
            Parent parentAccount = createNewParentAccount(account.getUid(),txtPostalCode.getText(),txtStreetName.getText(),txtBlockNumber.getText());
            LoginUtils.completeParentSetup(event,getClass(),account,parentAccount);
        } else {
            System.out.println("this shouldn't happen!");
        }
    }

    private Parent createNewParentAccount(String uid, String postalCode, String streetName, String blockNumber) {
        return new Parent(uid,postalCode,streetName,blockNumber);
    }

    @Override
    public void transferAccountDetails(Account account) {
        this.account = account;
    }
}
