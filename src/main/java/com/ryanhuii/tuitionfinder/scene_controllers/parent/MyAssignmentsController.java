package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.service.AssignmentService;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

        // call the database and get the list of my assignments
        List<Assignment> myAssignments = assignmentService.getAssignments(ParentUtils.getParent().getAssignmentList());
        try {
            for (Assignment assignment : myAssignments) {
//            System.out.println("Assignment found: ");
//            System.out.println(assignment.toString());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/pages/parent/assignment-item.fxml"));
                VBox itemVBox = loader.load();

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
