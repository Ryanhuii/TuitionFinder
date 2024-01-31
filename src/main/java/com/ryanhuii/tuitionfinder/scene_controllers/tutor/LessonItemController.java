package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LessonItemController {

    Assignment assignment;

    @FXML
    private Label txtDuration;

    @FXML
    private Label txtFrequency;

    @FXML
    private Label txtGender;

    @FXML
    private Label txtLessonSchedule;

    @FXML
    private Label txtLevel;

    @FXML
    private Label txtMyRate;

    @FXML
    private Label txtParentNote;

    @FXML
    private Label txtSubject;

    public void transferLessonDetails(Assignment lesson) {
        this.assignment = lesson;
        txtSubject.setText(assignment.getSubject());
        txtLevel.setText(assignment.getLevel());
        txtFrequency.setText(assignment.getFrequency() + " times a week");
        txtDuration.setText(assignment.getDuration() + " months");
        txtMyRate.setText(assignment.getRate());
        txtGender.setText(assignment.getGender());
        txtParentNote.setText(assignment.getParentNote());
        txtLessonSchedule.setText(assignment.getLessonSchedule().toString());
    }
}
