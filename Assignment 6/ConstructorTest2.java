public class ConstructorTest2 {

    ConstructorTest2(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {

        ConstructorTest2[] arr = new ConstructorTest2[5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ConstructorTest2("Object " + (i + 1) + " created");
        }
    }
}