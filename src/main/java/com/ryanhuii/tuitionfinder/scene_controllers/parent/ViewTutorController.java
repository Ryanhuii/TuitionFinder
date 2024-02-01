package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class ViewTutorController {
    Tutor tutor;

    @FXML
    private Label btnAssignments;

    @FXML
    private Button btnBack;

    @FXML
    private Label btnFindTutors;

    @FXML
    private Label btnLogout;

    @FXML
    private VBox vBoxFocus;

    @FXML
    private Label txtEducation;

    @FXML
    private Label txtTutorAge;

    @FXML
    private Label txtTutorExperience;

    @FXML
    private Label txtTutorGender;

    @FXML
    private Label txtTutorName;

    @FXML
    private Label txtTutorSubjects;

    @FXML
    private Label txtTutorType;

    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());

        // Setting up the appearance of the back button
        double iconDimension = 20;
        ImageView backArrow = new ImageView(new Image(getClass().getResourceAsStream("/images/arrow_back.png")));
        backArrow.setFitHeight(iconDimension);
        backArrow.setFitWidth(iconDimension);
        btnBack.setGraphic(backArrow);
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        ParentUtils.switchScene("find-tutors.fxml",event,getClass());
    }

    public void transferTutorDetails(Tutor tutor) {
        this.tutor = tutor;
        updateTutorDetailsDisplay();
    }

    private void updateTutorDetailsDisplay() {
        txtTutorName.setText(tutor.getName());
        txtTutorSubjects.setText(tutor.getSubjects().toString());
        txtTutorType.setText(tutor.getTutorType());
        txtTutorExperience.setText(tutor.getExperience() + " years");
        txtTutorGender.setText(tutor.getGender());
        txtTutorAge.setText(tutor.getAge() + " years old");
        txtEducation.setText(tutor.getEducation());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

}
