package loggerManager.exception;

import java.lang.RuntimeException;

public class CreateLogFileException extends RuntimeException {
    private String filePath;
    public String getFilePath() {
        return filePath;
    }

    public CreateLogFileException(String filePath) {
        super("Error when creating file : " + filePath);
        this.filePath = filePath;
    }
}
