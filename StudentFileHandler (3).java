
package com.mycompany.finalpro;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentFileHandler {

    private static final String FILE_NAME = "students.txt";

    public static void saveStudent(Student student) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(student.toString());

            printWriter.close();

        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }
}
