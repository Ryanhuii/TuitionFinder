package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TutorCardController {
    Tutor tutor;

    @FXML
    private Label txtTutorName;

    @FXML
    private Label txtTutorSubjects;

    @FXML
    private VBox vBoxCardItem;

    @FXML
    void onTutorClick(MouseEvent event) {
        System.out.println("Go to tutor info page");
        ParentUtils.displayTutor(event,getClass(),tutor);
    }

    public void initialize() {}

    public void setCardData(Tutor tutor) {
        this.tutor = tutor;
        updateDisplayDetails();
    }

    private void updateDisplayDetails() {
        txtTutorName.setText(tutor.getName());
        txtTutorSubjects.setText(tutor.getSubjects().toString());
    }
}

