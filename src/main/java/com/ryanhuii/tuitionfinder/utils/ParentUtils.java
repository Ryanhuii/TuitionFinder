package com.ryanhuii.tuitionfinder.utils;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Assignment;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.model.Tutor;
import com.ryanhuii.tuitionfinder.scene_controllers.parent.MyAssignmentsController;
import com.ryanhuii.tuitionfinder.scene_controllers.parent.ViewTutorController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class ParentUtils {
    @Getter
    private static ApplicationContext applicationContext;
    @Getter
    private static Account account;
    @Getter
    private static Parent parent;
    @Getter @Setter
    private static Event tempEvent;

    // general switch scene function
    public static void switchScene(String pageName, Event event, Class pageClass) {
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages/parent/" + pageName));

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

    public static void displayTutor(Event event, Class pageClass, Tutor tutor) {
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages/parent/view-tutor.fxml"));

            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            javafx.scene.Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Transfer the selected tutor's details over to the controller
            ViewTutorController controller = loader.getController();
            controller.transferTutorDetails(tutor);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setApplicationContext(ApplicationContext context) {
        ParentUtils.applicationContext = context;
    }

    public static void setAccount(Account account) {
        ParentUtils.account = account;
    }

    public static void setParent(Parent parent) {
        ParentUtils.parent = parent;
        if (parent != null) System.out.println("Parent successfully updated!");
        else System.out.println("Something went wrong, and parent is now null");
    }

    public static void deleteAssignment(Event event, Class pageClass, Assignment assignment) {
        // basically refresh the assignment list page, pass the specific assignment to be deleted and call the delete function.
        // MyAssignmentsController will then use its service objects to delete that assignment from Parent and Assignment collections
        // It will then refresh itself...
        try {
            FXMLLoader loader = new FXMLLoader(pageClass.getResource("/pages/parent/" + "my-assignments.fxml"));

            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            javafx.scene.Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            ParentUtils.setTempEvent(event); // oh my god

            MyAssignmentsController controller = loader.getController();
            controller.deleteAssignment(assignment);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
