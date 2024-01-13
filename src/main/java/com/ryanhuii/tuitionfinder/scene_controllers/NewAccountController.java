package com.ryanhuii.tuitionfinder.scene_controllers;

import com.ryanhuii.tuitionfinder.tools.SwitchScenes;
import jakarta.annotation.PostConstruct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NewAccountController {

    @Value("classpath:/pages/login.fxml")
    private Resource loginResource;

    // my variables for switching scenes
    private Stage stage;
    private Scene scene;
    private Parent root;
    String selectedAccountType = "";

    @FXML
    private VBox vBoxParent;
    @FXML
    private VBox vBoxTutor;
    @FXML
    private BorderPane borderPaneParent;
    @FXML
    private BorderPane borderPaneTutor;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnCheese;

    // this is so dumb istg
    public void initialize() {

        updateSelectedCard();
        double iconDimension = 20;
        ImageView backArrow = new ImageView(new Image(getClass().getResourceAsStream("/images/arrow_back.png")));
        backArrow.setFitHeight(iconDimension);
        backArrow.setFitWidth(iconDimension);

        ImageView copy = new ImageView(new Image(getClass().getResourceAsStream("/images/arrow_back.png")));
        copy.setFitHeight(iconDimension);
        copy.setFitWidth(iconDimension);

        btnBack.setGraphic(backArrow);
        btnCheese.setGraphic(copy);
        btnCheese.setVisible(false);
    }

    @FXML
    void onNextClicked(MouseEvent event) {

    }

    @FXML
    void onBackClicked(ActionEvent event) {
        System.out.println("going back to login page");
        SwitchScenes.switchScenesWithinSameWindow("login.fxml", event, getClass());
    }
    @FXML
    void onParentSelected(MouseEvent event) {
        //System.out.println("parent selected");
        selectedAccountType = "Parent";
        updateSelectedCard();
    }

    @FXML
    void onTutorSelected(MouseEvent event) {
        //System.out.println("tutor selected");
        selectedAccountType = "Tutor";
        updateSelectedCard();
    }

    @FXML
    void highlightParentOff(MouseEvent event) {
        vBoxParent.setStyle("-fx-background-color: transparent; -fx-background-radius: 10;");
    }

    @FXML
    void highlightParentOn(MouseEvent event) {
        vBoxParent.setStyle("-fx-background-color: #F4F4F4; -fx-background-radius: 10;");
    }

    @FXML
    void highlightTutorOff(MouseEvent event) {
        vBoxTutor.setStyle("-fx-background-color: transparent; -fx-background-radius: 10;");
    }

    @FXML
    void highlightTutorOn(MouseEvent event) {
        vBoxTutor.setStyle("-fx-background-color: #F4F4F4; -fx-background-radius: 10;");
    }

    void updateSelectedCard() {
        // just highlight the border of the selected account type
        switch (selectedAccountType) {
            case "Parent":
                borderPaneParent.setStyle("-fx-border-color:  #3F6C51; -fx-border-radius: 10; -fx-border-width: 2;");
                borderPaneTutor.setStyle("-fx-border-color:  #D9D9D9; -fx-border-radius: 10; -fx-border-width: 1;");
                btnNext.setDisable(false);
                break;
            case "Tutor":
                borderPaneTutor.setStyle("-fx-border-color:  #3F6C51; -fx-border-radius: 10; -fx-border-width: 2;");
                borderPaneParent.setStyle("-fx-border-color:  #D9D9D9; -fx-border-radius: 10; -fx-border-width: 1;");
                btnNext.setDisable(false);
                break;
            case "":
                //disable the button if this hasn't been clicked on yet. Also this block runs during initialization
                borderPaneParent.setStyle("-fx-border-color:  #D9D9D9; -fx-border-radius: 10; -fx-border-width: 1;");
                borderPaneTutor.setStyle("-fx-border-color:  #D9D9D9; -fx-border-radius: 10; -fx-border-width: 1;");
                btnNext.setDisable(true);
        }
    }

}
