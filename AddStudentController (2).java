
package com.mycompany.finalpro;


import javafx.event.ActionEvent;
import javafx.fxml.FXML; 
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddStudentController {

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField majorField;

    @FXML
    private void addStudent() {
        String id = studentIdField.getText();
        String name = studentNameField.getText();
        String major = majorField.getText();

        if (id.isEmpty()  ||name.isEmpty() || major.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields.");
            alert.showAndWait();
            return;
        }

        Student student = new Student(id, name, major);
        StudentFileHandler.saveStudent(student);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Student added successfully.");
        alert.showAndWait();

        studentIdField.clear();
        studentNameField.clear();
        majorField.clear();
    }
       @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "Dashboard", "Dashboard");
    }
}
