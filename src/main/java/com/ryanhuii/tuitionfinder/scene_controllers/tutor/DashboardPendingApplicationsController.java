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
    private Label btnLessons;
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
        List<AssignmentApplication> allMyPendingApplications = applicationService.getPendingApplications(TutorUtils.getTutor().getUid());
        List<Assignment> assignmentsAppliedTo = assignmentService.getAssignmentsAppliedTo(allMyPendingApplications);
        titlePendingApplications.setText("Pending (" + assignmentsAppliedTo.size() + ")");
        TutorUtils.setPendingApplicationsCount(assignmentsAppliedTo.size());
        System.out.println("Assignments that I applied to that still exist: " + assignmentsAppliedTo.size());
        refreshAssignmentList(allMyPendingApplications,assignmentsAppliedTo);

        if (TutorUtils.getMyLessonsCount() > 0) btnLessons.setText("Lessons (" + TutorUtils.getMyLessonsCount() + ")");
    }

    // this line of code's really important.
    // if an assignment is deleted, the repository might not return an assignment object that contains whatever UID.
    // therefore a safe measure would be to instead make the item list depend on the assignments available,
    // and not depend on the number of applications, as some of these applications might no longer be associated
    // with an assignment
    private void refreshAssignmentList(List<AssignmentApplication> pendingApplications, List<Assignment> assignmentsAppliedTo) {
        vBoxApplicationList.getChildren().clear();
        // for each assignment, I want to inject it's corresponding application
        try {
            for (Assignment assignment : assignmentsAppliedTo) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/pages/tutor/assignment-application-item.fxml"));
                VBox itemVBox = loader.load();

                AssignmentApplication linkedApplication = new AssignmentApplication();
                for (AssignmentApplication application : pendingApplications) {
                    if (assignment.getAssignmentApplications().contains(application.getApplication_id())) {
                        System.out.println("Linked an application to this assignment: " + application.getApplication_id());
                        linkedApplication = application;
                    }
                }

                AssignmentApplicationItemController controller = loader.getController();
                controller.transferAssignmentAndApplicationDetails(linkedApplication,assignment);

                vBoxApplicationList.getChildren().add(itemVBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onLessonsClicked(MouseEvent event) {
        TutorUtils.switchScene("dashboard-lessons.fxml",event,getClass());
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
