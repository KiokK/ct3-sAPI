package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println(task1());
        System.out.println(task2());
        System.out.println(task3());
        System.out.println("Animal count: " + task4());
        System.out.println("Is anyone from 'Hungarian': " + task5());
        System.out.println("There is other genders (no 'Male' or 'Female'): " + task6());
        System.out.println("No one from Oceania: " + task7());
        System.out.println("The oldest animal in sorted top 100: " + task8());
        System.out.println("The shortest animal bread: " + task9());
        System.out.println("All animals sum age: " + task10());
        System.out.println("Indonesian animals average age: " + task11());;
        System.out.println("Students whom can go to academy(limit 200): " + task12());
//        task13();
//        task14();
//        task15();
//        task16();
//        task17();
//        task18();
//        task19();
//        task20();
//        task21();
//        task22();
    }

    public static List<Animal> task1() {
        System.out.println("Task 1: ");
        List<Animal> animals = Util.getAnimals();

        AtomicInteger counter = new AtomicInteger();
        return animals.stream()
                .filter(a -> a.getAge() >= 10 && a.getAge() < 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 7))
                .get(2);
    }

    public static List<String> task2() {
        System.out.println("Task 2: ");
        List<Animal> animals = Util.getAnimals();

        final String JAPANESE = "Japanese";
        final String FEMALE = "Female";

        return animals.stream()
                .filter(a -> JAPANESE.equals(a.getOrigin()))
                .peek(a -> a.setBread(a.getBread().toUpperCase()))
                .filter(a -> FEMALE.equals(a.getGender()))
                .map(Animal::toString)
                .collect(Collectors.toList());
    }

    public static List<String> task3() {
        System.out.println("Task 3: ");
        List<Animal> animals = Util.getAnimals();

        return animals.stream()
                .filter(a -> a.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(o -> o.startsWith("A"))
                .collect(Collectors.toList());
    }

    public static long task4() {
        System.out.println("Task 4: ");
        List<Animal> animals = Util.getAnimals();

        final String FEMALE = "Female";
        return animals.stream()
                .filter(a -> FEMALE.equals(a.getGender()))
                .count();
    }

    public static boolean task5() {
        System.out.println("Task 5: ");
        List<Animal> animals = Util.getAnimals();

        final String HUNGARIAN = "Hungarian";
        return animals.stream()
                .filter(a -> a.getAge() >= 20 && a.getAge() <= 30)
                .anyMatch(a -> HUNGARIAN.equals(a.getOrigin()));
    }

    public static boolean task6() {
        System.out.println("Task 6: ");
        List<Animal> animals = Util.getAnimals();

        final String FEMALE = "Female";
        final String MALE = "Male";
        return animals.stream()
                .anyMatch(a -> !FEMALE.equals(a.getGender()) && !MALE.equals(a.getGender()) );
    }

    public static boolean task7() {
        System.out.println("Task 7: ");
        List<Animal> animals = Util.getAnimals();

        final String OCEANIA = "Oceania";
        return animals.stream()
                .noneMatch(a -> OCEANIA.equals(a.getOrigin()));
    }

    public static int task8() {
        System.out.println("Task 8: ");
        List<Animal> animals = Util.getAnimals();

        try {
            return animals.stream()
                    .sorted(Comparator.comparing(Animal::getBread))
                    .limit(100)
                    .max((x, y) -> x.getAge() - y.getAge())
                    .orElseThrow()
                    .getAge();
        } catch (NoSuchElementException e) {
            System.out.println("Task 8 Error!");
            return -1;
        }
    }

    public static int task9() {
        System.out.println("Task 9: ");
        List<Animal> animals = Util.getAnimals();

        try {
            return animals.stream()
                    .map(Animal::getBread)
                    .map(String::toCharArray)
                    .min((x, y) -> x.length - y.length)
                    .orElseThrow()
                    .length;
        } catch (NoSuchElementException e) {
            System.out.println("Task 9 Error!");
        }
        return -1;
    }

    public static long task10() {
        System.out.println("Task 10: ");
        List<Animal> animals = Util.getAnimals();

        return animals.stream()
                .collect(Collectors.summarizingInt(Animal::getAge))
                .getSum();
    }

    public static double task11() {
        System.out.println("Task 11: ");
        List<Animal> animals = Util.getAnimals();

        final String INDONESIAN = "Indonesian";
        return animals.stream()
                .filter(a -> INDONESIAN.equals(a.getOrigin()))
                .collect(Collectors.summarizingInt(Animal::getAge))
                .getAverage();
    }

    public static List<Person> task12() {
        System.out.println("Task 12: ");
        List<Person> persons = Util.getPersons();

        final String MALE = "Male";
        return persons.stream()
                .filter(p -> MALE.equals(p.getGender())
                        && p.getDateOfBirth().plusYears(18).isBefore(LocalDate.now())
                        && p.getDateOfBirth().plusYears(27).isAfter(LocalDate.now())
                )
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .collect(Collectors.toList());
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
//        houses.stream() Продолжить ...
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
//        flowers.stream() Продолжить ...
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
//        students.stream() Продолжить ...
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }
}
