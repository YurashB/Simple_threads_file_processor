package mvc.bussines_logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class RunnableFileProcessor implements Runnable {
    private final String fileName;
    private final HashSet<String> set = new HashSet<>();
    private final HashMap<String, Integer> map = new HashMap<>();

    public RunnableFileProcessor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        loadStatistic();
        Statistic statistic = Statistic.getInstance();
        statistic.loadDataToMap(map);
    }

    private void loadStatistic() {
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

