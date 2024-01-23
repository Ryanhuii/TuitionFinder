package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Tutor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TutorCardController {

    @FXML
    private Label txtTutorName;

    @FXML
    private Label txtTutorSubjects;

    public void setCardData(Tutor tutor) {
        txtTutorName.setText(tutor.getName());
        txtTutorSubjects.setText(tutor.getSubjects().toString());
    }

}
