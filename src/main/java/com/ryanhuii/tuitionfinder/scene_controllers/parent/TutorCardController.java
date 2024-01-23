package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import atlantafx.base.controls.Popover;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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

// this just some extra stuff. not important for now.
//class PopoverLink extends Hyperlink {
//    public PopoverLink() {
//        super();
//
//        TextFlow textFlow = new TextFlow(new Text("Hello "));
//
//        Popover popover = new Popover(textFlow);
//        popover.setHeaderAlwaysVisible(false);
//        popover.setArrowLocation(Popover.ArrowLocation.BOTTOM_RIGHT);
//
//        setText("yes hello testing");
//    }
//}

