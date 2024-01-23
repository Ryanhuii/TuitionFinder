package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class AddAssignmentController {

    @FXML
    private Label btnAssignments;

    @FXML
    private Label btnFindTutors;

    @FXML
    private Label btnLogout;

    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());


    }

    @FXML
    void onAssignmentsClicked(MouseEvent event) {
        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }

    @FXML
    void onFindTutorsClicked(MouseEvent event) {
        ParentUtils.switchScene("find-tutors.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

}
