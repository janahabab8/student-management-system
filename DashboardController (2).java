
package com.mycompany.finalpro;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    private void goToAddStudent(ActionEvent event) {
        SceneManager.switchScene(event, "AddStudent", "Add Student");
    }

    @FXML
    private void goToAddGrades(ActionEvent event) {
        SceneManager.switchScene(event, "AddGrades", "Add Grades");
    }

    @FXML
    private void goToViewStudents(ActionEvent event) {
        SceneManager.switchScene(event, "ViewStudents", "View Students");
    }

    @FXML
    private void goToViewReports(ActionEvent event) {
        SceneManager.switchScene(event, "ViewReports", "View Reports");
    }

    @FXML
    private void logout(ActionEvent event) {
        SceneManager.switchScene(event, "Login", "Login");
    }
}