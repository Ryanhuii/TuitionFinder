package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.utils.TagUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    void onBackClicked(ActionEvent event) {

    }

    @FXML
    void onDashboardClick(MouseEvent event) {

    }

    @FXML
    void onFindAssignmentsClick(MouseEvent event) {

    }

    @FXML
    void onLogout(MouseEvent event) {

    }

    public void transferAssignmentDetails(AssignmentApplication assignmentApplication, Assignment assignment) {
        // System.out.println("Transferring account details.");
        this.assignment = assignment;
        this.application = assignmentApplication;

        txtSubject.setText(assignment.getSubject() + application.getWhyChooseMe());
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
    }
}
