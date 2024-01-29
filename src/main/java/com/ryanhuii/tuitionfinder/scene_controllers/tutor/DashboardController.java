package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class DashboardController {

    @FXML
    private Label btnDashboard;

    @FXML
    private Label btnFindAssignments;

    @FXML
    private Label btnLogout;

    @FXML
    private VBox vBoxFocus;

    @FXML
    void onDashboardClick(MouseEvent event) {

    }

    @FXML
    void onFindAssignmentsClick(MouseEvent event) {

    }

    @FXML
    void onLogout(MouseEvent event) {

    }

}
