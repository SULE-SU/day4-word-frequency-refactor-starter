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
            return composeOutput(frequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    // 用Stream直接计数，简化算法
    private List<WordFrequency> countFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new WordFrequency(e.getKey(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    // 抽取输出拼接方法
    private String composeOutput(List<WordFrequency> frequencies) {
        return frequencies.stream()
                .map(wf -> wf.word() + " " + wf.count())
                .collect(Collectors.joining("\n"));
    }


}
