package loggerManager.exception;

import java.lang.RuntimeException;

public class DuplicateFilePathException extends RuntimeException {
    private String filePath;
    public String getFilePath() {
        return filePath;
    }

    public DuplicateFilePathException(String filePath) {
        super("Duplicate file path : " + filePath);
        this.filePath = filePath;
    }
}
