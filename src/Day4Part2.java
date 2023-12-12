import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4Part2 {

    public static Map<Integer, Integer> d = new HashMap<>();

    public static int parse(List<String> inputLines) {
        for (int i = 0; i < inputLines.size(); i++) {
            d.put(i, 1);
        }
        for (int i = 0; i < inputLines.size(); i++) {

            String inputLine = inputLines.get(i);
            String[] splitted = inputLine.split(":");
            String[] cardNumbers = splitted[1].split("\\|");

            List<Integer> st = new ArrayList<>();
            String[] winningNumbers = cardNumbers[0].trim().split("\\s+");
            String[] currNumbers = cardNumbers[1].trim().split("\\s+");
            for (String currNum : currNumbers) {
                st.add(Integer.parseInt(currNum));
            }
            int count = 0;
            for (String winningNum : winningNumbers) {
                if (st.contains(Integer.parseInt(winningNum))) {
                    count++;
                }
            }

            for (int j = i + 1; j < i + count + 1; j++) {
                d.put(j, d.getOrDefault(j, 0) + d.getOrDefault(i, 0));
            }
        }
        int ans = 0;
        for (int value : d.values()) {
            ans += value;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("inputs/input_day4.txt");
        List<String> lines = Files.readAllLines(filePath);
        System.out.println(parse(lines));
    }
}