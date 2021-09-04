import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import loggerManager.LoggerManager;
import loggerManager.configuration.*;
import loggerManager.exception.*;

import java.text.SimpleDateFormat;
import java.lang.RuntimeException;

class LogEntry {
    public String type;
    public String msg;

    public LogEntry(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}

class LogEntry2 {
    public int code;
    public String msg;
    
    public LogEntry2(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

class Profile extends LoggerProfile {
    public Profile() {
        createLogger(LogEntry.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forEntry(o -> o.type + " : " + o.msg);
    }
}

class ProfileCopy extends LoggerProfile {
    public ProfileCopy() {
        createLogger(LogEntry.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forEntry(o -> o.type + " : " + o.msg);
    }
}

class ProfileSameFilePath extends LoggerProfile {
    public ProfileSameFilePath() {
        createLogger(LogEntry2.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forEntry(o -> o.code + " : " + o.msg);
    }
}

class Profile2 extends LoggerProfile {
    public Profile2() {
        createLogger(LogEntry2.class)
            .forFilePath("log2.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forEntry(o -> Integer.toString(o.code) + " : " + o.msg);
    }
}

class ProfileWithWrongFilePath extends LoggerProfile {
    public ProfileWithWrongFilePath() {
        createLogger(LogEntry.class)
            .forFilePath("asdasdasdasdasd\\log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forEntry(o -> o.type + " : " + o.msg);
    }
}

public class TestCase1 {
    @Test                                               
    @DisplayName("Create LoggerManager with 1 Profile")   
    void CreateLoggerManager1() {
        LoggerManager loggerManager = new LoggerManager((config) -> {
            config.addProfile(new Profile());
        });

        loggerManager.log(new LogEntry("Normal", "CreateLoggerManager1"));
    }

    @Test                                               
    @DisplayName("Create LoggerManager with 2 Profile")   
    void CreateLoggerManager2() {
        LoggerManager loggerManager = new LoggerManager((config) -> {
            config.addProfile(new Profile());
            config.addProfile(new Profile2());
        });

        loggerManager.log(new LogEntry("Normal", "CreateLogManager2"));
        loggerManager.log(new LogEntry2(1, "CreateLoggerManager2"));
    }

    @Test                                               
    @DisplayName("Create LoggerManager with 2 same class")   
    void CreateLoggerManager3() {
        try {
            LoggerManager loggerManager = new LoggerManager((config) -> {
                config.addProfile(new Profile());
                config.addProfile(new ProfileCopy());
            });
            throw new RuntimeException("DuplicateTypeException not throwed");
        } catch(DuplicateTypeException e) {} 
        catch(Exception e) {
            throw new RuntimeException("DuplicateTypeException not throwed");
        }
    }

    @Test                                               
    @DisplayName("Create LoggerManager with 2 same filePath")   
    void CreateLoggerManager4() {
        try {
            LoggerManager loggerManager = new LoggerManager((config) -> {
                config.addProfile(new Profile());
                config.addProfile(new ProfileSameFilePath());
            });
            throw new RuntimeException("DuplicateFilePathException not throwed");
        } catch(DuplicateFilePathException e) {} 
        catch(Exception e) {
            throw new RuntimeException("DuplicateFilePathException not throwed");
        }
    }

    @Test                                               
    @DisplayName("Create LoggerManager with wrong file path")   
    void CreateLoggerManager5() {
        try {
            LoggerManager loggerManager = new LoggerManager((config) -> {
                config.addProfile(new ProfileWithWrongFilePath());
            });
            throw new RuntimeException("CreateLogFileException not throwed");
        } catch(CreateLogFileException e) {} 
        catch(Exception e) {
            throw new RuntimeException("CreateLogFileException not throwed");
        }
    }
}

