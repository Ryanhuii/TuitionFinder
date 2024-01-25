package com.ryanhuii.tuitionfinder.scene_controllers.tutor;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.service.TutorService;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
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
public class FindAssignmentsController {

    @Autowired
    TutorService tutorService;
    @Autowired
    AssignmentService assignmentService;

    @FXML
    private Label btnDashboard;

    @FXML
    private Label btnFindAssignments;

    @FXML
    private Label btnLogout;

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
        List<Assignment> myAssignments = assignmentService.getAllAssignments();
        try {
            for (Assignment assignment : myAssignments) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/pages/tutor/assignment-item.fxml"));
                VBox itemVBox = loader.load();

                FindAssignmentItemViewController controller = loader.getController();
                controller.transferAssignmentDetails(assignment);

                vBoxAssignmentList.getChildren().add(itemVBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
