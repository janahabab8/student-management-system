package com.mycompany.finalpro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewReportsController implements Initializable {

    @FXML
    private ComboBox<String> studentComboBox;

    @FXML
    private TextArea reportArea;

    @FXML
    private Label averageLabel;

    @FXML
    private Label letterGradeLabel;

    @FXML
    private Label gpaLabel;

    @FXML
    private Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadStudentIds();
    }

    private void loadStudentIds() {
        List<Student> students = ReportGenerator.getAllStudents();
        ObservableList<String> ids = FXCollections.observableArrayList();
        for (Student s : students) {
            ids.add(s.getStudentId() + " - " + s.getStudentName());
        }
        studentComboBox.setItems(ids);
    }

    @FXML
    private void handleGenerateReport() {
        String selected = studentComboBox.getValue();
        if (selected == null || selected.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student.");
            alert.showAndWait();
            return;
        }

        String studentId = selected.split(" - ")[0].trim();
        String report = ReportGenerator.generateStudentReport(studentId);
        reportArea.setText(report);

        double average = GradeCalculator.calculateAverage(studentId);
        String letter = GradeCalculator.getLetterGrade(average);
        double gpa = GradeCalculator.getGPA(average);
        String status = GradeCalculator.getStatus(average);

        averageLabel.setText(String.format("Average: %.2f", average));
        letterGradeLabel.setText("Grade: " + letter);
        gpaLabel.setText(String.format("GPA: %.2f", gpa));
        statusLabel.setText("Status: " + status);
        statusLabel.setStyle(status.equals("Pass")
                ? "-fx-text-fill: green; -fx-font-weight: bold;"
                : "-fx-text-fill: red; -fx-font-weight: bold;");
    }

    @FXML
    private void handleAllReports() {
        String allReports = ReportGenerator.generateAllReports();
        reportArea.setText(allReports);
        averageLabel.setText("Average: -");
        letterGradeLabel.setText("Grade: -");
        gpaLabel.setText("GPA: -");
        statusLabel.setText("Status: -");
        statusLabel.setStyle("");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "Dashboard", "Dashboard");
    }
}
