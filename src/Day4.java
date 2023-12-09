import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    public static void parseInputPart1() {
        List<String> lines = readFilePart1();
        int totalPoints = 0;

        for (String s : lines) {
            List<String> winningNumbers = Arrays.asList(s.split("\\|")[0].split(" "));
            List<String> yourNumbers = Arrays.stream(s.split("\\|")[1].trim().split(" ")).filter(num -> !num.isEmpty()).toList();

            int cardTotal = 0;

            System.out.println("Winning numbers: " + Arrays.toString(winningNumbers.toArray()));
            System.out.println("Your numbers: " + Arrays.toString(yourNumbers.toArray()));

            for (String n : yourNumbers) {
                System.out.println("Checking " + n);
                for (String nW : winningNumbers) {
                    if (nW.equals(n)) {
                        System.out.println(n + " found in winningNumbers");
                        if (cardTotal == 0){
                            cardTotal++;
                        } else {
                            cardTotal = cardTotal * 2;
                        }
                    }
                }
            }

            totalPoints += cardTotal;
        }

        System.out.println("Total points: " + totalPoints);
    };

    public static void parseInputPart2() {
        List<String> lines = readFilePart2();


    }

    private static List<String> readFilePart1() {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader("inputs/input_day4.txt"));

            while (in.ready()) {
                String line = in.readLine().split(":")[1].trim();
                lines.add(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    private static List<String> readFilePart2() {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader("inputs/input_day4.txt"));

            while (in.ready()) {
                String line = in.readLine();
                lines.add(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}
