package mvc.bussines_logic;

import mvc.model.Word;

import java.util.Map;

public class MultiThreadProcessor {

    private final String[] files;


    public MultiThreadProcessor(String[] files) {
        this.files = files;
    }

    public Word getMostCommonWord() {
        RunnableFileProcessor[] runnableFileProcessors = getRunnableFileProcessorArray();
        Thread[] threads = startAllThreads(runnableFileProcessors);

        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Map.Entry<String, Integer> mostCommon = null;
        for (Map.Entry<String, Integer> word : Statistic.getInstance().getMap().entrySet()) {
            if (mostCommon == null || word.getValue().compareTo(mostCommon.getValue()) > 0) {
                mostCommon = word;
            }
        }

        return mostCommon != null ? new Word(mostCommon.getKey(), mostCommon.getValue()) : new Word(" ", 0);
    }


    private RunnableFileProcessor[] getRunnableFileProcessorArray() {
        int N = files.length;

        RunnableFileProcessor[] runnableFileProcessors = new RunnableFileProcessor[N];
        for (int i = 0; i < files.length; i++) {
            runnableFileProcessors[i] = new RunnableFileProcessor(files[i]);
        }

        return runnableFileProcessors;
    }

    private Thread[] startAllThreads(RunnableFileProcessor[] runnableFileProcessors) {
        int N = files.length;
        Thread[] threads = new Thread[N];

        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(runnableFileProcessors[i]);
            threads[i] = thread;
            thread.start();
        }

        return threads;
    }
}
