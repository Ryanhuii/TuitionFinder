package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.TutorUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class DashboardScheduleController {

    @FXML
    private Label btnDashboard;

    @FXML
    private Label btnFindAssignments;

    @FXML
    private Label btnLogout;

    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater(() -> vBoxFocus.requestFocus());
    }

    @FXML
    void onPendingApplicationsClicked(MouseEvent event) {
        TutorUtils.switchScene("dashboard-pending-applications.fxml",event,getClass());
    }

    @FXML
    void onDashboardClick(MouseEvent event) {
        TutorUtils.switchScene("dashboard-my-schedule.fxml",event,getClass());
    }

    @FXML
    void onFindAssignmentsClick(MouseEvent event) {
        TutorUtils.switchScene("find-assignments.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

}
