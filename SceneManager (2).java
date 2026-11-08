
package com.mycompany.finalpro;



import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

public class SceneManager {

    public static void switchScene(ActionEvent event, String fxmlFile, String title) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene  scene = new Scene(loadFXML(fxmlFile), 640, 480);
                stage.setTitle("Student Management System");

           

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

        } catch (IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText(null);
            alert.setContentText("Cannot open: " + fxmlFile);
            alert.showAndWait();
        }
    }
     private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}