package com.ryanhuii.tuitionfinder.utils;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.scene_controllers.tutor.FindAssignmentItemViewController;
import com.ryanhuii.tuitionfinder.scene_controllers.tutor.ViewAssignmentController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class TutorUtils {
    private static ApplicationContext applicationContext;
    private static Account account;
    private static Tutor tutor;

    public static void switchScene(String pageName, Event event, Class pageClass) {
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages/tutor/" + pageName));

            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            javafx.scene.Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setApplicationContext(ApplicationContext context) {
        TutorUtils.applicationContext = context;
    }

    public static void setAccount(Account account) {
        TutorUtils.account = account;
    }

    public static void setTutor(Tutor tutor) {
        TutorUtils.tutor = tutor;
    }

    public static void viewAssignment(Assignment assignment, Event event, Class<?> pageClass) {
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages/tutor/" + "view-assignment.fxml"));

            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            javafx.scene.Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // show the assignment details to the tutor
            ViewAssignmentController controller = loader.getController();
            controller.transferAssignmentDetails(assignment);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
