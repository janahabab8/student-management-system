package com.mycompany.finalpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    private static final String STUDENTS_FILE = "students.txt";

    // تحميل كل الطلاب من الملف
    private static List<Student> loadAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("students.txt not found.");
        }
        return students;
    }

    // إيجاد طالب بالـ ID
    private static Student findStudentById(String studentId) {
        for (Student s : loadAllStudents()) {
            if (s.getStudentId().equals(studentId)) return s;
        }
        return null;
    }

    // تقرير طالب واحد
    public static String generateStudentReport(String studentId) {
        Student student = findStudentById(studentId);
        if (student == null) return "Student not found.";

        List<Grade> grades = GradeCalculator.getGradesForStudent(studentId);
        double average = GradeCalculator.calculateAverage(studentId);
        String letter = GradeCalculator.getLetterGrade(average);
        double gpa = GradeCalculator.getGPA(average);
        String status = GradeCalculator.getStatus(average);

        StringBuilder report = new StringBuilder();
        report.append("===== Student Report =====\n");
        report.append("ID     : ").append(student.getStudentId()).append("\n");
        report.append("Name   : ").append(student.getStudentName()).append("\n");
        report.append("Major  : ").append(student.getMajor()).append("\n");
        report.append("--------------------------\n");
        report.append("Courses & Grades:\n");

        if (grades.isEmpty()) {
            report.append("  No grades recorded.\n");
        } else {
            for (Grade g : grades) {
                report.append("  ").append(g.getCourseName())
                      .append(": ").append(g.getGrade()).append("\n");
            }
        }

        report.append("--------------------------\n");
        report.append(String.format("Average      : %.2f\n", average));
        report.append("Letter Grade : ").append(letter).append("\n");
        report.append(String.format("GPA          : %.2f\n", gpa));
        report.append("Status       : ").append(status).append("\n");
        report.append("==========================\n");

        return report.toString();
    }

    // تقرير كل الطلاب
    public static String generateAllReports() {
        List<Student> students = loadAllStudents();
        if (students.isEmpty()) return "No students found.";

        StringBuilder all = new StringBuilder();
        for (Student s : students) {
            all.append(generateStudentReport(s.getStudentId()));
            all.append("\n");
        }
        return all.toString();
    }

    // إرجاع قائمة الطلاب للـ ComboBox
    public static List<Student> getAllStudents() {
        return loadAllStudents();
    }
}
