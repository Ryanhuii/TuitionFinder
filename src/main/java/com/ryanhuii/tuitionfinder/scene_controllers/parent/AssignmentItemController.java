package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class AssignmentItemController {

    private Assignment assignment;
    private Tutor tutor;
    boolean onGoingAssignment = false;

    @FXML
    private Label txtButtonTitle;
    @FXML
    private HBox hBoxStatus;
    @FXML
    private ImageView btnDelete;
    @FXML
    private HBox btnViewPendingTutors;
    @FXML
    private HBox hBoxIncomingRequest;
    @FXML
    private Label txtAvailability;
    @FXML
    private Label txtDuration;
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
    private Label txtStatus;
    @FXML
    private Label txtSubject;
    @FXML
    private Label txtHiddenAssignmentId; // this is so stupid

    @FXML
    void onDeleteClicked(MouseEvent event) {
        // delete this assignment
//        assignmentService.deleteAssignment(assignment);
//        ParentUtils.setParent(parentService.deleteAssignmentFromAssignmentList(assignment));
//        ParentUtils.switchScene("my-assignments.fxml",event,getClass());

        ParentUtils.deleteAssignment(event,getClass(),assignment);
    }

    @FXML
    void onViewPendingTutorsClick(MouseEvent event) {
        //System.out.println("viewing the tutors that applied to this assignment");
        if (!onGoingAssignment) ParentUtils.viewPendingTutors(assignment,event,getClass());
        else ParentUtils.displayTutor(event,getClass(),tutor);
        // if the assignment is ongoing, then can just view the tutor
    }

    void updateAssignmentDetailsDisplay() {
        txtSubject.setText(assignment.getSubject());
        txtStatus.setText(assignment.getStatus());
        txtLevel.setText(assignment.getLevel());
        txtFrequency.setText(assignment.getFrequency() + " times a week");
        txtDuration.setText(assignment.getDuration() + " months");
        txtRates.setText(assignment.getRates());
        txtParentNote.setText(assignment.getParentNote());
        txtAvailability.setText(assignment.getAvailability().toString());

        hBoxIncomingRequest.setVisible(!assignment.getAssignmentApplications().isEmpty());

        txtHiddenAssignmentId.setText(assignment.getAssignment_id());

        // update the colour of the status tag
        if (assignment.getStatus().equals("Ongoing")) {
            onGoingAssignment = true;
            assignmentIsOngoing();
        }
    }

    private void assignmentIsOngoing() {
        // if the assignment is ongoing, i want to make the "incoming tutor requests" tag invisible
        // i also want to disable the "view pending tutors" button
        if (onGoingAssignment) {
            hBoxStatus.setStyle("-fx-background-color: #68d973;-fx-background-radius: 4");
            txtButtonTitle.setText("View Tutor");
            //btnViewPendingTutors.setVisible(false);
            hBoxIncomingRequest.setVisible(false);
        }
    }

    void transferAssignmentDetails(Assignment assignment, Tutor tutor) {
        this.assignment = assignment;
        this.tutor = tutor;
        updateAssignmentDetailsDisplay();
    }

}
