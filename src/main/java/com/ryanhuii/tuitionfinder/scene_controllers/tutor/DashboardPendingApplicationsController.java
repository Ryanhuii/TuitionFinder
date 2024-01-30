package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.service.AssignmentApplicationService;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.TutorUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DashboardPendingApplicationsController {

    @Autowired
    AssignmentApplicationService applicationService;
    @Autowired
    AssignmentService assignmentService;

    @FXML
    private Label titlePendingApplications;
    @FXML
    private VBox vBoxApplicationList;
    @FXML
    private Label btnDashboard;
    @FXML
    private Label btnFindAssignments;
    @FXML
    private Label btnLogout;
    @FXML
    private VBox vBoxFocus;

    // todo: when the parent deletes an assignment, I have to go delete the AssignmentApplication objects from
    // the database as well.
    public void initialize() {
        Platform.runLater(() -> vBoxFocus.requestFocus());
        // get all my applications, as well as the corresponding assignment it is attached to
        List<AssignmentApplication> pendingApplications = applicationService.getPendingApplications(TutorUtils.getTutor().getUid());
        List<Assignment> assignmentsAppliedTo = assignmentService.getAssignmentsAppliedTo(pendingApplications);
        titlePendingApplications.setText("Pending Applications (" + pendingApplications.size() + ")");
        refreshAssignmentList(pendingApplications,assignmentsAppliedTo);
    }

    private void refreshAssignmentList(List<AssignmentApplication> pendingApplications, List<Assignment> assignmentsAppliedTo) {
        vBoxApplicationList.getChildren().clear();
        try {
            for (int i=0;i< pendingApplications.size();i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/pages/tutor/assignment-application-item.fxml"));
                VBox itemVBox = loader.load();

                AssignmentApplicationItemController controller = loader.getController();
                controller.transferAssignmentAndApplicationDetails(pendingApplications.get(i),assignmentsAppliedTo.get(i));

                vBoxApplicationList.getChildren().add(itemVBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onMyScheduleClicked(MouseEvent event) {
        TutorUtils.switchScene("dashboard-my-schedule.fxml",event,getClass());
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
