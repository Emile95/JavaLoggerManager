import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import loggerManager.LoggerManager;
import loggerManager.LoggerProfile;

public class UseObjectTests {
    @Test                                               
    @DisplayName("Log an new entry")   
    void catchDuplicateTypeException() throws Exception {
        LoggerManager loggerManager = new LoggerManager((config) -> {
            config.addProfile(new Profile());
        });

        loggerManager.log(new UserLogEntry("fefeto","password"));
    }
}
