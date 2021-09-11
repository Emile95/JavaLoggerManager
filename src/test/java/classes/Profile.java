import java.text.SimpleDateFormat;

import loggerManager.LoggerProfile;

class Profile extends LoggerProfile {
    public Profile() {
        createLogger(UserLogEntry.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forEntry(o -> o.pseudo + " : " + o.password);
    }
}
