package by.clevertec.util;

import by.clevertec.model.Examination;
import by.clevertec.model.Student;
import by.clevertec.util.reader.JsonReader;
import by.clevertec.util.reader.Reader;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class TestUtil {

    private static final String STUDENTS_DATA_FILE = "src/test/resources/json/students.json";
    private static final String EXAMINATION_DATA_FILE = "src/test/resources/json/examinations.json";

    private static final Reader reader = new JsonReader();

    public static List<Student> getStudents() {
        return reader.getModelData(STUDENTS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Examination> getExaminations() {
        return reader.getModelData(EXAMINATION_DATA_FILE, new TypeReference<>() {
        });
    }
}
