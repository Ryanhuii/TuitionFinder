package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.classes.Account;
import com.ryanhuii.tuitionfinder.classes.Tutor;
import com.ryanhuii.tuitionfinder.tools.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.tools.TuitionFinderTools;
import com.ryanhuii.tuitionfinder.tools.TutorDetailsUpdater;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SetupTutor1Controller implements AccountDetailsUpdater , TutorDetailsUpdater {
    Account account;
    Tutor tutor = new Tutor();
    String[] genders = {"Male", "Female", "Other"};
    String selectedGender = "";

    @FXML
    private Button btnBack;
    @FXML
    private Button btnCheese;
    @FXML
    private Button btnNext;
    @FXML
    private ComboBox<String> choiceGender;
    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtFullName;
    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        // Removes the autofocus
        Platform.runLater( () -> vBoxFocus.requestFocus() );
        TuitionFinderTools.setUpBackButton(btnBack,btnCheese,getClass());

        // setup my combo box for gender
        choiceGender.setItems(FXCollections.observableArrayList(genders));
        choiceGender.setValue(null);
        choiceGender.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                selectedGender = genders[t1.intValue()];
                System.out.println(selectedGender);
                setNextButton();
            }
        });

        // force the age field to be numeric only
        txtAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtAge.setText(newValue.replaceAll("[^\\d]", ""));
                }
                // Limit to 2 characters
                if (newValue.length() > 2) {
                    txtAge.setText(newValue.substring(0, 2));
                }
                setNextButton();
            }
        });

        // Force the name field to be text only
        txtFullName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[a-zA-Z ]*")) {
                    txtFullName.setText(newValue.replaceAll("[^a-zA-Z ]", ""));
                }
            }
        });

        Platform.runLater(() ->
        {
            if (tutor.getName() != null) {
                populateFields();
                btnNext.setDisable(false);
            }
        });

        // end of initialize()
    }

    private void populateFields() {
        txtFullName.setText(tutor.getName());
        txtAge.setText(String.valueOf(tutor.getAge()));
        choiceGender.setValue(tutor.getGender());
    }

    @FXML
    void checkFields(KeyEvent event) {
        setNextButton();
    }

    private void setNextButton() {
        btnNext.setDisable(txtFullName.getText().isBlank() || txtAge.getText().isBlank() || selectedGender.isEmpty());
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        // Proper UX design - saving previous data so user doesn't have to start all over again
        TuitionFinderTools.nextSetupPage(event,getClass(),"/account/account-details.fxml",account);
    }

    @FXML
    void onNextClicked(MouseEvent event) {
        // Input all good. Will create new tutor class object
        // Move on to part 2 of tutor account setup

        tutor.setUid(account.getUid()); // same UID
        tutor.setName(txtFullName.getText());
        tutor.setGender(selectedGender);
        tutor.setAge(Integer.parseInt(txtAge.getText()));
//        System.out.println(tutor.toString());
        TuitionFinderTools.nextTutorSetupPage(event,getClass(),account,tutor);
    }

    @Override
    public void transferAccountDetails(Account account) {
        this.account = account;
    }

    @Override
    public void transferTutorDetails(Tutor tutor) {
        this.tutor = tutor;
    }
}
