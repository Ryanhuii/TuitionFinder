package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.classes.Account;
import com.ryanhuii.tuitionfinder.classes.Tutor;
import com.ryanhuii.tuitionfinder.tools.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.tools.TuitionFinderTools;
import com.ryanhuii.tuitionfinder.tools.TutorDetailsUpdater;
import com.sun.prism.shader.Solid_TextureRGB_AlphaTest_Loader;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.springframework.stereotype.Component;

@Component
public class SetupTutor2Controller implements AccountDetailsUpdater , TutorDetailsUpdater {
    Account account;
    Tutor tutor = new Tutor();
    String selectedTutorType = "";
    String[] tutorTypes = {"Part-Time", "Full-Time", "Ex-MOE"};

    @FXML
    private Button btnBack;
    @FXML
    private Button btnCheese;
    @FXML
    private Button btnNext;
    @FXML
    private ComboBox<String> choiceTutorType;
    @FXML
    private TextArea txtAreaEducation;
    @FXML
    private TextField txtExperience;
    @FXML
    private VBox vBoxFocus;
    @FXML
    private CheckComboBox<String> comboBoxSubjects;
    //<CheckComboBox fx:id="comboBoxSubjects" prefWidth="350.0"/>

    public void initialize() {
        // Removes the autofocus
        Platform.runLater( () -> vBoxFocus.requestFocus() );
        TuitionFinderTools.setUpBackButton(btnBack,btnCheese,getClass());

        // setup my combo box for gender
        choiceTutorType.setItems(FXCollections.observableArrayList(tutorTypes));
        choiceTutorType.setValue(null);
        choiceTutorType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                selectedTutorType = tutorTypes[t1.intValue()];
                System.out.println(selectedTutorType);
                setNextButton();
            }
        });

        // force the experience field to be numeric only
        txtExperience.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtExperience.setText(newValue.replaceAll("[^\\d]", ""));
                }
                // Limit to 2 characters
                if (newValue.length() > 2) {
                    txtExperience.setText(newValue.substring(0, 2));
                }
            }
        });

        // code for setting up the check combo box for the subjects
        ObservableList<String> programmingLanguages = FXCollections.observableArrayList(
                "Java",
                "Cpp",
                "Python",
                "C#",
                "Kotlin"
        );
        comboBoxSubjects.getItems().setAll(programmingLanguages);
        comboBoxSubjects.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                System.out.println(comboBoxSubjects.getCheckModel().getCheckedItems());
            }
        });
        comboBoxSubjects.setTitle("What do you teach?");
        System.out.println(comboBoxSubjects.getTitle());
        System.out.println(comboBoxSubjects.getItems().toString());

        // end of initialize
    }

    @FXML
    void checkFields(KeyEvent event) {
        btnNext.setDisable(true);
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        // Proper UX design - saving previous data so user doesn't have to start all over again
        TuitionFinderTools.previousTutorSetupPage(event,getClass(),account,tutor);
    }

    @FXML
    void onNextClicked(MouseEvent event) {
        // You're all set! Pass the completed account and tutor classes to the next page for database object creation
        setNextButton();
    }
    private void setNextButton() {
        btnNext.setDisable(selectedTutorType.isBlank()
                || txtExperience.getText().isBlank() || txtAreaEducation.getText().isEmpty());
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
