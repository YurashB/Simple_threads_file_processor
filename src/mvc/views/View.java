package mvc.views;

import mvc.model.Word;

public class View {
    public void printMostCommonWord(String word, int amount) {
        System.out.println("Найчастіше вживане слово");
        System.out.println("Слово: " + word);
        System.out.println("Кількість: " + amount);
    }

    public void printError() {
        System.out.println("Упс, одного з переданих файлів не існує");
    }

    public void printDiffTime(long oneThreadTime, long sevThreadTime, Word oneThreadWord, Word sevThreadWord){
        System.out.println("Найчастіше вживане слово одного потоку vs багатьох");
        System.out.println("Слово (one thread): " + oneThreadWord.getValue());
        System.out.println("Слово (several threads): " + sevThreadWord.getValue());
        System.out.println("Кількість: (one thread): " + oneThreadWord.getAmount());
        System.out.println("Кількість: (several threads): " +  sevThreadWord.getAmount());
        System.out.println("Час (one thread): " + oneThreadTime / 1_000_000 + " мс");
        System.out.println("Час (sev thread): " + sevThreadTime / 1_000_000  + " мс");
        System.out.println("Різниця часу (one - sev): " + ((oneThreadTime - sevThreadTime) / 1_000_000) + " мс");
    }
}
