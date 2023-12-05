import java.util.Map;

public class Day2 {

    public static int parseInputPart1(String input) {
        // Grab ID from first part of line
        int id = Integer.parseInt(input.split(":")[0].split(" ")[1]);
        // Put rest of line (data) in variable
        String data = input.split(":")[1];

        Map<String, Integer> allowedCubes = Map.of(
                "red", 12,
                "blue", 14,
                "green", 13
        );

        // 1. Separate per set
        String[] sets = data.split(";");

        // 2. Loop over sets and check if possible per set
        for (String set : sets) {
            String[] colors = set.split(",");
            for (String color : colors) {
                String colorStripped = color.strip();
                String[] s = colorStripped.split(" ");

                // Return 0 if any color violates rules
                if (allowedCubes.get(s[1]) < Integer.parseInt(s[0])) {
                    return 0;
                }
            }
        }
        return id;
    }
}
