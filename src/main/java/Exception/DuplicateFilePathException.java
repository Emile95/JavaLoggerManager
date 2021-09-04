package LoggerManager.Exception;

import java.lang.RuntimeException;

public class DuplicateFilePathException extends RuntimeException {
    public DuplicateFilePathException(String filePath) {
        super("Duplicate file path : " + filePath);
    }
}