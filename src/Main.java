import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Day1Function();

    }

    private static void Day1Function() {
        int sum = 0;

        try {
            FileReader fileReader = new FileReader("input.txt");
            BufferedReader in = new BufferedReader(fileReader);

            while (in.ready()) {
                String line = in.readLine();
                int s = Day1.parseInput(line);
                System.out.printf("%s: %d\n", line, s);
                sum += s;
            }

        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The sum is: " + sum);
    }
}