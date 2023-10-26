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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static by.clevertec.util.Const.ALUMINUM;
import static by.clevertec.util.Const.BMW;
import static by.clevertec.util.Const.Black;
import static by.clevertec.util.Const.Blue;
import static by.clevertec.util.Const.CHILDREN_AGE;
import static by.clevertec.util.Const.CONSCRIPT_AGE;
import static by.clevertec.util.Const.COST_KILOGRAM;
import static by.clevertec.util.Const.Cherokee;
import static by.clevertec.util.Const.Chrysler;
import static by.clevertec.util.Const.Civic;
import static by.clevertec.util.Const.Dodge;
import static by.clevertec.util.Const.FEMALE;
import static by.clevertec.util.Const.FEMALE_RETIREE_AGE;
import static by.clevertec.util.Const.GLASS;
import static by.clevertec.util.Const.GMC;
import static by.clevertec.util.Const.Green;
import static by.clevertec.util.Const.HOSPITAL;
import static by.clevertec.util.Const.HUNGARIAN;
import static by.clevertec.util.Const.INDONESIAN;
import static by.clevertec.util.Const.JAGUAR;
import static by.clevertec.util.Const.JAPANESE;
import static by.clevertec.util.Const.LEXUS;
import static by.clevertec.util.Const.MALE;
import static by.clevertec.util.Const.MALE_RETIREE_AGE;
import static by.clevertec.util.Const.MAX_COST;
import static by.clevertec.util.Const.MAX_MASS;
import static by.clevertec.util.Const.MAX_YEAR;
import static by.clevertec.util.Const.MIN_MASS;
import static by.clevertec.util.Const.OCEANIA;
import static by.clevertec.util.Const.Red;
import static by.clevertec.util.Const.STEEL;
import static by.clevertec.util.Const.Toyota;
import static by.clevertec.util.Const.VIN_59;
import static by.clevertec.util.Const.WHITE;
import static by.clevertec.util.Const.Yellow;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nTask 1: ");
        System.out.println("Yang animals(10..20) sorted by age in 3d zoo: " + task1());

        System.out.println("\nTask 2: ");
        System.out.println("Animals from Japanese (female in upper case): " + task2());

        System.out.println("\nTask 3: ");
        System.out.println("Animals older than 30, distinct, origin start with 'A': " + task3());

        System.out.println("\nTask 4: ");
        System.out.println("Animal count: " + task4());

        System.out.println("\nTask 5: ");
        System.out.println("Is anyone from 'Hungarian': " + task5());

        System.out.println("\nTask 6: ");
        System.out.println("There is other genders (no 'Male' or 'Female'): " + task6());

        System.out.println("\nTask 7: ");
        System.out.println("No one from Oceania: " + task7());

        System.out.println("\nTask 8: ");
        System.out.println("The oldest animal in sorted top 100: " + task8());

        System.out.println("\nTask 9: ");
        System.out.println("The shortest animal bread: " + task9());

        System.out.println("\nTask 10: ");
        System.out.println("All animals sum age: " + task10());

        System.out.println("\nTask 11: ");
        System.out.println("Indonesian animals average age: " + task11());

        System.out.println("\nTask 12: ");
        System.out.println("Students whom can go to academy(limit 200): " + task12());

        System.out.println("\nTask 13: ");
        System.out.println("First 500 evacuation people: " + task13());

        System.out.println("\nTask 14: ");
        task14();

        System.out.println("\nTask 15: ");
        task15();

        System.out.println("\nTask 16: ");
        task16();

        System.out.println("\nTask 17: ");
        task17();

        System.out.println("\nTask 18: ");
        task18();

        System.out.println("\nTask 19: ");
        task19();

        System.out.println("\nTask 20: ");
        System.out.println("Faculty with max average assessment in exam1: " + task20(Util.getStudents(), Util.getExaminations()));

        System.out.println("\nTask 21: ");
        task21();

        System.out.println("\nTask 22: ");
        task22();
    }

    public static List<Animal> task1() {
        List<Animal> animals = Util.getAnimals();

        final int MIN_AGE = 10;
        final int MAX_AGE = 20;
        final int GROUP_COUNT = 7;
        AtomicInteger counter = new AtomicInteger();

        return animals.stream()
                .filter(a -> a.getAge() >= MIN_AGE && a.getAge() < MAX_AGE)
                .sorted(Comparator.comparing(Animal::getAge))
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / GROUP_COUNT))
                .get(2);
    }

    public static List<String> task2() {
        List<Animal> animals = Util.getAnimals();

        return animals.stream()
                .filter(a -> JAPANESE.equals(a.getOrigin()))
                .peek(a -> a.setBread(a.getBread().toUpperCase()))
                .filter(a -> FEMALE.equals(a.getGender()))
                .map(Animal::toString)
                .collect(Collectors.toList());
    }

    public static List<String> task3() {
        List<Animal> animals = Util.getAnimals();

        final String START_SIM = "A";
        final int PREF_AGE = 30;

        return animals.stream()
                .filter(a -> a.getAge() > PREF_AGE)
                .map(Animal::getOrigin)
                .distinct()
                .filter(o -> o.startsWith(START_SIM))
                .collect(Collectors.toList());
    }

    public static long task4() {
        List<Animal> animals = Util.getAnimals();

        return animals.stream()
                .filter(a -> FEMALE.equals(a.getGender()))
                .count();
    }

    public static boolean task5() {
        List<Animal> animals = Util.getAnimals();

        final int MIN_AGE = 20;
        final int MAX_AGE = 20;

        return animals.stream()
                .filter(a -> a.getAge() >= MIN_AGE && a.getAge() <= MAX_AGE)
                .anyMatch(a -> HUNGARIAN.equals(a.getOrigin()));
    }

    public static boolean task6() {
        List<Animal> animals = Util.getAnimals();

        return animals.stream()
                .anyMatch(a -> !FEMALE.equals(a.getGender()) && !MALE.equals(a.getGender()));
    }

    public static boolean task7() {
        List<Animal> animals = Util.getAnimals();

        return animals.stream()
                .noneMatch(a -> OCEANIA.equals(a.getOrigin()));
    }

    public static int task8() {
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
        List<Animal> animals = Util.getAnimals();

        return animals.stream()
                .collect(Collectors.summarizingInt(Animal::getAge))
                .getSum();
    }

    public static double task11() {
        List<Animal> animals = Util.getAnimals();


        return animals.stream()
                .filter(a -> INDONESIAN.equals(a.getOrigin()))
                .collect(Collectors.summarizingInt(Animal::getAge))
                .getAverage();
    }

    public static List<Person> task12() {
        List<Person> persons = Util.getPersons();

        return persons.stream()
                .filter(p -> MALE.equals(p.getGender())
                        && p.getDateOfBirth().plusYears(CHILDREN_AGE).isBefore(LocalDate.now())
                        && p.getDateOfBirth().plusYears(CONSCRIPT_AGE).isAfter(LocalDate.now())
                )
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .collect(Collectors.toList());
    }

    public static List<Person> task13() {
        List<House> houses = Util.getHouses();

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
        final Map<Integer, Predicate<Car>> GROUP_CAR = new LinkedHashMap<>() {
            {
                put(1, (car) -> JAGUAR.equals(car.getCarMake()) || WHITE.equals(car.getColor()));
                put(2, (car) -> car.getMass() < MIN_MASS && (BMW.equals(car.getCarMake()) || LEXUS.equals(car.getCarMake()) ||
                        Chrysler.equals(car.getCarMake()) || Toyota.equals(car.getCarMake())));
                put(3, (car) -> (Black.equals(car.getColor()) && car.getMass() > MAX_MASS) ||
                        GMC.equals(car.getCarMake()) || Dodge.equals(car.getCarMake()));
                put(4, (car) -> car.getReleaseYear() < MAX_YEAR || Civic.equals(car.getCarModel()) ||
                        Cherokee.equals(car.getCarModel()));
                put(5, (car) -> !(Yellow.equals(car.getColor()) || Red.equals(car.getColor()) ||
                        Green.equals(car.getColor()) || Blue.equals(car.getColor()))
                        || car.getPrice() > MAX_COST);
                put(6, (car) -> car.getVin() != null && car.getVin().contains(VIN_59));
                put(7, (car) -> true);
            }
        };

        double sum =
                cars.stream()
                        .map(car -> Map.entry(GROUP_CAR.entrySet().stream()
                                .filter(entry -> entry.getValue().test(car))
                                .findFirst()
                                .map(Map.Entry::getKey)
                                .get(), car))
                        //  stream pairs <NumberOfGroup(1-7), Car>
                        .filter(entry -> entry.getKey() < 7)
                        .collect(Collectors.groupingBy(Map.Entry::getKey,
                                Collectors.mapping(Map.Entry::getValue, Collectors.toList())))
                        //  Map <NumberOfGroup(1-6), List<Car> >
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .map(Map.Entry::getValue)
                        .map(carsIngroup -> Map.entry(carsIngroup,
                                COST_KILOGRAM * carsIngroup.stream()
                                        .collect(Collectors.summarizingInt(Car::getMass))
                                        .getSum()))
                        .peek(valueGroupCarCost -> System.out.printf("%.2f $\n", valueGroupCarCost.getValue()))

                        .mapToDouble(groupCarCost -> groupCarCost.getKey().stream()
                                .collect(Collectors.summarizingDouble(Car::getPrice))
                                .getSum())
                        .sum();

        System.out.printf("Total company profit %.2f $\n", sum);
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();

        final double WATER_SERVICE_FOR_5_YEARS = 1.39 * 0.001 * 365 * 5;
        final String PLANTS_NAMES_START_PATTERN = "^[C-S].*";

        final Predicate<Flower> FILTER =
            (flower) -> flower.isShadePreferred() &&
                flower.getFlowerVaseMaterial()
                    .stream()
                    .anyMatch(material -> GLASS.equals(material) || ALUMINUM.equals(material) || STEEL.equals(material));

        System.out.printf("Total cost of maintaining all plants: %.2f $\n",
                flowers.stream()
                        .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                        .sorted(Comparator.comparing(Flower::getPrice).thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                        .filter(flower -> Pattern.compile(PLANTS_NAMES_START_PATTERN).matcher(flower.getCommonName()).matches())
                        .filter(FILTER)
                        .map(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * WATER_SERVICE_FOR_5_YEARS)
                        .mapToDouble(f -> f)
                        .sum());
    }

    public static void task16() {
        List<Student> students = Util.getStudents();

        final int CHILDREN_AGE = 18;

        System.out.println("Students younger than 18:" +
                students.stream()
                        .filter(s -> s.getAge() < CHILDREN_AGE)
                        .sorted(Comparator.comparing(Student::getSurname))
                        .toList());
    }

    public static void task17() {
        List<Student> students = Util.getStudents();

        System.out.println("Unique groups:");
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();

        System.out.println("Faculty - average age:");
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(Student::getAge)))
                .forEach((faculty, avgAge) -> System.out.printf("%s : %.2f\n", faculty, avgAge));
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        final int MARK = 4;
        final String GROUP = "C-2";

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

    public static String task20(List<Student> students, List<Examination> examinations) {

        return students.stream()
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
                .getKey();
    }

    public static void task21() {
        List<Student> students = Util.getStudents();

        System.out.println("Students amount in groups");
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()))
                .forEach((faculty, studentsCount) -> System.out.printf("%s: %d\n", faculty, studentsCount));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();

        System.out.println("Group and MinAge:");
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.minBy((a, b) -> a.getAge() - b.getAge())))
                .forEach((faculty, studentsMinAge) -> System.out.println(faculty + " " + studentsMinAge.get().getAge()));
    }
}
