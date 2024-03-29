package com.ryanhuii.tuitionfinder.utils;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.scene_controllers.account.AllSetController;
import com.ryanhuii.tuitionfinder.scene_controllers.account.SetupParentController;
import com.ryanhuii.tuitionfinder.scene_controllers.account.SetupTutor1Controller;
import com.ryanhuii.tuitionfinder.scene_controllers.account.SetupTutor2Controller;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.security.SecureRandom;

public class LoginUtils {
    private static ApplicationContext applicationContext;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 10;

    // The glue that holds my entire application together.
    public static void setApplicationContext(ApplicationContext context) {
        LoginUtils.applicationContext = context;
    }

    public static void logOut(Event event, Class pageClass) {
        // log out of Tuition Finder application
        switchScene("/account/login.fxml",event,pageClass);
    }
    public static void goToParentHomePage(Event event, Class pageClass, Account account, com.ryanhuii.tuitionfinder.model.Parent parent) {
        // Go to Parent home page. I plan to use a new Tools Class for managing parent-account app interactions
        ParentUtils.setApplicationContext(applicationContext);
        // all the page controllers will display data based on the uid of the parent or account. It is managed top-level by ParentUtils for central safekeeping.
        ParentUtils.setAccount(account);
        ParentUtils.setParent(parent);
        // todo: very first page for parent
        switchScene("/parent/find-tutors.fxml",event,pageClass);
    }

    public static void goToTutorHomePage(Event event, Class pageClass, Account account, Tutor tutor) {
        // Go to Tutor home page. I plan to use a new Tools Class for managing tutor-account app interactions
        // I think I'll work on the tutor side only after a bunch of parent-side stuff is done.
        TutorUtils.setApplicationContext(applicationContext);
        // all the page controllers will display data based on the uid of the parent or account. It is managed top-level by ParentUtils for central safekeeping.
        TutorUtils.setAccount(account);
        TutorUtils.setTutor(tutor);
        switchScene("/tutor/dashboard-my-schedule.fxml",event,pageClass);
    }

    // todo: should I shift this somewhere? Will it be used for all Login, Parent and Tutor app flows?
    public static void switchScene(String pageName, Event event, Class pageClass) {
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages" + pageName));

            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Special function for transitioning from create-new-account to account-details
    public static void nextSetupPage(Event event, Class pageClass, String pageName, Account account) {
        try {
            // splitting up the code above, so I can access its controller
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages" + pageName));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            AccountDetailsUpdater controller = loader.getController();
            controller.transferAccountDetails(account);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void nextTutorSetupPage(Event event, Class pageClass, Account account, Tutor tutor) {
        // this function is for tutor setup part 1 to part 2
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages/account/setup-tutor-2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            SetupTutor2Controller controller = loader.getController();
            controller.transferTutorDetails(tutor);
            controller.transferAccountDetails(account);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void previousTutorSetupPage(Event event, Class pageClass, Account account, Tutor tutor) {
        // go back; this function is for tutor setup part 2 to part 1
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages/account/setup-tutor-1.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            SetupTutor1Controller controller = loader.getController();
            controller.transferTutorDetails(tutor);
            controller.transferAccountDetails(account);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setUpBackButton(Button btnBack, Button btnCheese, Class pageClass) {
        // Setting up the appearance of the back button
        double iconDimension = 20;
        ImageView backArrow = new ImageView(new Image(pageClass.getResourceAsStream("/images/arrow_back.png")));
        backArrow.setFitHeight(iconDimension);
        backArrow.setFitWidth(iconDimension);
        // lol. cheesing it.
        ImageView copy = new ImageView(new Image(pageClass.getResourceAsStream("/images/arrow_back.png")));
        copy.setFitHeight(iconDimension);
        copy.setFitWidth(iconDimension);

        btnBack.setGraphic(backArrow);
        btnCheese.setGraphic(copy);
        btnCheese.setVisible(false);
    }

    public static String generateUID() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    // These 2 functions takes in the completed classes, creates them in the database, then directs the user to the completion screen.
    public static void completeParentSetup(Event event, Class<? extends SetupParentController> className, Account account,
                                     com.ryanhuii.tuitionfinder.model.Parent parent) {
        System.out.println("account setup for parent complete");
        try {
            FXMLLoader loader = new FXMLLoader(className.getResource("/pages/account/all-set.fxml"));

            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            AllSetController controller = loader.getController();
            controller.transferParentDetails(parent);
            controller.transferAccountDetails(account);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void completeTutorSetup(Event event, Class<? extends SetupTutor2Controller> className, Account account,
                                          Tutor tutor) {
        System.out.println("account setup for tutor complete");
        try {
            FXMLLoader loader = new FXMLLoader(className.getResource("/pages/account/all-set.fxml"));

            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            AllSetController controller = loader.getController();
            controller.transferTutorDetails(tutor);
            controller.transferAccountDetails(account);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
