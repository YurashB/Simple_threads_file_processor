import mvc.bussines_logic.RunnableFileProcessor;
import mvc.controllers.DiffTimeController;

public class Main {
    public static void main(String[] args) {
        String[] files = {
                "src/resources/2600-11.txt",
                "src/resources/2600-12.txt",
                "src/resources/2600-13.txt",
                "src/resources/2600-14.txt",
                "src/resources/2600-15.txt",
                "src/resources/2600-16.txt",
                "src/resources/2600-17.txt",
                "src/resources/2600-18.txt",
                "src/resources/2600-19.txt",

        };
        DiffTimeController controller = new DiffTimeController(files);
        controller.showDiffTime();

    }
}
