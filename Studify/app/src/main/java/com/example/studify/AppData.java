
package com.example.studify;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    public static final List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(
                "STU001",
                "Ahmed",
                "Ben Ali",
                "ahmed@example.com",
                "22123456",
                "Class A"
        ));

        students.add(new Student(
                "STU002",
                "Sara",
                "Trabelsi",
                "sara@example.com",
                "55123456",
                "Class B"
        ));

        students.add(new Student(
                "STU003",
                "Mohamed",
                "Mansouri",
                "mohamed@example.com",
                "99123456",
                "Class A"
        ));
    }

    private AppData() {
        // Prevent creating AppData objects.
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static List<Student> getStudents() {
        return students;
    }
}
