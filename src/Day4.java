import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
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
        Map<Integer, Integer> cards = new HashMap<>();

        // First card will always be 1
        //cards.put(1, 1);

        for (String s : lines) {
            int cardNumber = parseCardNumber(s);
            int matchingNumbers = 0;

            // Add current card to the map
            if (cards.containsKey(cardNumber)) {
                cards.put(cardNumber, cards.get(cardNumber) + 1);
            } else {
                cards.put(cardNumber,1);
            }

            System.out.printf("Checking - %s%n", s);

            List<String> winningNumbers = Arrays.asList(s.split(":")[1].trim().split("\\|")[0].split(" "));
            List<String> yourNumbers = Arrays.stream(s.split(":")[1].trim().split("\\|")[1].trim().split(" ")).filter(num -> !num.isEmpty()).toList();

            // Check for matching numbers
            for (String n : yourNumbers) {
                for (String nW : winningNumbers) {
                    if (nW.equals(n)) {
                        matchingNumbers++;
                    }
                }
            }

            System.out.println(matchingNumbers + " matching numbers found.");

            // Add copies to the map
            for (int i = cardNumber + 1; i < cardNumber + matchingNumbers + 1; i++) {
                if (cards.containsKey(i)) {
                    int previous = cards.get(i);
                    int newValue = previous + cards.get(cardNumber);
                    cards.put(i, newValue);
                    System.out.println(cards.get(cardNumber) + " copies of Card " + i + " added.");
                } else {
                    cards.put(i, 1);
                    System.out.println(cards.get(cardNumber) + " copies of Card " + i + " added.");
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : cards.entrySet()) {
            System.out.printf("%d copies of Card %d%n", entry.getValue(), entry.getKey());
        }

        int totalCards = cards.values().stream().reduce(0, Integer::sum);
        System.out.println("Total scratchcards: " + totalCards);
    }

    private static int parseCardNumber(String line) {
        String leftPart = line.split(":")[0];

        return Integer.parseInt(leftPart.substring(4).trim());
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
