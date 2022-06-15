package mvc.bussines_logic;

import mvc.model.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class OneThreadFileProcessor {
    private final HashSet<String> set = new HashSet<>();
    private final HashMap<String, Integer> map = new HashMap<>();
    private final String[] filesName;

    public OneThreadFileProcessor(String[] filesName) {
        this.filesName = filesName;
    }

    public Word getMostCommonWord() {
        for (String fileName: filesName){
            getUsedWords(fileName);
        }

        Map.Entry<String, Integer> mostCommon = null;

        for (Map.Entry<String, Integer> word : map.entrySet()) {
            if (mostCommon == null || word.getValue().compareTo(mostCommon.getValue()) > 0) {
                mostCommon = word;
            }
        }

        return mostCommon != null ? new Word(mostCommon.getKey(), mostCommon.getValue()) : new Word(" ", 0);
    }

    private void getUsedWords(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                for (String word : line.toLowerCase().split(" ")) {
                    word = getResolvedWord(word);

                    if (word.length() != 0) {
                        if (!set.contains(word)) {
                            set.add(word);
                            map.put(word, 1);
                        } else {
                            map.put(word, map.get(word) + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Smth went wrong");
        }
    }

    private String getResolvedWord(String word) {
        while (true) {
            if (!word.isEmpty() && !Character.isLetter(word.charAt(0))) {
                word = word.substring(1);
            } else if (!word.isEmpty() && !Character.isLetter(word.charAt(word.length() - 1))) {
                word = word.substring(0, word.length() - 1);
            } else {
                return word;
            }
        }
    }
}

