package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MyAssignmentsController {

    @Autowired
    AssignmentService assignmentService;
    @Autowired
    ParentService parentService;

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
    @FXML
    private VBox vBoxAssignmentList;

    public void initialize() {
        Platform.runLater(() -> vBoxFocus.requestFocus());
        vBoxAssignmentList.setSpacing(10);

        // call the database and get the list of my assignments
        refreshAssignmentList();
    }

    public void deleteAssignment(Assignment assignment) {
        System.out.println("page has been refreshed. Will now...delete assignment" + assignment.getFormDetails());
        // two-step process:
        // delete the assignment object from the assignments collection
        // delete the assignment ID from the parent's arrayList
        assignmentService.deleteAssignment(assignment);
        ParentUtils.setParent(parentService.deleteAssignmentFromAssignmentList(assignment));
        refreshAssignmentList();
    }

    private void refreshAssignmentList() {
        vBoxAssignmentList.getChildren().clear();
        List<Assignment> myAssignments = assignmentService.getParentAssignments(ParentUtils.getParent().getAssignmentList());
        try {
            for (Assignment assignment : myAssignments) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/pages/parent/assignment-item.fxml"));
                //loader.setControllerFactory(aClass -> ParentUtils.getApplicationContext().getBean(aClass)); // shd i do this
                VBox itemVBox = loader.load();

                AssignmentItemController controller = loader.getController();
                controller.transferAssignmentDetails(assignment);

                vBoxAssignmentList.getChildren().add(itemVBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAddAssignment(MouseEvent event) {
        System.out.println("Adding Assignment");
        ParentUtils.switchScene("add-assignment.fxml", event, getClass());
    }

    @FXML
    void onBtnFindTutorsClick(MouseEvent event) {
        ParentUtils.switchScene("find-tutors.fxml", event, getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml", event, getClass());
    }
}
