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
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
//        System.out.println(task1());
//        System.out.println(task2());
//        System.out.println(task3());
//        System.out.println("Animal count: " + task4());
//        System.out.println("Is anyone from 'Hungarian': " + task5());
//        System.out.println("There is other genders (no 'Male' or 'Female'): " + task6());
//        System.out.println("No one from Oceania: " + task7());
//        System.out.println("The oldest animal in sorted top 100: " + task8());
//        System.out.println("The shortest animal bread: " + task9());
//        System.out.println("All animals sum age: " + task10());
//        System.out.println("Indonesian animals average age: " + task11());
//        System.out.println("Students whom can go to academy(limit 200): " + task12());
//        System.out.println("First 500 evacuation people: " + task13());
//        task14();
//        task15();
//        task16();
//        task17();

//        task18();
//        task19();
        task20();
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
                .anyMatch(a -> !FEMALE.equals(a.getGender()) && !MALE.equals(a.getGender()));
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

    public static List<Person> task13() {
        System.out.println("Task 13: ");
        List<House> houses = Util.getHouses();

        final String HOSPITAL = "Hospital";
        final String FEMALE = "Female";
        final String MALE = "Male";
        final int CHILDREN_AGE = 18;
        final int FEMALE_RETIREE_AGE = 58;
        final int MALE_RETIREE_AGE = 63;
        final LocalDate NOW = LocalDate.now();

        return houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry(HOSPITAL.equals(house.getBuildingType()) ? 1 :
                                (Period.between(person.getDateOfBirth(), NOW).getYears() < CHILDREN_AGE
                                        || (FEMALE.equals(person.getGender())
                                        && Period.between(person.getDateOfBirth(), NOW).getYears() >= FEMALE_RETIREE_AGE)
                                        || (MALE.equals(person.getGender())
                                        && Period.between(person.getDateOfBirth(), NOW).getYears() >= MALE_RETIREE_AGE) ? 2 : 3), person)))
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .limit(500)
                .collect(Collectors.toList());
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    public static void task15() {
        System.out.println("Task 15: ");
        List<Flower> flowers = Util.getFlowers();

        final double WATER_SERVICE_FOR_5_YEARS = 1.39 * 0.001 * 365 * 5;
        final String GLASS = "Glass", ALUMINUM = "Aluminum", STEEL = "Steel";

        System.out.println("Total cost of maintaining all plants: " +
            flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                .sorted(Comparator.comparing(Flower::getPrice).thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(flower -> Pattern.compile("^[C-S].*").matcher(flower.getCommonName()).matches())
                .filter(Flower::isShadePreferred)
                .peek(flower -> flower.getFlowerVaseMaterial()
                        .stream()
                        .filter(material -> GLASS.equals(material) || ALUMINUM.equals(material) || STEEL.equals(material))
                        .collect(Collectors.toList()))
                .map(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * WATER_SERVICE_FOR_5_YEARS)
                .mapToDouble(f -> f)
                .sum());
    }

    public static void task16() {
        System.out.println("\nTask 16: ");
        List<Student> students = Util.getStudents();

        final int CHILDREN_AGE = 18;

        System.out.println("Students younger than 18:" +
                students.stream()
                        .filter(s -> s.getAge() < CHILDREN_AGE)
                        .sorted(Comparator.comparing(Student::getSurname))
                        .toList());
    }

    public static void task17() {
        System.out.println("\nTask 17: ");
        List<Student> students = Util.getStudents();

        System.out.println("Unique groups:");
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        System.out.println("\nTask 18: ");
        List<Student> students = Util.getStudents();

        System.out.println("Faculty - middle age:");
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(Student::getAge)))
                .forEach((faculty, avgAge) -> System.out.printf("%s : %.2f\n", faculty, avgAge));
    }

    public static void task19() {
        System.out.println("\nTask 19: ");
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        final int MARK = 4;
        final String GROUP = "C-2";//[P-1, C-2, M-3, C-4, M-1, C-3, M-2, P-3, P-4, C-1, P-2]

        System.out.println("Students from *group with exam3 > 4: ");
        examinations.stream()
                .filter(e -> e.getExam3() > MARK)
                .map(e -> students.stream()
                        .filter(s -> e.getStudentId() == s.getId() && GROUP.equals(s.getGroup()))
                        .findAny()
                        .map(Student::getSurname)
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    }

    public static void task20() {
        System.out.println("\nTask 20: ");
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        System.out.println("The faculty with max assessment by exam1: " +
                students.stream()
                        .collect(Collectors.groupingBy(Student::getFaculty))
                        .entrySet()
                        .stream()
                        .map(m -> Map.entry(m.getKey(), m.getValue()
                                        .stream()
                                        .map(s -> examinations.stream()
                                                .filter(e -> e.getStudentId() == s.getId())
                                                .map(Examination::getExam1)
                                                .findAny()
                                        )
                                        .filter(Optional::isPresent)
                                        .collect(Collectors.summarizingInt(Optional::get))
                                        .getAverage()

                                )
                        )
                        .max((a, b) -> (int) (a.getValue() - b.getValue()))
                        .get()
                        .getKey());
    }

    public static void task21() {
        System.out.println("\nTask 21: ");
        List<Student> students = Util.getStudents();

        System.out.println("Students amount in groups");
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()))
                .forEach((faculty, studentsCount) -> System.out.printf("%s: %d\n", faculty, studentsCount));
    }

    public static void task22() {
        System.out.println("\nTask 22: ");
        List<Student> students = Util.getStudents();

        System.out.println("Group and MinAge:");
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.minBy((a, b) -> a.getAge() - b.getAge())))
                .forEach((faculty, studentsMinAge) -> System.out.println(faculty + " " + studentsMinAge.get().getAge()));
    }
}
