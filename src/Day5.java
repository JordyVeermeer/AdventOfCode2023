import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day5 {

    public static void parseInputPart1() {
        List<String> maps = new ArrayList<>();
        Long lowestLocation = null;

        try {
            maps = readFile();

        } catch(IOException e) {
            System.err.println("File was not found");
        }

        Long[] seeds = Arrays.stream(maps.get(0).split(":")[1].trim().split(" ")).map(Long::valueOf).toArray(Long[]::new);

        Long[][] seedToSoil = parseMap(maps.get(1));
        Long[][] soilToFertilizer = parseMap(maps.get(2));
        Long[][] fertilizerToWater = parseMap(maps.get(3));
        Long[][] waterToLight = parseMap(maps.get(4));
        Long[][] lightToTemperature = parseMap(maps.get(5));
        Long[][] temperatureToHumidity = parseMap(maps.get(6));
        Long[][] humidityToLocation = parseMap(maps.get(7));

        for (Long seed : seeds) {

            // seed to soil
            Long soil = correspondingNumber(seedToSoil, seed);
            Long fertilizer = correspondingNumber(soilToFertilizer, soil);
            Long water = correspondingNumber(fertilizerToWater, fertilizer);
            Long light = correspondingNumber(waterToLight, water);
            Long temperature = correspondingNumber(lightToTemperature, light);
            Long humidity = correspondingNumber(temperatureToHumidity, temperature);
            Long location = correspondingNumber(humidityToLocation, humidity);

            System.out.println("Seed number " + seed + " corresponds to location " + location);
            if (Objects.isNull(lowestLocation)) {
                lowestLocation = location;
            } else if (lowestLocation > location) {
                lowestLocation = location;
            }
        }

        System.out.println("Lowest location: " + lowestLocation);

    }

    private static Long correspondingNumber(Long[][] mapping, Long num) {
        Long correspondingNumber = num;

        for (Long[] map : mapping) {
            if (num >= map[1] && num < map[1] + map[2]) {
                correspondingNumber = num + (map[0] - map[1]);
                break;
            }
        }

        return correspondingNumber;
    }

    private static Long[][] parseMap(String map) {
        String[] lines = map.split("\n");
        /*System.out.println("lines: ");
        Arrays.stream(lines).forEach(System.out::println);*/
        Long[][] result = new Long[lines.length - 1][3];

        for (int i = 1; i < lines.length; i++) {
            String[] numbers = lines[i].split(" ");

            for (int j = 0; j < numbers.length; j++) {
                result[i - 1][j] = Long.parseLong(numbers[j]);
            }
        }

        Comparator<Long[]> comparator = Comparator.comparingLong((Long[] s) -> s[1]);

        Arrays.sort(result, comparator);

        //Arrays.stream(result).forEach(a -> System.out.println(Arrays.toString(a)));

        return result;
    }

    private static List<String> readFile() throws IOException {
        List<String> maps = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("inputs/input_day5.txt"));

        StringBuilder l = new StringBuilder();

        for (String line : lines) {
            if (!line.isEmpty()) {
                l.append(line);
                l.append("\n");
            } else if (line.isEmpty() && !l.isEmpty()) {
                maps.add(l.toString());
                l.setLength(0);
            }
        }

        if (!l.isEmpty()) {
            maps.add(l.toString());
        }

        maps.forEach(System.out::println);

        return maps;
    }
}
