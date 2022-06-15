package mvc.validators;

import java.io.File;

public class Validator {
    public static boolean isFileExist(String fileName){
        File file = new File(fileName);
        return file.exists();
    }
}
