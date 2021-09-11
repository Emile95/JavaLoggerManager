import java.text.SimpleDateFormat;

import loggerManager.LoggerProfile;

class Profile extends LoggerProfile {
    public Profile() {
        createLogger(UserLogEntry.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forLine(line -> {
                line.forValue(data -> "line 1 : + " + data.pseudo);
            })
            .forLine(line -> {
                line.forValue(data -> "line 2 : + " + data.password);
            })
            .forLine(line -> {
                line
                    .forTab(data -> data.pseudo)
                    .forTab(data -> data.password);
            });
    }
}
