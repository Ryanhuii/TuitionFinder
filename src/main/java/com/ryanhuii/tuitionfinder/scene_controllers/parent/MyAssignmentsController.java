package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class MyAssignmentsController {

    // navigation

    @FXML
    private Label btnFindTutors;

    @FXML
    private Label btnLogout;

    @FXML
    private VBox vBoxFocus;
    // end of navigation components

    @FXML
    private HBox btnAddAssignment;

    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());
    }

    @FXML
    void onAddAssignment(MouseEvent event) {
        System.out.println("Adding Assignment");
        ParentUtils.switchScene("add-assignment.fxml",event,getClass());
    }

    @FXML
    void onBtnFindTutorsClick(MouseEvent event) {
        ParentUtils.switchScene("find-tutors.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }
}
