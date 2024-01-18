package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.AccountService;
import com.ryanhuii.tuitionfinder.tools.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.tools.ParentDetailsUpdater;
import com.ryanhuii.tuitionfinder.tools.TutorDetailsUpdater;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@NoArgsConstructor
@Controller
public class AllSetController implements AccountDetailsUpdater, ParentDetailsUpdater, TutorDetailsUpdater {
    @Autowired
    AccountService accountService;
    private Parent parent = null;
    private Tutor tutor = null;
    private Account account;

    @FXML
    private Label confirmationMsg;
    @FXML
    private VBox vBoxFocus;

    public AllSetController() {
        System.out.println("AllSetController created!");
    }

    public void initialize() {
        Platform.runLater(() -> {});

        //accountService.verifyExistence(); // This code gives error
    }

    @FXML
    void onLetsGo(ActionEvent event) {
        // i'm thinking.....should i disable this button until i get a confirmation from my database that my
        // account has been created within mongoDB?
        System.out.println("Database under construction...");

        // Okay this is when shit gets REAL.
        // Depending on the object data received, perform different account-type creations when clicking lets go
        if (parent != null) {
            createParentAccount(parent,account);
        } else if (tutor != null) {
            createTutorAccount(tutor,account);
        } else {
            System.out.println("How did both classes end up null???");
        }
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
    private void createParentAccount(Parent parent, Account account) {

    }
    private void createTutorAccount(Tutor tutor, Account account) {
        System.out.println("Now creating account within database, please wait...");
        Account result = accountService.createAccount(account);
        System.out.println(result == null ? "oh dear the result is null" : "yay account created!");
    }
}
