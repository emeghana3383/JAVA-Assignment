import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class KycFormValidator {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfInputs = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < numberOfInputs; i++) {

            String input = scanner.nextLine();
            String[] dates = input.split(" ");

            LocalDate signupDate = LocalDate.parse(dates[0], FORMATTER);
            LocalDate currentDate = LocalDate.parse(dates[1], FORMATTER);

            printKycRange(signupDate, currentDate);
        }

        scanner.close();
    }

    private static void printKycRange(LocalDate signupDate, LocalDate currentDate) {

    // Case 1: current date before signup
    if (currentDate.isBefore(signupDate)) {
        System.out.println("No range");
        return;
    }

    // Get anniversary in current year
    LocalDate anniversary = signupDate.withYear(currentDate.getYear());

    // If anniversary is after current date → shift to previous year
    if (anniversary.isAfter(currentDate)) {
        anniversary = anniversary.minusYears(1);
    }

    // Calculate window
    LocalDate startDate = anniversary.minusDays(30);
    LocalDate endDate = anniversary.plusDays(30);

    // If current date is before window start → No range
    if (currentDate.isBefore(startDate)) {
        System.out.println("No range");
        return;
    }

    // Cannot go into future
    if (endDate.isAfter(currentDate)) {
        endDate = currentDate;
    }

    System.out.println(
            startDate.format(FORMATTER) + " " +
            endDate.format(FORMATTER)
    );
}
}