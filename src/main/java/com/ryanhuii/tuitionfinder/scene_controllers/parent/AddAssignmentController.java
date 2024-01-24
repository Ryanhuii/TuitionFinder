package com.ryanhuii.tuitionfinder.scene_controllers.parent;

import atlantafx.base.util.IntegerStringConverter;
import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentUtils;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

@Component
public class AddAssignmentController {

    Assignment assignment = new Assignment();

    @FXML
    private ComboBox<String> comboBoxGender;
    String[] genders = {"Male", "Female", "Other"};
    String selectedGender = "";

    @FXML
    private ComboBox<String> comboBoxSubject;
    String[] subjects = {"English",
            "Chinese",
            "A Math",
            "E Math",
            "Math (Lower-Sec)",
            "Biology",
            "Chemistry",
            "Physics",
            "Science (Lower-Sec)",
            "Computing",
            "D&T",
            "Art",
            "History",
            "Geography",
            "Social Studies"};
    String selectedSubject = "";

    @FXML
    private HBox hBoxSpinners;

    @FXML
    private TextField txtLevel;

    @FXML
    private TextArea txtParentNote;

    @FXML
    private TextArea txtRates;

    // page setup stuff
    @FXML
    private Label btnAssignments;

    @FXML
    private Label btnFindTutors;

    @FXML
    private Label btnLogout;

    @FXML
    private VBox vBoxFocus;
    // end of page setup stuff

    public void initialize() {
        Platform.runLater( () -> vBoxFocus.requestFocus());

        // setup my combo box for assignment subject
        comboBoxSubject.setItems(FXCollections.observableArrayList(subjects));
        comboBoxSubject.setValue(null);
        comboBoxSubject.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                selectedSubject = subjects[t1.intValue()];
                System.out.println(selectedSubject);
            }
        });

        // setup my combo box for gender
        comboBoxGender.setItems(FXCollections.observableArrayList(genders));
        comboBoxGender.setValue(null);
        comboBoxGender.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                selectedGender = genders[t1.intValue()];
                System.out.println(selectedGender);
            }
        });

        // Setting up my entire hBox - 2 spinners and 2 labels
        // The hBox is 340 pixels wide. Need to make sure that all 4 element widths add up to 340

        // daysSpinner
        var daysSpinner = new Spinner<Integer>(1, 7, 1);
        IntegerStringConverter.createFor(daysSpinner);
        daysSpinner.setEditable(true);
        daysSpinner.setPrefWidth(120);
        daysSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                //System.out.println("days is " + t1);
            }
        });

        var daysText = new Text("days a week, for");

        var durationSpinner = new Spinner<Integer>(1, 12, 1);
        IntegerStringConverter.createFor(durationSpinner);
        durationSpinner.setEditable(true);
        durationSpinner.setPrefWidth(120);
        durationSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                //System.out.println("duration is " + t1);
            }
        });

        var durationText = new Text("months.");

        hBoxSpinners.getChildren().addAll(daysSpinner,daysText,durationSpinner,durationText);

    }

    @FXML
    void onAssignmentsClicked(MouseEvent event) {
        ParentUtils.switchScene("my-assignments.fxml",event,getClass());
    }

    @FXML
    void onFindTutorsClicked(MouseEvent event) {
        ParentUtils.switchScene("find-tutors.fxml",event,getClass());
    }

    @FXML
    void onLogout(MouseEvent event) {
        LoginUtils.switchScene("/account/login.fxml",event,getClass());
    }

}
