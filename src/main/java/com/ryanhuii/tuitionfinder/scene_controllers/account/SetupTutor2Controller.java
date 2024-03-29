package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.utils.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.TutorDetailsUpdater;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetupTutor2Controller implements AccountDetailsUpdater , TutorDetailsUpdater {
    Account account;
    Tutor tutor = new Tutor();
    String selectedTutorType = "";
    List<String> selectedSubjects;
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

    final ObservableList<String> subjectsList = FXCollections.observableArrayList(
            "English",
            "Chinese",
            "A Math",
            "E Math",
            "Math (Lower-Sec)",
            "Biology",
            "Chemistry",
            "Physics",
            "Science (Lower-Sec)",
            "Computing",
            "D&T",
            "Art",
            "History",
            "Geography",
            "Social Studies"
    );

    public void initialize() {
        // Removes the autofocus
        Platform.runLater( () -> vBoxFocus.requestFocus() );
        LoginUtils.setUpBackButton(btnBack,btnCheese,getClass());

        // setup my combo box for gender
        choiceTutorType.setItems(FXCollections.observableArrayList(tutorTypes));
        choiceTutorType.setValue(null);
        choiceTutorType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                selectedTutorType = tutorTypes[t1.intValue()];
                //System.out.println(selectedTutorType);
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
        comboBoxSubjects.getItems().setAll(subjectsList);
        comboBoxSubjects.setTitle("What do you teach?");
        comboBoxSubjects.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                //System.out.println(comboBoxSubjects.getCheckModel().getCheckedItems());
                selectedSubjects = comboBoxSubjects.getCheckModel().getCheckedItems();
                setNextButton();
            }
        });

        txtAreaEducation.setWrapText(true);

        // end of initialize
    }

    @FXML
    void checkFields(KeyEvent event) {
        setNextButton();
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        // Proper UX design - saving previous data so user doesn't have to start all over again
        LoginUtils.previousTutorSetupPage(event,getClass(),account,tutor);
    }

    @FXML
    void onNextClicked(MouseEvent event) {
        // You're all set! Pass the completed account and tutor classes to the next page for database object creation
        //System.out.println("Tutor account creation done.");
        tutor.setTutorType(selectedTutorType);
        tutor.setExperience(Integer.parseInt(txtExperience.getText()));
        tutor.setSubjects(selectedSubjects);
        tutor.setEducation(txtAreaEducation.getText());
        //System.out.println(tutor.toString());
        //System.out.println(account.toString());

        LoginUtils.completeTutorSetup(event,getClass(),account,tutor);

    }
    private void setNextButton() {
        btnNext.setDisable(selectedTutorType.isBlank()
                || txtExperience.getText().isBlank() || txtAreaEducation.getText().isEmpty()
                || selectedSubjects.isEmpty());
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
