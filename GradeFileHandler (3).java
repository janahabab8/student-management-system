
package com.mycompany.finalpro;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class GradeFileHandler {

    private static final String FILE_NAME = "grades.txt";

    public static void saveGrade(Grade grade) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(grade.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving grade: " + e.getMessage());
        }
    }
}
