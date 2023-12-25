import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7 {

    public static void parseInput(boolean withJoker) {
        String[][] input;
        String[][] inputWithValue;

        Map<String, Integer> cards = new HashMap<>();
        cards.put("A", 14);
        cards.put("K", 13);
        cards.put("Q", 12);
        cards.put("J", 11);
        cards.put("T", 10);
        cards.put("9", 9);
        cards.put("8", 8);
        cards.put("7", 7);
        cards.put("6", 6);
        cards.put("5", 5);
        cards.put("4", 4);
        cards.put("3", 3);
        cards.put("2", 2);

        // Read input text file
        try {
            input = readFile();
            inputWithValue = new String[input.length][3];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Check values for each hand
        for (int i = 0; i < input.length; i++) {
            System.out.println("Checking " + input[i][0]);
            int value = checkHand(input[i][0], withJoker);

            inputWithValue[i][0] = input[i][0];
            inputWithValue[i][1] = input[i][1];
            inputWithValue[i][2] = String.valueOf(value);
        }

        // Only for part 2: replace value of a joker to be the lowest value
        cards.replace("J", 1);

        // Sort inputWithValue by value and then by strongest card
        Comparator<String[]> comparatorByValue = Comparator.comparingInt(a -> Integer.parseInt(a[2]));

        Comparator<String[]> comparatorByStrongestCard = (a, b) -> {
            String[] aSplit = a[0].split("");
            String[] bSplit = b[0].split("");
            for (int i = 0; i < aSplit.length; i++) {
                if (cards.get(aSplit[i]) > cards.get(bSplit[i])) return 1;
                if (cards.get(aSplit[i]) < cards.get(bSplit[i])) return -1;
            }
            return 0;
        };

        Arrays.sort(inputWithValue, comparatorByValue.thenComparing(comparatorByStrongestCard));

        // Calculate & print result
        int sum = 0;

        for (int i = 0; i < inputWithValue.length; i++) {
            System.out.println(Arrays.toString(inputWithValue[i]));
            sum += Integer.parseInt(inputWithValue[i][1]) * (i + 1);
        }

        System.out.println("Sum: " + sum);
    }

    private static int checkHand(String hand, boolean withJoker) {
        final int FiveOfAKind = 7, FourOfAKind = 6, FullHouse = 5, ThreeOfAKind = 4, TwoPair = 3, OnePair = 2, HighCard = 1;

        Map<Character, Integer> mappedHand = new HashMap<>();

        // Map hand string
        for (char c : hand.toCharArray()) {
            if (mappedHand.containsKey(c)) {
                mappedHand.put(c, mappedHand.get(c) + 1);
            } else {
                mappedHand.put(c, 1);
            }
        }

        for (char c : mappedHand.keySet()) {
            System.out.printf("%d instances of %s%n", mappedHand.get(c), c);
        }

        // Handle jokers
        if (withJoker && mappedHand.containsKey('J')) {
            Map.Entry<Character, Integer> max = null;
            for (Map.Entry<Character, Integer> entry : mappedHand.entrySet()) {
                if (entry.getKey() != 'J' && (max == null || max.getValue() < entry.getValue())) {
                    max = entry;
                }
            }

            if (mappedHand.get('J') != 5) {
                System.out.printf("Adding %d jokers to %s%n", mappedHand.get('J'), max.getKey());
                mappedHand.replace(max.getKey(), max.getValue() + mappedHand.get('J'));
                mappedHand.remove('J');
                for (Map.Entry<Character, Integer> entry : mappedHand.entrySet()) {
                    System.out.println(entry.toString());
                }
            }
        }

        // Return hand value
        switch (mappedHand.size()) {
            case 1 -> {
                System.out.printf("%s: Five of a kind%n", hand);
                return FiveOfAKind;
            }
            case 2 -> {
                if (mappedHand.containsValue(4)) {
                    System.out.printf("%s: Four of a kind%n", hand);
                    return FourOfAKind;
                } else {
                    System.out.printf("%s: Full house%n", hand);
                    return FullHouse;
                }
            }
            case 3 -> {
                if (mappedHand.containsValue(3)) {
                    System.out.printf("%s: Three of a kind%n", hand);
                    return ThreeOfAKind;
                } else {
                    System.out.printf("%s: Two pair%n", hand);
                    return TwoPair;
                }
            }
            case 4 -> {
                System.out.printf("%s: One pair%n", hand);
                return OnePair;
            }
            case 5 -> {
                System.out.printf("%s: High card%n", hand);
                return HighCard;
            }
            default -> {
                System.out.printf("%s: Something went wrong%n", hand);
                return 0;
            }
        }
    }

    private static String[][] readFile() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("inputs/input_day7.txt"));

        String[][] res = new String[input.size()][2];

        for (int i = 0; i < input.size(); i++) {
            res[i][0] = input.get(i).split(" ")[0].trim();
            res[i][1] = input.get(i).split(" ")[1].trim();
        }

        return res;
    }
}
