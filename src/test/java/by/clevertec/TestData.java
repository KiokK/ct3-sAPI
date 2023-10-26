package by.clevertec;

import by.clevertec.model.Examination;
import by.clevertec.model.Student;

import java.util.List;

public class TestData {
    public static final List<Student> students = List.of(
        new Student(1, "Adams", 18, "Physics", "P-1"),
        new Student(2, "Carter", 19, "ComputerScience", "C-2"),
        new Student(3, "Jonson", 20, "Mathematics", "M-3")
    );

    public static final List<Examination> examinations = List.of(
      new Examination(1, 1, 10, 9, 7),
      new Examination(2, 2, 7, 4, 9),
      new Examination(3, 3, 9, 8, 7)
    );
}
