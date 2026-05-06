public class ConstructorTest {

    ConstructorTest(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        ConstructorTest[] arr = new ConstructorTest[5];

        // No objects created → no constructor calls
        System.out.println("Array created, but no objects initialized");
    }
}