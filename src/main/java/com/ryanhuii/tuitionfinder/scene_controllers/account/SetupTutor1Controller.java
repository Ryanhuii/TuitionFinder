package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.classes.Account;
import com.ryanhuii.tuitionfinder.tools.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.tools.TuitionFinderTools;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SetupTutor1Controller implements AccountDetailsUpdater {
    Account account;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCheese;

    @FXML
    private Button btnNext;

    @FXML
    private ChoiceBox<String> choiceGender;

    @FXML
    private Label errorMessage;

    @FXML
    private Spinner<Integer> spinnerAge;

    @FXML
    private TextField txtFullName;

    @FXML
    private VBox vBoxFocus;
    public void initialize() {
        // Removes the autofocus
        Platform.runLater( () -> vBoxFocus.requestFocus() );
        TuitionFinderTools.setUpBackButton(btnBack,btnCheese,getClass());

        // Set up the Spinner with a value factory
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 150, 18);
        spinnerAge.setValueFactory(valueFactory);

        choiceGender = new ChoiceBox<>(
                FXCollections.observableArrayList("Male", "Female", "Other")
        );

        // Set a default selection
        choiceGender.setValue("Other");
    }

    @FXML
    void checkFields(KeyEvent event) {

    }

    @FXML
    void onBackClicked(ActionEvent event) {
        // Proper UX design - saving previous data so user doesn't have to start all over again
        TuitionFinderTools.nextSetupPage(event,getClass(),"/account/account-details.fxml",account);
    }

    @FXML
    void onNextClicked(MouseEvent event) {

    }

    @Override
    public void transferAccountDetails(Account account) {
        this.account = account;
    }

}
