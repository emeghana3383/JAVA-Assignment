import java.util.*;
import java.util.stream.*;

public class streamapi {

    // ================= STUDENT =================
    static class Student {
        int id;
        String name;
        int age;
        String gender;
        String dept;
        int year;
        double percent;

        Student(int id, String name, int age, String gender,
                String dept, int year, double percent) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.dept = dept.toLowerCase();
            this.year = year;
            this.percent = percent;
        }

        public String toString() {
            return name + " | " + dept + " | Age:" + age + " | " + percent + "%";
        }
    }

    // ================= QUERY ENGINE =================
    static void process(String q, List<Student> students) {

        q = q.toLowerCase();

        Stream<Student> stream = students.stream();

        // 🔹 FILTERS (dynamic)
        if (q.contains("male")) {
            stream = stream.filter(s -> s.gender.equalsIgnoreCase("Male"));
        }
        if (q.contains("female")) {
            stream = stream.filter(s -> s.gender.equalsIgnoreCase("Female"));
        }
        if (q.contains("computer")) {
            stream = stream.filter(s -> s.dept.contains("computer"));
        }
        if (q.contains("electronic")) {
            stream = stream.filter(s -> s.dept.contains("electronic"));
        }
        if (q.contains("mechanical")) {
            stream = stream.filter(s -> s.dept.contains("mechanical"));
        }
        if (q.contains("textile")) {
            stream = stream.filter(s -> s.dept.contains("textile"));
        }

        // 🔹 NUMERIC CONDITIONS
        if (q.contains("above")) {
            int val = extract(q);
            stream = stream.filter(s -> s.percent > val);
        }
        if (q.contains("below")) {
            int val = extract(q);
            stream = stream.filter(s -> s.percent < val);
        }
        if (q.contains("after")) {
            int year = extract(q);
            stream = stream.filter(s -> s.year > year);
        }

        // ================= OPERATIONS =================

        // Highest
        if (q.contains("highest") || q.contains("top")) {
            stream.max(Comparator.comparingDouble(s -> s.percent))
                    .ifPresent(System.out::println);
            return;
        }

        // Youngest
        if (q.contains("youngest")) {
            stream.min(Comparator.comparingInt(s -> s.age))
                    .ifPresent(System.out::println);
            return;
        }

        // Average age
        if (q.contains("average age")) {
            double avg = stream.mapToInt(s -> s.age).average().orElse(0);
            System.out.println("Average Age: " + avg);
            return;
        }

        // Average percentage
        if (q.contains("average percentage")) {
            double avg = stream.mapToDouble(s -> s.percent).average().orElse(0);
            System.out.println("Average %: " + avg);
            return;
        }

        // Count
        if (q.contains("count") || q.contains("how many")) {
            long count = stream.count();
            System.out.println("Count: " + count);
            return;
        }

        // Group by gender
        if (q.contains("group") && q.contains("gender")) {
            Map<String, List<Student>> res =
                    students.stream().collect(Collectors.groupingBy(s -> s.gender));
            System.out.println(res);
            return;
        }

        // Default: print filtered students
        List<Student> result = stream.collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("❌ No results found");
        } else {
            result.forEach(System.out::println);
        }
    }

    static int extract(String q) {
        for (String w : q.split(" ")) {
            try {
                return Integer.parseInt(w);
            } catch (Exception ignored) {}
        }
        return 0;
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Student> s = Arrays.asList(
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

        System.out.println("💡 Ask anything (type exit to stop)");

        while (true) {
            System.out.print("\n👉 ");
            String q = sc.nextLine();
            if (q.equalsIgnoreCase("exit")) break;

            process(q, s);
        }
    }
}