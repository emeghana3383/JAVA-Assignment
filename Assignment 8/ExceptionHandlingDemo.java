class ExceptionOne extends Exception {
    public ExceptionOne(String message) {
        super(message);
    }
}

class ExceptionTwo extends Exception {
    public ExceptionTwo(String message) {
        super(message);
    }
}

class ExceptionThree extends Exception {
    public ExceptionThree(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {

    // Method that throws all three exceptions
    static void testExceptions(int choice)
            throws ExceptionOne, ExceptionTwo, ExceptionThree {

        if (choice == 1) {
            throw new ExceptionOne("Exception One occurred");
        } else if (choice == 2) {
            throw new ExceptionTwo("Exception Two occurred");
        } else if (choice == 3) {
            throw new ExceptionThree("Exception Three occurred");
        } else {
            // Force NullPointerException
            String str = null;
            System.out.println(str.length());
        }
    }

    public static void main(String[] args) {

        try {
            testExceptions(1);   // Change 1,2,3,4 to test
        }
        catch (Exception e) {   // Single catch handles all
            System.out.println("Caught Exception: " + e);
        }
        finally {
            System.out.println("Finally block executed");
        }
    }
}