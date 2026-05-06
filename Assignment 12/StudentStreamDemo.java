import java.util.*;
import java.util.stream.Collectors;

class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String department;
    private int yearOfEnrollment;
    private double percentage;

    public Student(int id, String name, int age, String gender,
                   String department, int yearOfEnrollment, double percentage) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfEnrollment = yearOfEnrollment;
        this.percentage = percentage;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }
    public int getYearOfEnrollment() { return yearOfEnrollment; }
    public double getPercentage() { return percentage; }

    @Override
    public String toString() {
        return name + " (" + department + ", " + percentage + "%)";
    }
}

public class StudentStreamDemo {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student(111, "Jiya Brein", 17, "Female", "Computer Science", 2018, 70.8),
                new Student(122, "Paul Niksui", 18, "Male", "Mechanical", 2016, 50.2),
                new Student(133, "Martin Theron", 17, "Male", "Electronic", 2017, 90.3),
                new Student(144, "Murali Gowda", 18, "Male", "Electrical", 2018, 80),
                new Student(155, "Nima Roy", 19, "Female", "Textile", 2016, 70),
                new Student(166, "Iqbal Hussain", 18, "Male", "Security", 2016, 70),
                new Student(177, "Manu Sharma", 16, "Male", "Chemical", 2018, 70),
                new Student(188, "Wang Liu", 20, "Male", "Computer Science", 2015, 80),
                new Student(199, "Amelia Zoe", 18, "Female", "Computer Science", 2016, 85),
                new Student(200, "Jaden Dough", 18, "Male", "Security", 2015, 82),
                new Student(211, "Jasna Kaur", 20, "Female", "Electronic", 2019, 83),
                new Student(222, "Nitin Joshi", 19, "Male", "Textile", 2016, 60.4),
                new Student(233, "Jyothi Reddy", 16, "Female", "Computer Science", 2015, 45.6),
                new Student(244, "Nicolus Den", 16, "Male", "Electronic", 2017, 95.8),
                new Student(255, "Ali Baig", 17, "Male", "Electronic", 2018, 88.4),
                new Student(266, "Sanvi Pandey", 17, "Female", "Electric", 2019, 72.4),
                new Student(277, "Anuj Chettiar", 18, "Male", "Computer Science", 2017, 57.5)
        );

        // 1. All departments
        System.out.println("Departments:");
        students.stream()
                .map(Student::getDepartment)
                .distinct()
                .forEach(System.out::println);

        // 2. Students enrolled after 2018
        System.out.println("\nStudents enrolled after 2018:");
        students.stream()
                .filter(s -> s.getYearOfEnrollment() > 2018)
                .map(Student::getName)
                .forEach(System.out::println);

        // 3. Male students in Computer Science
        System.out.println("\nMale students in Computer Science:");
        students.stream()
                .filter(s -> s.getGender().equals("Male") &&
                             s.getDepartment().equals("Computer Science"))
                .forEach(System.out::println);

        // 4. Count male and female
        System.out.println("\nCount by gender:");
        Map<String, Long> genderCount =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getGender, Collectors.counting()));
        System.out.println(genderCount);

        // 5. Average age by gender
        System.out.println("\nAverage age by gender:");
        Map<String, Double> avgAge =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getGender,
                                Collectors.averagingInt(Student::getAge)));
        System.out.println(avgAge);

        // 6. Highest percentage student
        System.out.println("\nTop student:");
        students.stream()
                .max(Comparator.comparing(Student::getPercentage))
                .ifPresent(System.out::println);

        // 7. Count by department
        System.out.println("\nStudents per department:");
        Map<String, Long> deptCount =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getDepartment, Collectors.counting()));
        System.out.println(deptCount);

        // 8. Average percentage by department
        System.out.println("\nAverage percentage by department:");
        Map<String, Double> avgDept =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getDepartment,
                                Collectors.averagingDouble(Student::getPercentage)));
        System.out.println(avgDept);

        // 9. Youngest male in Electronic
        System.out.println("\nYoungest male in Electronic:");
        students.stream()
                .filter(s -> s.getGender().equals("Male") &&
                             s.getDepartment().equals("Electronic"))
                .min(Comparator.comparing(Student::getAge))
                .ifPresent(System.out::println);

        // 10. Male & Female in Computer Science
        System.out.println("\nGender count in Computer Science:");
        Map<String, Long> csGender =
                students.stream()
                        .filter(s -> s.getDepartment().equals("Computer Science"))
                        .collect(Collectors.groupingBy(
                                Student::getGender, Collectors.counting()));
        System.out.println(csGender);
    }
}