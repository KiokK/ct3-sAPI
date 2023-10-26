package by.clevertec;

import by.clevertec.util.TestUtil;
import by.clevertec.util.Util;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {


    @Nested
    class Task20 {
        @Test
        void checkTask20ReturnExpectedMathematics() {
            String expected = "Mathematics";
            String actual = Main.task20(TestUtil.getStudents(), TestUtil.getExaminations());

            assertEquals(expected, actual);
        }

        @Test
        void checkTask20ReturnNoExpected() {
            String expected = "Physics";
            String actual = Main.task20(TestUtil.getStudents(), TestUtil.getExaminations());

            assertNotEquals(expected, actual);
        }

        @Test
        void checkTask20ReturnExpectedPhysics() {
            String expected = "Physics";
            String actual = Main.task20(TestData.students, TestData.examinations);

            assertEquals(expected, actual);
        }

        @Test
        void checkTask20ThrowNoSuchElementException() {
            assertAll(() -> assertThrows(NoSuchElementException.class, () -> {
                        Main.task20(new ArrayList<>(), new ArrayList<>());
                    }),
                    () -> assertThrows(NoSuchElementException.class, () -> {
                        Main.task20(new ArrayList<>(), Util.getExaminations());
                    })
            );
        }
    }
}
