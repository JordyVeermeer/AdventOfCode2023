import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 {


    public static int parseInput(String input) {
        // input examples:
        // 1abc2
        // pqr3stu8vwx
        // a1b2c3d4e5f
        // treb7uchet

        List<Integer> numbers = new ArrayList<Integer>();


        // 1. loop over all characters from input string and check if they can be parsed to integer.
        Arrays.stream(input.split("")).forEach((value) -> {
            try {
                int num = Integer.parseInt(value);
                numbers.add(num);
            } catch (NumberFormatException e) {
                // character is not an integer
                // do nothing just skip
            }
        });

        // 2. Make two-digit number with first and last digits

        if (numbers.size() == 0) {
            return 0;
        } else if (numbers.size() == 1) {
            return Integer.valueOf(String.valueOf(numbers.get(0)).repeat(2));
        } else {
            String first = String.valueOf(numbers.get(0));
            String last = String.valueOf(numbers.get(numbers.size() - 1));

            return Integer.valueOf(first + last);
        }

    }
}
