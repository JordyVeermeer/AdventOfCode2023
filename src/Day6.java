import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    public static void parseInputPart1() {
        int[][] input;

        try {
            input = readFile();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        int[] possibleWinsPerRace = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            int possibleWins = checkRace(input[i][0], input[i][1]);
            possibleWinsPerRace[i] = possibleWins;
        }

        int sum = Arrays.stream(possibleWinsPerRace).reduce(1, (a, b) -> a * b);

        System.out.printf("Sum: %d%n", sum);

    }

    private static int checkRace(int time, int recordDistance) {
        int possibleWins = 0;

        for (int i = 0; i <= time; i++) {
            int distanceTraveled = i * (time - i);
            if (distanceTraveled > recordDistance) possibleWins++;
        }

        return possibleWins;
    }

    private static int[][] readFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("inputs/input_day6.txt"));

        String[] times = lines.get(0).split(":")[1].trim().split(" +");
        String[] recordDistances = lines.get(1).split(":")[1].trim().split(" +");

        int[][] res = new int[times.length][recordDistances.length];

        for (int i = 0; i < times.length; i++) {
            res[i] = new int[] {Integer.parseInt(times[i]), Integer.parseInt(recordDistances[i])};
        }

        return res;
    }
}
