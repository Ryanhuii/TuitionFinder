package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import atlantafx.base.util.IntegerStringConverter;
import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddAssignmentController {

    @Autowired
    ParentService parentService;
    @Autowired
    AssignmentService assignmentService;
    Assignment assignment = new Assignment();

    @FXML
    private ComboBox<String> comboBoxGender;
    String[] genders = {"Male", "Female", "Other"};

    @FXML
    private ComboBox<String> comboBoxSubject;
    String[] subjects = {"English",
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
            "Social Studies"};

    @FXML
    private ComboBox<String> comboBoxLevel;
    String[] levels = {
            "Primary 1",
            "Primary 2",
            "Primary 3",
            "Primary 4",
            "Primary 5",
            "Primary 6",
            "Secondary 1",
            "Secondary 2",
            "Secondary 3",
            "Secondary 4",
            "Secondary 5",
            "Polytechnic"};

    @FXML
    private HBox hBoxSpinners;

    @FXML
    private TextArea txtParentNote;

    @FXML
    private TextArea txtRates;

    // page setup stuff
    @FXML
    private Label btnAssignments;
    @FXML
    private Label btnFindTutors;
    @FXML
    private Label btnLogout;
    @FXML
    private VBox vBoxFocus;
    @FXML
    private HBox hBoxForComboBox;
    @FXML
    private Button btnSubmit;
    // end of page setup stuff

    // my variables for the input form
    String selectedSubject = "";
    String selectedLevel = "";
    int frequency = 1, duration = 1;
    String selectedGender = "";
    String rates = "";
    List<String> availability = new ArrayList<>(); // todo: set this up and add to refreshBtn
    String parentNote = "";

    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());

        // disable the submit button initially
        btnSubmit.setDisable(true);

        // setup my combo box for assignment subject
        comboBoxSubject.setItems(FXCollections.observableArrayList(subjects));
        comboBoxSubject.setValue(null);
        comboBoxSubject.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                // System.out.println(selectedSubject);
                selectedSubject = subjects[t1.intValue()];
                refreshBtnEnable();
            }
        });

        // combo box for level
        comboBoxLevel.setItems(FXCollections.observableArrayList(levels));
        comboBoxLevel.setValue(null);
        comboBoxLevel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                // System.out.println(selectedSubject);
                selectedLevel = levels[t1.intValue()];
                refreshBtnEnable();
            }
        });

        // setup my combo box for gender
        comboBoxGender.setPromptText("Gender of child");
        comboBoxGender.setItems(FXCollections.observableArrayList(genders));
        comboBoxGender.setValue(null);
        comboBoxGender.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                // System.out.println(selectedGender);
                selectedGender = genders[t1.intValue()];
                refreshBtnEnable();
            }
        });

        // Setting up my entire hBox - 2 spinners and 2 labels
        // The hBox is 340 pixels wide. Need to make sure that all 4 element widths add up to 340

        // daysSpinner
        var daysSpinner = new Spinner<Integer>(1, 7, 1);
        IntegerStringConverter.createFor(daysSpinner);
        daysSpinner.setEditable(true);
        daysSpinner.setPrefWidth(120);
        daysSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                //System.out.println("days is " + t1);
                frequency = t1;
            }
        });
        var daysText = new Text("days a week, for");
        var durationSpinner = new Spinner<Integer>(1, 12, 1);
        IntegerStringConverter.createFor(durationSpinner);
        durationSpinner.setEditable(true);
        durationSpinner.setPrefWidth(120);
        durationSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                //System.out.println("duration is " + t1);
                duration = t1;
            }
        });
        var durationText = new Text("months.");
        hBoxSpinners.getChildren().addAll(daysSpinner,daysText,durationSpinner,durationText);

        // create a CheckComboBox for days available
        CheckComboBox<String> availabilityComboBox = new CheckComboBox<>();
        availabilityComboBox.setMinWidth(150);
        availabilityComboBox.setPrefWidth(150);
        availabilityComboBox.setMaxWidth(150);
        availabilityComboBox.setTitle("Availability");
        availabilityComboBox.getItems().setAll("Monday","Tuesday","Wednesday",
                "Thursday","Friday","Saturday","Sunday");
        availabilityComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                availability = availabilityComboBox.getCheckModel().getCheckedItems();
                refreshBtnEnable();

                if (!availability.isEmpty()) availabilityComboBox.setTitle("Availability (" + availability.size() + ")");
                else availabilityComboBox.setTitle("Availability");
            }
        });
        hBoxForComboBox.getChildren().add(availabilityComboBox);

    } // end of initialize()

    @FXML
    void onSubmit(ActionEvent event) {
        // Fill in the fields of the assignment object
        assignment.setSubject(selectedSubject);
        assignment.setLevel(selectedLevel);
        assignment.setFrequency(frequency);
        assignment.setDuration(duration);
        assignment.setGender(selectedGender);
        assignment.setRates(rates);
        assignment.setAvailability(availability);
        assignment.setParentNote(parentNote);

        System.out.println(assignment.getFormDetails());

        // The rest of the fields need to be filled in with default values
        // Also generate a new uid for this assignment
        assignment.setAssignment_id(LoginUtils.generateUID());
        assignment.setStatus("Pending");
        assignment.setLessonSchedule(new ArrayList<>());
        assignment.setAssignmentApplications(new ArrayList<>());
        assignment.setTutorUID("");
        assignment.setRate("Unrated");

        // Submit the Assignment, i.e. upload it to the database
        Assignment result = assignmentService.createAssignment(assignment);
        if (result != null) System.out.println("Assignment successfully created!");

        // Additionally, add this assignment to the parent list via service database function, and update ParentUtils object.
        // Parent parentResult = parentService.addAssignmentToParentAssignmentList(assignment.getAssignment_id(), ParentUtils.getParent());
        ParentUtils.setParent(parentService.addAssignmentToParentAssignmentList(assignment.getAssignment_id(), ParentUtils.getParent()));

        // maybe display a UI prompt to say hey it's successful

        // redirect me back to "My Assignments" page
        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }

    @FXML
    void onParentNoteChange(KeyEvent event) {
        parentNote = txtParentNote.getText();
        refreshBtnEnable();
    }

    @FXML
    void onTxtRatesChange(KeyEvent event) {
        rates = txtRates.getText();
        refreshBtnEnable();
    }

    private void refreshBtnEnable() {
        btnSubmit.setDisable(selectedSubject.isBlank()
                || selectedLevel.isBlank()
                || selectedGender.isBlank()
                || rates.isBlank()
                || parentNote.isBlank()
                || availability.isEmpty()
        );
    }

    @FXML
    void onAssignmentsClicked(MouseEvent event) {
        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }

    @FXML
    void onFindTutorsClicked(MouseEvent event) {
        ParentUtils.switchScene("find-tutors.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

}
