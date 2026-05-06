import java.util.*;
import java.util.stream.*;

public class main {

    // ===================== STUDENT CLASS =====================
    static class Student {
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

        public int getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getGender() { return gender; }
        public String getDepartment() { return department; }
        public int getYearOfEnrollment() { return yearOfEnrollment; }
        public double getPercentage() { return percentage; }

        @Override
        public String toString() {
            return name + " | " + department + " | " + percentage + "%";
        }
    }

    // ===================== SERVICE METHODS =====================
    static class StudentService {

        public static List<String> getAllDepartments(List<Student> students) {
            return students.stream()
                    .map(Student::getDepartment)
                    .distinct()
                    .collect(Collectors.toList());
        }

        public static Map<String, Long> countByGender(List<Student> students) {
            return students.stream()
                    .collect(Collectors.groupingBy(
                            Student::getGender,
                            Collectors.counting()
                    ));
        }

        public static Map<String, Double> avgAgeByGender(List<Student> students) {
            return students.stream()
                    .collect(Collectors.groupingBy(
                            Student::getGender,
                            Collectors.averagingInt(Student::getAge)
                    ));
        }

        public static Optional<Student> topStudent(List<Student> students) {
            return students.stream()
                    .max(Comparator.comparingDouble(Student::getPercentage));
        }

        public static List<Student> enrolledAfter(List<Student> students, int year) {
            return students.stream()
                    .filter(s -> s.getYearOfEnrollment() > year)
                    .collect(Collectors.toList());
        }

        public static Map<String, List<Student>> groupByDepartment(List<Student> students) {
            return students.stream()
                    .collect(Collectors.groupingBy(Student::getDepartment));
        }

        public static Map<String, Double> avgPercentByDept(List<Student> students) {
            return students.stream()
                    .collect(Collectors.groupingBy(
                            Student::getDepartment,
                            Collectors.averagingDouble(Student::getPercentage)
                    ));
        }

        public static List<Student> belowPercentage(List<Student> students, double percent) {
            return students.stream()
                    .filter(s -> s.getPercentage() < percent)
                    .collect(Collectors.toList());
        }
    }

    // ===================== QUERY PROCESSOR =====================
    static class QueryProcessor {

        public static void processQuery(String query, List<Student> students) {

            query = query.toLowerCase();

            if (query.contains("department")) {
                System.out.println(StudentService.getAllDepartments(students));
            }

            else if (query.contains("gender") && query.contains("count")) {
                System.out.println(StudentService.countByGender(students));
            }

            else if (query.contains("average age")) {
                System.out.println(StudentService.avgAgeByGender(students));
            }

            else if (query.contains("top") || query.contains("highest")) {
                StudentService.topStudent(students)
                        .ifPresent(System.out::println);
            }

            else if (query.contains("enrolled after")) {
                int year = extractNumber(query);
                System.out.println(StudentService.enrolledAfter(students, year));
            }

            else if (query.contains("group")) {
                System.out.println(StudentService.groupByDepartment(students));
            }

            else if (query.contains("average percentage")) {
                System.out.println(StudentService.avgPercentByDept(students));
            }

            else if (query.contains("below")) {
                int percent = extractNumber(query);
                System.out.println(StudentService.belowPercentage(students, percent));
            }

            else {
                System.out.println("❌ Sorry, I didn't understand.");
            }
        }

        private static int extractNumber(String query) {
            String[] words = query.split(" ");
            for (String word : words) {
                try {
                    return Integer.parseInt(word);
                } catch (Exception ignored) {}
            }
            return 0;
        }
    }

    // ===================== MAIN METHOD =====================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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

        System.out.println("💡 Ask any question (type 'exit' to stop)");

        while (true) {
            System.out.print("\n👉 Your Question: ");
            String query = sc.nextLine();

            if (query.equalsIgnoreCase("exit")) break;

            QueryProcessor.processQuery(query, students);
        }

        sc.close();
    }
}