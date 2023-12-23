import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //Day1Part1();
        //Day2Part2();
        //Day3.parseInputPart1();
        //Day3.parseInputPart2();
        //Day4.parseInputPart1();
        //Day4.parseInputPart2();
        //Day5.parseInputPart1();
        //Day5.parseInputPart2(); // 231522441 too high? 50855035 right answer
        Day6.parseInputPart1();
    }

    private static void Day1Part1() {
        int sum = 0;

        try {
            FileReader fileReader = new FileReader("inputs/input_day1.txt");
            BufferedReader in = new BufferedReader(fileReader);

            while (in.ready()) {
                String line = in.readLine();
                String parsedLine = Day1.parseInputPart2(line);
                int s = Day1.parseInputPart1(parsedLine);
                System.out.printf("%s: %d\n", parsedLine, s);
                sum += s;
            }

        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The sum is: " + sum);
    }

    private static void Day1Part2() {
        // adjusted Day1FunctionPart1()
    }

    private static void Day2Part1() {
        int sum = 0;

        try {
            FileReader fileReader = new FileReader("inputs/input_day2.txt");
            BufferedReader in = new BufferedReader(fileReader);

            while(in.ready()) {
                String line = in.readLine();
                int result = Day2.parseInputPart1(line);
                System.out.printf("%s: %d%n", line, result);
                sum += result;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("The sum is: " + sum);

    }

    private static void Day2Part2() {
        int sum = 0;

        try {
            FileReader fileReader = new FileReader("inputs/input_day2.txt");
            BufferedReader in = new BufferedReader(fileReader);

            while(in.ready()) {
                String line = in.readLine();
                int result = Day2.parseInputPart2(line);
                System.out.printf("%s --> result: %d%n", line, result);
                sum += result;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("The sum is: " + sum);
    }


}