package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.TutorService;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class FindTutorsController {
    // Parent Account used for testing, lol:
    // RianParent, parent123

    @Autowired
    TutorService tutorService;

    @FXML
    private GridPane tutorGrid;

    @FXML
    private Label btnAssignments;

    @FXML
    private Label btnFindTutors;

    @FXML
    private Label btnLogout;

    @FXML
    private VBox vBoxFocus;

    List<Tutor> tutors = new ArrayList<>();

    // get the list of tutors from database on initialize
    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());

        // get array list of tutors
        tutors.clear();
        tutors = tutorService.getAllTutors();

        System.out.println("I found " + tutors.size() + " tutors");

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
                loader.setLocation(getClass().getResource("/pages/parent/tutor-card.fxml"));
                VBox cardBox = loader.load();
                TutorCardController cardController = loader.getController();
                cardController.setCardData(tutor);

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

    @FXML
    void onBtnAssignmentsClick(MouseEvent event) {
        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }
}
