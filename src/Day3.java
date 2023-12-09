import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void parseInputPart1() {
        char[][] input = readFileIntoCharArray();
        int sum = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }

        String number = "";
        boolean keepNumber = false;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                // check if digit
                if (Character.isDigit(input[i][j])) {
                    number += input[i][j];
                    // Check for adjacent symbol
                    if (checkIfDigitValid(input, i, j)) {
                        keepNumber = true;
                    }
                } else {
                    if (keepNumber && !number.isEmpty()) {
                        System.out.println(number + " is a part number");
                        sum += Integer.parseInt(number);
                        keepNumber = false;
                    }
                    number = "";
                }

            }
        }
        System.out.println("Sum is: " + sum);
    }

    public static void parseInputPart2() {
        char[][] input = readFileIntoCharArray();

        // Look for '*'
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {

            }
        }
    }

    private static boolean checkIfDigitValid(char[][] input, int i, int y) {
        // i = rows
        // y = columns
        return input[i + 1][y] != '.' || input[i - 1][y] != '.' || input[i + 1][y + 1] != '.' || input[i - 1][y + 1] != '.' || input[i - 1][y - 1] != '.' || input[i + 1][y - 1] != '.'
                || !isDigitOrDot(input[i][y + 1]) || !isDigitOrDot(input[i][y - 1]);
    }

    private static boolean isDigitOrDot(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private static char[][] readFileIntoCharArray() {
        char[][] charArray;
        List<String> lines = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("inputs/input_day3.txt");
            BufferedReader in = new BufferedReader(fileReader);

            while (in.ready()) {
                String line = in.readLine();
                lines.add(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int rows = lines.size() + 2;
        int cols = lines.get(1).length() + 2;

        charArray = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < cols; y++) {
                charArray[i][y] = '.';
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            line.getChars(0, line.length(), charArray[i + 1], 1);
        }

        return charArray;
    }
}
