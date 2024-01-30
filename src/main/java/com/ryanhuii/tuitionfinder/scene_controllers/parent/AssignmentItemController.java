package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.Parent;
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
        ParentUtils.viewPendingTutors(assignment,event,getClass());
    }

    void updateAssignmentDetailsDisplay() {
        txtSubject.setText(assignment.getSubject());
        txtStatus.setText(assignment.getStatus());
        txtLevel.setText(assignment.getLevel());
        txtFrequency.setText(assignment.getFrequency() + " times a week");
        txtDuration.setText(assignment.getDuration() + " months");
        txtRates.setText(assignment.getRates());
        txtParentNote.setText(assignment.getParentNote());
        String availability = "";
        for (int i=0;i<assignment.getAvailability().size();i++) {
            availability += assignment.getAvailability().get(i);
            if (i != assignment.getAvailability().size()-1) availability += ", ";
        }
        txtAvailability.setText(assignment.getAvailability().toString());

        hBoxIncomingRequest.setVisible(!assignment.getAssignmentApplications().isEmpty());

        txtHiddenAssignmentId.setText(assignment.getAssignment_id());
    }

    void transferAssignmentDetails(Assignment assignment) {
        this.assignment = assignment;
        updateAssignmentDetailsDisplay();
    }

}
