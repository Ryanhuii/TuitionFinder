package com.ryanhuii.tuitionfinder.tools;

import com.ryanhuii.tuitionfinder.classes.Account;
import com.ryanhuii.tuitionfinder.scene_controllers.account.AccountDetailsController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    public static void createNewAccountToAccountDetails(MouseEvent event, Class pageClass, Account account) {
        try {
            String pageName = "/account/account-details.fxml";
            // splitting up the code above, so I can access its controller
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages" + pageName));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            AccountDetailsController controller = loader.getController();
            controller.updateAccountDetails(account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Setting up the appearance of the back button
    public static void setUpBackButton(Button btnBack, Button btnCheese, Class pageClass) {
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
}
