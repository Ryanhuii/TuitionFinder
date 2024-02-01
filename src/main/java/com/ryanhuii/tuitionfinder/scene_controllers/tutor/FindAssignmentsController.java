package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.service.TutorService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.TutorUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class FindAssignmentsController {

    @Autowired
    TutorService tutorService;
    @Autowired
    AssignmentService assignmentService;


    @FXML
    private ImageView imgEmpty;
    @FXML
    private VBox vBoxAssignmentList;

    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater(() -> vBoxFocus.requestFocus());
        vBoxAssignmentList.setSpacing(20);

        refreshAssignmentList();
    }

    private void refreshAssignmentList() {
        vBoxAssignmentList.getChildren().clear();
        List<Assignment> allAssignments = assignmentService.getAllAssignments();
        try {
            boolean noMoreAssignmentsForMe = true;
            for (Assignment assignment : allAssignments) {
                if (!assignment.getStatus().equals("Ongoing")) { // make sure that only non-confirmed assignments are present!
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/pages/tutor/assignment-item.fxml"));
                    VBox itemVBox = loader.load();

                    FindAssignmentItemViewController controller = loader.getController();
                    controller.transferAssignmentDetails(assignment);

                    vBoxAssignmentList.getChildren().add(itemVBox);
                    noMoreAssignmentsForMe = false;
                }
            }
            vBoxAssignmentList.getChildren().add(imgEmpty);
            imgEmpty.setVisible(noMoreAssignmentsForMe);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
