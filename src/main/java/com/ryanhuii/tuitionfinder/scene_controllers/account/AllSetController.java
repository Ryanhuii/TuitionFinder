package com.ryanhuii.tuitionfinder.scene_controllers.account;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.service.AccountService;
import com.ryanhuii.tuitionfinder.service.ParentService;
import com.ryanhuii.tuitionfinder.service.TutorService;
import com.ryanhuii.tuitionfinder.utils.AccountDetailsUpdater;
import com.ryanhuii.tuitionfinder.utils.LoginUtils;
import com.ryanhuii.tuitionfinder.utils.ParentDetailsUpdater;
import com.ryanhuii.tuitionfinder.utils.TutorDetailsUpdater;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@NoArgsConstructor
@Controller
public class AllSetController implements AccountDetailsUpdater, ParentDetailsUpdater, TutorDetailsUpdater {
    @Autowired
    AccountService accountService;
    @Autowired
    TutorService tutorService;
    @Autowired
    ParentService parentService;

    private Parent parent = null;
    private Tutor tutor = null;
    private Account account;

    @FXML
    private VBox vBoxFocus;

    public AllSetController() {
        System.out.println("AllSetController created!");
    }

    public void initialize() {
        Platform.runLater(() -> {

            // Okay this is when shit gets REAL.
            // Depending on the object data received, perform different account-type creations when clicking lets go
            if (parent != null) {
                createParentAccount(parent, account);
            } else if (tutor != null) {
                createTutorAccount(tutor, account);
            } else {
                System.out.println("How did both classes end up null???");
            }
        });

        //accountService.verifyExistence(); // This code gives error
    }

    @FXML
    void onLetsGo(ActionEvent event) {
        // calls the login function. This must be defined here, not in LoginUtils, as the class services cannot be static.
        login(event, getClass(), account.getUsername(), account.getPassword());
    }

    public void login(ActionEvent event, Class<? extends AllSetController> aClass, String username, String password) {
        // login time.
        Account account = accountService.loginAccount(username,password);
        if (account != null) {
            System.out.println("we found the account!");
            System.out.println(account);
            switch (account.getAccountType()) {
                case "Parent":
                    Parent parent = parentService.getParentByUid(account.getUid());
                    System.out.println((parent != null) ? "We found the parent!" + parent: "Uh oh no parent found");
                    LoginUtils.goToParentHomePage(event, getClass(),account,parent);
                    break;
                case "Tutor":
                    Tutor tutor = tutorService.getTutorByUid(account.getUid());
                    System.out.println((tutor != null) ? "We found the tutor!" + tutor: "Uh oh no tutor found");
                    LoginUtils.goToTutorHomePage(event,getClass(),account,tutor);
                    break;
                default:
                    System.out.println("how did account have no type??");
                    break;
            }
        } else {
            System.out.println("Account is null. We couldn't find it");
        }
    }

    private void createParentAccount(Parent parent, Account account) {
        System.out.println("Now creating account within database, please wait...");

        Account result = accountService.createAccount(account);
        Parent resultParent = parentService.createParent(parent);

        System.out.println((result == null || resultParent == null) ? "oh dear the result is null" : "yay parent account created!");
    }

    private void createTutorAccount(Tutor tutor, Account account) {
        System.out.println("Now creating account within database, please wait...");

        Account result = accountService.createAccount(account);
        Tutor resultTutor = tutorService.createTutor(tutor);

        System.out.println((result == null || resultTutor == null) ? "oh dear the result is null" : "yay tutor account created!");
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
