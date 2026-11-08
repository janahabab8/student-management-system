
package com.mycompany.finalpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddGradesController {

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField courseField;

    @FXML
    private TextField gradeField;

    @FXML
    private Label messageLabel;

    @FXML
private void handleSaveGrade(ActionEvent event) {

        String studentId = studentIdField.getText();
        String course = courseField.getText();
        String gradeText = gradeField.getText();

        if (studentId.isEmpty() || course.isEmpty() || gradeText.isEmpty()) {
            messageLabel.setText("Please fill all fields");
            return;
        }

        try {
            double gradeValue = Double.parseDouble(gradeText);

            Grade grade = new Grade(studentId, course, gradeValue);
            GradeFileHandler.saveGrade(grade);

            messageLabel.setText("Grade saved successfully!");

            studentIdField.clear();
            courseField.clear();
            gradeField.clear();

        } catch (NumberFormatException e) {
            messageLabel.setText("Grade must be a number");
        }
    }
   @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "Dashboard", "Dashboard");
    }
}
