import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import loggerManager.LoggerManager;
import loggerManager.LoggerProfile;
import loggerManager.exception.*;

import java.text.SimpleDateFormat;
import java.lang.RuntimeException;

public class CreateObjectTests {
    @Test                                               
    @DisplayName("Catch duplicate Type Exception ")   
    void catchDuplicateTypeException() throws Exception {
        try {
            LoggerManager loggerManager = new LoggerManager((config) -> {
                config.addProfile(new Profile());
                config.addProfile(new Profile());
            });
        } catch(DuplicateTypeException e) { }
    }

    @Test                                               
    @DisplayName("Catch duplicate file path Exception ")   
    void catchDuplicateFilePathException() throws Exception {
        try {
            LoggerManager loggerManager = new LoggerManager((config) -> {
                config.addProfile(new Profile());
                config.addProfile(new ProfileSameFilePath());
            });
        } catch(DuplicateFilePathException e) { }
    }

    @Test                                               
    @DisplayName("Catch create log file Exception ")   
    void catchCreateLogFileException() throws Exception {
        try {
            LoggerManager loggerManager = new LoggerManager((config) -> {
                config.addProfile(new ProfileWrongPath());
            });
        } catch(CreateLogFileException e) { }
    }

    @Test                                               
    @DisplayName("Catch not mapped type Exception ")   
    void catchNotMappedTypeException() throws Exception {
        try {
            LoggerManager loggerManager = new LoggerManager((config) -> {
                config.addProfile(new Profile());
            });
            loggerManager.log(5);
        } catch(NotMappedTypeException e) { }
    }
}

