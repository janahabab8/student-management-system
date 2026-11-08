
package com.mycompany.finalpro;

public class Grade {
    

    private String studentId;
    private String courseName;
    private double grade;

    public Grade(String studentId, String courseName, double grade) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return studentId + "," + courseName + "," + grade;
    }
}
