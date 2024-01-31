package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.AssignmentApplication;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.AssignmentApplicationService;
import com.ryanhuii.tuitionfinder.service.TutorService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViewPendingTutorsController {

    Assignment assignment;
    List<Tutor> tutors = new ArrayList<>();

    @Autowired
    AssignmentApplicationService applicationService;
    @Autowired
    TutorService tutorService;

    @FXML
    private Label txtAssignmentLabel;
    @FXML
    private Label btnAssignments;
    @FXML
    private Label btnFindTutors;
    @FXML
    private Label btnLogout;
    @FXML
    private GridPane tutorGrid;
    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());

        // i think i'll only update the list once the object is passed via the function
    }

    @FXML
    void onBtnFindTutorsClick(MouseEvent event) {
        ParentUtils.switchScene("find-tutors.fxml", event, getClass());
    }
    @FXML
    void onBtnAssignmentsClick(MouseEvent event) {
        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }
    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

    public void transferAssignmentDetails(Assignment assignment) {
        this.assignment = assignment;
        txtAssignmentLabel.setText("View Pending Tutors for Assignment " + assignment.getAssignment_id());

        // get array list of tutors and display them onto the grid
        tutors.clear();
        tutors = tutorService.getTutorsThatAppliedToThisAssignment(assignment);
        List<AssignmentApplication> applications = applicationService.getAssignmentApplications(assignment);

        System.out.println(tutors.size() + " tutors applied for this assignment");
        for (Tutor tutor : tutors) {
            System.out.println(tutor.getName());
        }

        // Set equal width constraints for all columns in the GridPane
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(25); // 25% width for each column
            tutorGrid.getColumnConstraints().add(column);
        }
        // Populate the GridPane with tutors
        int column = 0, row = 1;
        try {
            for (Tutor tutor : tutors) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/pages/parent/pending-tutor-card.fxml"));
                VBox cardBox = loader.load();

                AssignmentApplication application = new AssignmentApplication();
                for (AssignmentApplication a : applications) {
                    if (a.getTutorID().equals(tutor.getUid())) application = a;
                }
                // for each item controller, inject both the tutor and the application object.
                PendingTutorCardController cardController = loader.getController();
                cardController.setCardData(tutor, assignment ,application);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                // Set both row and column margins for the label
                Insets margins = new Insets(10); // You can adjust the values as needed
                GridPane.setMargin(cardBox, margins);
                // Add the card to the grid
                tutorGrid.add(cardBox,column++,row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
