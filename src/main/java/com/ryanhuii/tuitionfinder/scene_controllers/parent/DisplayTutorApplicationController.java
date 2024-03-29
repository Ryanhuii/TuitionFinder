package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.AssignmentApplicationService;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayTutorApplicationController {

    Tutor tutor;
    Assignment assignment;
    AssignmentApplication application;

    @Autowired
    AssignmentService assignmentService;
    @Autowired
    AssignmentApplicationService assignmentApplicationService;

    @FXML
    private HBox btnConfirm;
    @FXML
    private Label txtRate;
    @FXML
    private Label txtSchedule;
    @FXML
    private Label txtWhyChooseMe;
    @FXML
    private Label btnAssignments;
    @FXML
    private Button btnBack;
    @FXML
    private Label btnFindTutors;
    @FXML
    private Label btnLogout;
    @FXML
    private Label txtEducation;
    @FXML
    private Label txtTutorAge;
    @FXML
    private Label txtTutorExperience;
    @FXML
    private Label txtTutorGender;
    @FXML
    private Label txtTutorName;
    @FXML
    private Label txtTutorSubjects;
    @FXML
    private Label txtTutorType;
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

        // set up the look of the confirm button
        btnConfirm.setStyle("-fx-background-color: #47B286; -fx-background-radius: 100");
        btnConfirm.setCursor(Cursor.HAND);
    }

    @FXML
    void onConfirmClicked(MouseEvent event) {
        // confirm that this tutor will be the one teaching your child!
        // 1. Update the Assignment object to reflect the chosen tutor's details
        // 2. Update the assignment application object's status to "Accepted", so that it appears on the
        //  tutor's calendar
        System.out.println("Confirming tutor!");
        System.out.println("Tutor: " + tutor.getName());
        System.out.println("Application: " + application.getApplication_id());
        System.out.println("Assignment: " + assignment.getFormDetails());

        // update the assignment object
        assignment.setStatus("Ongoing");
        assignment.setTutorUID(tutor.getUid());
        assignment.setLessonSchedule(application.getLessonSchedule());
        assignment.setRate(application.getTutorRate());
        assignmentService.updateAssignment(assignment);

        // update the assignment application's status
        application.setApplicationStatus("Accepted");
        assignmentApplicationService.updateApplication(application);

        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

    public void transferTutorAndApplicationDetails(Tutor tutor, Assignment assignment, AssignmentApplication application) {
        this.tutor = tutor;
        this.assignment = assignment;
        this.application = application;
        updateDataDisplay();
    }

    private void updateDataDisplay() {
        txtTutorName.setText(tutor.getName());
        txtTutorSubjects.setText(tutor.getSubjects().toString());
        txtTutorType.setText(tutor.getTutorType());
        txtTutorExperience.setText(tutor.getExperience() + " years");
        txtTutorGender.setText(tutor.getGender());
        txtTutorAge.setText(tutor.getAge() + " years old");
        txtEducation.setText(tutor.getEducation());

        txtSchedule.setText(application.getLessonSchedule().toString());
        txtRate.setText(application.getTutorRate());
        txtWhyChooseMe.setText(application.getWhyChooseMe());
    }
}
