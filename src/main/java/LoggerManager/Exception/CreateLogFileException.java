package loggerManager.exception;

import java.lang.RuntimeException;

public class CreateLogFileException extends RuntimeException {
    public CreateLogFileException(String filePath) {
        super("Error when creating file : " + filePath);
    }
}
