import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day8 {

    public static void parseInputPart1() {
        String[][] input;
        String[] instructions;
        Map<String, String> network = new HashMap<>();

        try {
            input = readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        instructions = input[0][0].split("");

        for (int i = 1; i < input.length; i++) {
            network.put(input[i][0], input[i][1]);
            //System.out.println(input[i][1]);
        }

        String node = "AAA";
        int steps = 0;
        int instructionPointer = 0;

        while (!node.equals("ZZZ")) {
            int instruction = Objects.equals(instructions[instructionPointer], "L") ? 0 : 1;
            node = network.get(node).split(",")[instruction].trim();
            instructionPointer++;
            steps++;
            if (instructionPointer >= instructions.length) {
                instructionPointer = 0;
            }
        }

        System.out.println("Number of steps: " + steps);


    }

    private static String[][] readFile() throws IOException {
        List<String> input = Files.readAllLines(Paths.get("inputs/input_day8.txt"));

        String instructions = input.get(0);

        String[][] network = new String[input.size() - 1][2];

        network[0][0] = instructions;

        for (int i = 2; i < input.size(); i++) {
            String node = input.get(i).split("=")[0].trim();
            String nextNodes = input.get(i).split("=")[1].trim().replaceAll("[()]", "");
            network[i - 1][0] = node;
            network[i - 1][1] = nextNodes;
        }

        return network;
    }
}
