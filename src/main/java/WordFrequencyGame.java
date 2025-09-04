import modle.WordFrequency;

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";
    public String getResult(String inputStr) {

        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        }
        try {
            List<WordFrequency> frequencies = countFrequencies(words);
            frequencies.sort(Comparator.comparingInt(WordFrequency::count).reversed());
        } catch (Exception e) {
            return "Calculate Error";
        }

        return inputStr;
    }

    // 用Stream直接计数，简化算法
    private List<WordFrequency> countFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new WordFrequency(e.getKey(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        List<String> inputList = new ArrayList<>();
        for (String s : words) {
            inputList.add(s);
        }
        //get the map for the next step of sizing the same word
        Map<String, List<String>> map = new HashMap<>();
        for (String input1 : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input1)) {
                ArrayList arr = new ArrayList<>();
                arr.add(input1);
                map.put(input1, arr);
            } else {
                map.get(input1).add(input1);
            }
        }

        return map;
    }


}
