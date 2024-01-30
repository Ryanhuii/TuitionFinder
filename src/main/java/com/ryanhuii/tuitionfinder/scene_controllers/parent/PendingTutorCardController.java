package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PendingTutorCardController {

    Tutor tutor;
    AssignmentApplication application;

    @FXML
    private Label txtTutorName;

    @FXML
    private Label txtTutorSubjects;

    @FXML
    private VBox vBoxCardItem;

    @FXML
    void onTutorClick(MouseEvent event) {
        System.out.println("DISPLAY THE TUTOR APPLICATION");
        ParentUtils.displayTutorApplication(event,getClass(),tutor,application);
    }

    public void setCardData(Tutor tutor, AssignmentApplication application) {
        this.tutor = tutor;
        this.application = application;
        updateDisplayDetails();
    }

    private void updateDisplayDetails() {
        txtTutorName.setText(tutor.getName());
        if (tutor.getSubjects().size() > 2) {
            // if there are more than 2 subjects, truncate the string
            txtTutorSubjects.setText(tutor.getSubjects().get(0) + ", " + tutor.getSubjects().get(1) + "...");
        } else {
            String subjectDisplay = "";
            for (int i = 0; i < tutor.getSubjects().size(); i++) {
                subjectDisplay += tutor.getSubjects().get(i);
                if (i != tutor.getSubjects().size()-1) subjectDisplay += ", ";
            }
            txtTutorSubjects.setText(subjectDisplay);
        }
    }
}

