import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void parseInputPart1() {
        //List<String> symbols = List.of("*", "#", "+", "$", "+", "=", "/", "%", "@", "&", "#");
        int sum = 0;
        char[][] input = readFileIntoCharArray();
        String number = "";
        boolean keepNumber = false;

        for (int i = 1; i < input.length - 1; i++) {
            for (int j = 1; j < input[i].length - 1; j++) {
                if (input[i][j] >= '0' && input[i][j] <= '9') {
                    number += input[i][j];
                    if (checkIfDigitValid(input, i, j)){
                        keepNumber = true;
                    }
                } else {
                    if (keepNumber && !number.isEmpty()){
                        sum += Integer.parseInt(number);
                    }

                    keepNumber = false;
                    number = "";
                }

            }

            if (keepNumber && !number.isEmpty()){
                sum+=Integer.parseInt(number);
            }

            keepNumber = false;
        }

        System.out.println("Sum is: " + sum);

    };

    private static boolean checkIfDigitValid(char[][] input, int i, int y) {
        // i = rows
        // y = columns
        return input[i + 1][y] != '.' || input[i - 1][y] != '.' || input[i + 1][y + 1] != '.' || input[i - 1][y + 1] != '.' || input[i - 1][y - 1] != '.'
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
