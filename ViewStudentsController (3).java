package com.mycompany.finalpro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewStudentsController implements Initializable {

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> majorColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Label totalLabel;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        loadStudents();
    }

    private void loadStudents() {
        List<Student> students = ReportGenerator.getAllStudents();
        studentList.setAll(students);
        studentsTable.setItems(studentList);
        totalLabel.setText("Total Students: " + students.size());
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase().trim();
        if (keyword.isEmpty()) {
            loadStudents();
            return;
        }
        ObservableList<Student> filtered = FXCollections.observableArrayList();
        for (Student s : ReportGenerator.getAllStudents()) {
            if (s.getStudentId().toLowerCase().contains(keyword)
                    || s.getStudentName().toLowerCase().contains(keyword)
                    || s.getMajor().toLowerCase().contains(keyword)) {
                filtered.add(s);
            }
        }
        studentsTable.setItems(filtered);
        totalLabel.setText("Total Students: " + filtered.size());
    }

    @FXML
    private void handleRefresh() {
        searchField.clear();
        loadStudents();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "Dashboard", "Dashboard");
    }
}
