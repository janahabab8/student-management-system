
package com.mycompany.finalpro;

/**
 *
 * @author Yara1
 */
public class Student {

    private String studentId;
    private String studentName;
    private String major;

    public Student(String studentId, String studentName, String major) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return studentId + "," + studentName + "," + major;
    }
}
