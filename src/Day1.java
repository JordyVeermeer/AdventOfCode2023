import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Day1 {


    public static int parseInputPart1(String input) {
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

    public static String parseInputPart2(String input) {
        // input examples:
        // two1nine --> t2o1n9e --> 29
        // eightwothree --> e8t2ot3e --> 83
        // abcone2threexyz --> abco1e2t3exyz --> 13
        // xtwone3four
        // 4nineeightseven2
        // zoneight234
        // 7pqrstsixteen
        Map<String, Integer> numberMap = Map.of(
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
        );

        // 1. replace words representing numbers ('one', 'two',..) with 'o1e', 't2o'
        // 'twone' --> 't2one' --> 't2o1e'
        // 2. use previous function to parse number from value

        StringBuilder result = new StringBuilder();
        int pointer = 0;
        StringBuilder temp = new StringBuilder();

        String[] keys = numberMap.keySet().toArray(new String[]{});

        for (int i = 0; i < input.length(); i++) {
            // add char to temp
            temp.append(input.toCharArray()[i]);

            // check if temp contains number in string form
            for (int y = 0; y < keys.length; y++) {
                String s = keys[y];
                int index = temp.indexOf(s);
                if (index != -1) {
                    // replace string with integer value if found & append to result
                    temp.replace(index, index + s.length(), String.valueOf(numberMap.get(s)));
                    result.append(temp);
                    // clear temp & set pointer 1 space back
                    temp.setLength(0);
                    i--;
                    break;
                }
            }
        }

        result.append(temp);

        return result.toString();
    }
}
