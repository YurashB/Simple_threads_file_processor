package mvc.controllers;

import mvc.bussines_logic.MultiThreadProcessor;
import mvc.bussines_logic.OneThreadFileProcessor;
import mvc.model.Word;
import mvc.validators.Validator;
import mvc.views.View;

public class DiffTimeController implements Controller {

    private final String[] files;
    private final View view = new View();

    public DiffTimeController(String[] files) {
        this.files = files;
    }

    public void showDiffTime(){
        if (isFilesValidate()) {
            MultiThreadProcessor multiThreadProcessor = new MultiThreadProcessor(files);
            OneThreadFileProcessor oneThreadFileProcessor = new OneThreadFileProcessor(files);

            long start = System.nanoTime();
            Word mostComWordSevThread = multiThreadProcessor.getMostCommonWord();
            long finish = System.nanoTime();
            long sevThreadTime = finish - start;

            start = System.nanoTime();
            Word mostComWordOneThread = oneThreadFileProcessor.getMostCommonWord();
            finish = System.nanoTime();
            long oneThreadTime = finish - start;

            view.printDiffTime(oneThreadTime,sevThreadTime,mostComWordOneThread,mostComWordSevThread);
        } else {
            view.printError();
        }
    }

    private boolean isFilesValidate() {
        for (String file : files) {
            if (!Validator.isFileExist(file)) {
                return false;
            }
        }
        return true;
    }
}
