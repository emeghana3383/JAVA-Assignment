import java.util.Arrays;

public class VampireNumbers {

    public static void main(String[] args) {
        int count = 0;
        int number = 10;

        while (count < 100) {
            if (isVampire(number)) {
                System.out.println(number);
                count++;
            }
            number++;
        }
    }

    private static boolean isVampire(int num) {
        String numStr = String.valueOf(num);

        if (numStr.length() % 2 != 0) return false;

        int half = numStr.length() / 2;
        char[] numChars = numStr.toCharArray();
        Arrays.sort(numChars);

        int start = (int) Math.pow(10, half - 1);
        int end = (int) Math.pow(10, half);

        for (int i = start; i < end; i++) {
            if (num % i == 0) {
                int j = num / i;

                if (String.valueOf(j).length() != half) continue;

                // both should not end with zero
                if (i % 10 == 0 && j % 10 == 0) continue;

                char[] fangChars = (String.valueOf(i) + String.valueOf(j)).toCharArray();
                Arrays.sort(fangChars);

                if (Arrays.equals(numChars, fangChars)) {
                    return true;
                }
            }
        }
        return false;
    }
}