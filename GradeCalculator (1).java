package com.mycompany.finalpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeCalculator {

    private static final String FILE_NAME = "grades.txt";

    // تحميل درجات طالب معين من الملف
    public static List<Grade> getGradesForStudent(String studentId) {
        List<Grade> grades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].trim().equals(studentId)) {
                    try {
                        double gradeValue = Double.parseDouble(parts[2].trim());
                        grades.add(new Grade(parts[0].trim(), parts[1].trim(), gradeValue));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("grades.txt not found.");
        }
        return grades;
    }

    // حساب المعدل
    public static double calculateAverage(String studentId) {
        List<Grade> grades = getGradesForStudent(studentId);
        if (grades.isEmpty()) return 0.0;
        double total = 0;
        for (Grade g : grades) {
            total += g.getGrade();
        }
        return total / grades.size();
    }

    // التقدير الحرفي
    public static String getLetterGrade(double average) {
        if (average >= 90) return "A+";
        else if (average >= 85) return "A";
        else if (average >= 80) return "B+";
        else if (average >= 75) return "B";
        else if (average >= 70) return "C+";
        else if (average >= 65) return "C";
        else if (average >= 60) return "D+";
        else if (average >= 55) return "D";
        else return "F";
    }

    // GPA
    public static double getGPA(double average) {
        if (average >= 90) return 4.0;
        else if (average >= 85) return 3.75;
        else if (average >= 80) return 3.5;
        else if (average >= 75) return 3.0;
        else if (average >= 70) return 2.5;
        else if (average >= 65) return 2.0;
        else if (average >= 60) return 1.5;
        else if (average >= 55) return 1.0;
        else return 0.0;
    }

    // الحالة
    public static String getStatus(double average) {
        return average >= 60 ? "Pass" : "Fail";
    }
}
