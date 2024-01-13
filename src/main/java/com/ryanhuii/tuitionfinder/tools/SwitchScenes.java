package com.ryanhuii.tuitionfinder.tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScenes {
    public static void switchScenesWithinSameWindow (String pageName, ActionEvent event, Class pageClass) {
        try {
            Parent root = FXMLLoader.load(pageClass.getResource("/pages/" + pageName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Just in case it's a mouse event instead of a button click. This happens in pages like the login page.
    public static void switchScenesWithinSameWindow (String pageName, MouseEvent event, Class pageClass) {
        try {
            Parent root = FXMLLoader.load(pageClass.getResource("/pages/" + pageName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
