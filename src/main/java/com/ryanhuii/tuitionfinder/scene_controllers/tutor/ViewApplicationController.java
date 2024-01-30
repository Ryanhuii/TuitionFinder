package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.utils.TagUtils;
import com.ryanhuii.tuitionfinder.utils.TutorUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewApplicationController {

    Assignment assignment;
    AssignmentApplication application;
    @Autowired
    ParentService parentService;

    @FXML
    private Label txtRate;
    @FXML
    private Label txtLessonSchedule;
    @FXML
    private Label txtWhyChooseMe;

    @FXML
    private Button btnBack;
    @FXML
    private Label btnDashboard;
    @FXML
    private Label btnFindAssignments;
    @FXML
    private Label btnLogout;
    @FXML
    private Label txtAddress;
    @FXML
    private Label txtAvailability;
    @FXML
    private Label txtFrequency;
    @FXML
    private Label txtGender;
    @FXML
    private Label txtLevel;
    @FXML
    private Label txtParentNote;
    @FXML
    private Label txtRates;
    @FXML
    private Label txtSubject;
    @FXML
    private Label txtTagLevel;
    @FXML
    private Label txtTagSubject;
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
    void onBackClicked(ActionEvent event) {
        TutorUtils.switchScene("dashboard-pending-applications.fxml",event,getClass());
    }
    @FXML
    void onDashboardClick(MouseEvent event) {
        TutorUtils.switchScene("dashboard-my-schedule.fxml",event,getClass());
    }
    @FXML
    void onFindAssignmentsClick(MouseEvent event) {
        TutorUtils.switchScene("find-assignments.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {

    }

    public void transferAssignmentDetails(AssignmentApplication myApplication, Assignment assignment) {
        // System.out.println("Transferring account details.");
        this.assignment = assignment;
        this.application = myApplication;

        txtSubject.setText(assignment.getSubject());
        txtLevel.setText(assignment.getLevel());
        txtFrequency.setText(assignment.getFrequency() + " times a week");
        txtGender.setText(assignment.getGender());
        txtRates.setText(assignment.getRates());
        txtAddress.setText("will contact parent database");
        txtParentNote.setText(assignment.getParentNote());
        txtAvailability.setText(assignment.getAvailability().toString());

        txtTagLevel.setText(TagUtils.setTagLevel(assignment));
        txtTagSubject.setText(TagUtils.setTagSubject(assignment));

        txtAddress.setText(parentService.getAddressFromAssignmentUid(assignment.getAssignment_id()));

        txtLessonSchedule.setText(myApplication.getLessonSchedule().toString());
        txtRate.setText(myApplication.getTutorRate());
        txtWhyChooseMe.setText(myApplication.getWhyChooseMe());
    }
}
