public class ConstructorChaining {

    ConstructorChaining() {
        this("Hello from second constructor");
        System.out.println("First constructor executed");
    }

    ConstructorChaining(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        new ConstructorChaining();
    }
}