package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.classes.Account;
import com.ryanhuii.tuitionfinder.classes.Parent;
import com.ryanhuii.tuitionfinder.classes.Tutor;
import com.ryanhuii.tuitionfinder.tools.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.tools.ParentDetailsUpdater;
import com.ryanhuii.tuitionfinder.tools.TutorDetailsUpdater;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AllSetController implements AccountDetailsUpdater, ParentDetailsUpdater, TutorDetailsUpdater {

    private Parent parent = null;
    private Tutor tutor = null;
    private Account account;

    @FXML
    private Label confirmationMsg;
    @FXML
    private VBox vBoxFocus;

    public void initialize() {
        Platform.runLater(() -> {
            Platform.runLater( () -> vBoxFocus.requestFocus() );

            // Okay this is when shit gets REAL.
            // Depending on the object data received, perform different account-type creations when clicking lets go
            if (parent != null) {
                createParentAccount(parent,account);
            } else if (tutor != null) {
                createTutorAccount(tutor,account);
            } else {
                System.out.println("How did both classes end up null???");
            }

        });
    }

    private void createTutorAccount(Tutor tutor, Account account) {

    }

    private void createParentAccount(Parent parent, Account account) {

    }

    @FXML
    void onLetsGo(ActionEvent event) {
        // i'm thinking.....should i disable this button until i get a confirmation from my database that my
        // account has been created within mongoDB?
        System.out.println("Database under construction...");
    }

    @Override
    public void transferAccountDetails(Account account) {
        this.account = account;
    }

    @Override
    public void transferParentDetails(Parent parent) {
        this.parent = parent;
    }

    @Override
    public void transferTutorDetails(Tutor tutor) {
        this.tutor = tutor;
    }
}
