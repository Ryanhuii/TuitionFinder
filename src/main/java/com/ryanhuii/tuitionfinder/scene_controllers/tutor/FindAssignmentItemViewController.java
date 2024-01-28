package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.utils.TutorUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class FindAssignmentItemViewController {

    Assignment assignment;

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
    private Label txtSubject;

    @FXML
    void onViewAssignmentClick(MouseEvent event) {
        //System.out.println("Taking you to view Assignment page.");
        TutorUtils.viewAssignment(assignment,event,getClass());
    }

    public void transferAssignmentDetails(Assignment assignment) {
        this.assignment = assignment;
        updateAssignmentDetailsDisplay();
    }

    void updateAssignmentDetailsDisplay() {
        txtSubject.setText(assignment.getSubject());
        txtLevel.setText(assignment.getLevel());
        txtFrequency.setText(assignment.getFrequency() + " times a week");
        txtDuration.setText(assignment.getDuration() + " months");
        txtRates.setText(assignment.getRates());
        txtGender.setText(assignment.getGender());
        txtParentNote.setText(assignment.getParentNote());
        String availability = "";
        for (int i=0;i<assignment.getAvailability().size();i++) {
            availability += assignment.getAvailability().get(i);
            if (i != assignment.getAvailability().size()-1) availability += ", ";
        }
        txtAvailability.setText(assignment.getAvailability().toString());
    }
}
