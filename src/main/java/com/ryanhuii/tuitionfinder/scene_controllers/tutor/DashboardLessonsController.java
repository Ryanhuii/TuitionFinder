package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
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
import java.util.Optional;

@Component
public class DashboardLessonsController {
    @Autowired
    AssignmentService assignmentService;

    @FXML
    private Label btnDashboard;

    @FXML
    private Label btnFindAssignments;

    @FXML
    private Label btnLessons;

    @FXML
    private Label btnLogout;

    @FXML
    private Label titlePendingApplications;

    @FXML
    private VBox vBoxApplicationList;

    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater(() -> vBoxFocus.requestFocus());
        // get all my applications, as well as the corresponding assignment it is attached to
        Optional<List<Assignment>> getOngoingAssignments = assignmentService.getTutorOngoingAssignments(TutorUtils.getTutor().getUid());
        getOngoingAssignments.ifPresent(this::refreshLessonList);

        titlePendingApplications.setText("Pending (" + TutorUtils.getPendingApplicationsCount() + ")");

        if (TutorUtils.getMyLessonsCount() > 0) btnLessons.setText("Lessons (" + TutorUtils.getMyLessonsCount() + ")");
    }

    private void refreshLessonList(List<Assignment> myLessons) {
        vBoxApplicationList.getChildren().clear();
        // simple display of my assignment
        try {
            for (Assignment lesson : myLessons) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/pages/tutor/lesson-item.fxml"));
                VBox itemVBox = loader.load();

                LessonItemController controller = loader.getController();
                controller.transferLessonDetails(lesson);

                vBoxApplicationList.getChildren().add(itemVBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onFindAssignmentsClick(MouseEvent event) {
        TutorUtils.switchScene("find-assignments.fxml",event,getClass());
    }
    @FXML
    void onPendingApplicationsClicked(MouseEvent event) {
        TutorUtils.switchScene("dashboard-pending-applications.fxml",event,getClass());
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
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

}
