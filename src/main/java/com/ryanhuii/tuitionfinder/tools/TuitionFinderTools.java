package com.ryanhuii.tuitionfinder.tools;

import com.ryanhuii.tuitionfinder.classes.Account;
import com.ryanhuii.tuitionfinder.classes.Tutor;
import com.ryanhuii.tuitionfinder.scene_controllers.account.SetupParentController;
import com.ryanhuii.tuitionfinder.scene_controllers.account.SetupTutor1Controller;
import com.ryanhuii.tuitionfinder.scene_controllers.account.SetupTutor2Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.SecureRandom;

public class TuitionFinderTools {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 10;

    public static void switchScene(String pageName, ActionEvent event, Class pageClass) {
        try {
            Parent root = FXMLLoader.load(pageClass.getResource("/pages" + pageName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Just in case it's a mouse event instead of a button click. This happens in pages like the login page.
    public static void switchScene(String pageName, MouseEvent event, Class pageClass) {
        try {
            Parent root = FXMLLoader.load(pageClass.getResource("/pages" + pageName));
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

    // This function takes in the completed classes, creates them in the database, then directs the user to the completion screen.
    public static void completeSetup(Event event, Class<? extends SetupParentController> className, Account account,
                                     com.ryanhuii.tuitionfinder.classes.Parent parent) {
        System.out.println("account setup for parent complete");
    }

    public static void completeSetup(Event event, Class<? extends SetupTutor2Controller> className, Account account,
                                     Tutor tutor) {
        System.out.println("account setup for tutor complete");
    }
}
