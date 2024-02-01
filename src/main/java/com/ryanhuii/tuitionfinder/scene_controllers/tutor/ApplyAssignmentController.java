package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.service.AssignmentApplicationService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.TagUtils;
import com.ryanhuii.tuitionfinder.utils.TutorUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplyAssignmentController {

    // this form create an AssignmentApplication Object.
    // when it is created, the object's application_id also gets appended to the Assignment's assignmentApplications list variable
    Assignment assignment;
    List<String> lessonSchedule = new ArrayList<>();
    boolean canProceed = false;

    @Autowired
    AssignmentApplicationService service;

    @FXML
    private Button btnBack;
    @FXML
    private Label btnDashboard;
    @FXML
    private Label btnFindAssignments;
    @FXML
    private Label btnLogout;

    @FXML
    private HBox btnConfirm;
    @FXML
    private TextArea textAreaChooseMe;
    @FXML
    private TextField textFieldRate;
    @FXML
    private Label txtAvailability;
    @FXML
    private Label txtFrequency;
    @FXML
    private Label txtRates;
    @FXML
    private Label txtSubject;
    @FXML
    private Label txtTagLevel;
    @FXML
    private Label txtTagSubject;

    @FXML
    private CheckBox cbMonday;
    @FXML
    private CheckBox cbTuesday;
    @FXML
    private CheckBox cbWednesday;
    @FXML
    private CheckBox cbThursday;
    @FXML
    private CheckBox cbFriday;
    @FXML
    private CheckBox cbSaturday;
    @FXML
    private CheckBox cbSunday;

    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());

        // Setting up the appearance of the back button
        double iconDimension = 20;
        ImageView backArrow = new ImageView(new Image(getClass().getResourceAsStream("/images/arrow_back.png")));
        backArrow.setFitHeight(iconDimension);
        backArrow.setFitWidth(iconDimension);
        btnBack.setGraphic(backArrow);
    }

    @FXML
    void validateInputs(KeyEvent event) {
        updateConfirmButton();
    }

    @FXML
    void onApplyClicked(MouseEvent event) {
        //System.out.println(lessonSchedule.toString());
        if (canProceed) {
            AssignmentApplication application = new AssignmentApplication();

            application.setApplication_id(LoginUtils.generateUID());
            application.setTutorID(TutorUtils.getTutor().getUid());
            application.setTutorRate(textFieldRate.getText());
            application.setLessonSchedule(lessonSchedule);
            application.setWhyChooseMe(textAreaChooseMe.getText());

            System.out.println(application.printDetails());
            // todo: upload to database
            service.submitAssignmentApplication(application,assignment);

            System.out.println("Apply clicked okay!");
            TutorUtils.switchScene("dashboard-my-schedule.fxml",event,getClass());
        }
    }
    private void updateConfirmButton() {
        if (!textFieldRate.getText().isBlank() && !textAreaChooseMe.getText().isBlank() && !lessonSchedule.isEmpty()) {
            btnConfirm.setStyle("-fx-background-color: #47B286; -fx-background-radius: 100");
            btnConfirm.setCursor(Cursor.HAND);
            canProceed = true;
        } else {
            btnConfirm.setStyle("-fx-background-color: #8DCAB1; -fx-background-radius: 100");
            btnConfirm.setCursor(Cursor.DEFAULT);
            canProceed = false;
        }
    }

    @FXML
    void updateAvailability(ActionEvent event) {
        // update the list of available days based on checkbox input
        lessonSchedule.clear();

        // Call updateIfSelected method all the days
        updateLessonSchedule(cbMonday.isSelected(), "Monday");
        updateLessonSchedule(cbTuesday.isSelected(), "Tuesday");
        updateLessonSchedule(cbWednesday.isSelected(), "Wednesday");
        updateLessonSchedule(cbThursday.isSelected(), "Thursday");
        updateLessonSchedule(cbFriday.isSelected(), "Friday");
        updateLessonSchedule(cbSaturday.isSelected(), "Saturday");
        updateLessonSchedule(cbSunday.isSelected(), "Sunday");

        // also call the function to update the confirm button
        updateConfirmButton();
    }


    @FXML
    void onBackClicked(ActionEvent event) {
        TutorUtils.viewAssignment(assignment,event,getClass());
    }

    @FXML
    void onDashboardClick(MouseEvent event) {

    }

    @FXML
    void onFindAssignmentsClick(MouseEvent event) {
        TutorUtils.switchScene("find-assignments.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

    public void transferAssignmentDetails(Assignment assignment) {
        System.out.println("Assignment details transferred: ");
        this.assignment = assignment;

        // update the details displayed
        txtSubject.setText(assignment.getSubject());
        txtFrequency.setText(assignment.getFrequency() + " times a week");
        txtRates.setText(assignment.getRates());
        txtAvailability.setText(assignment.getAvailability().toString());

        txtTagLevel.setText(TagUtils.setTagLevel(assignment));
        txtTagSubject.setText(TagUtils.setTagSubject(assignment));

        // code for checkbox enabling
        cbMonday.setDisable(!assignment.getAvailability().contains("Monday"));
        cbTuesday.setDisable(!assignment.getAvailability().contains("Tuesday"));
        cbWednesday.setDisable(!assignment.getAvailability().contains("Wednesday"));
        cbThursday.setDisable(!assignment.getAvailability().contains("Thursday"));
        cbFriday.setDisable(!assignment.getAvailability().contains("Friday"));
        cbSaturday.setDisable(!assignment.getAvailability().contains("Saturday"));
        cbSunday.setDisable(!assignment.getAvailability().contains("Sunday"));
    }

    private void updateLessonSchedule(boolean isSelected, String day) {
        if (isSelected) {
            lessonSchedule.add(day);
        }
    }
}
