import java.text.SimpleDateFormat;

import loggerManager.LoggerProfile;

class Profile extends LoggerProfile {
    public Profile() {
        createLogger(UserLogEntry.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forBeginLine((data,context) -> context.getCurrentFileIndex() + " -- " + context.getCurrentEntryIndex() + " -- ")
            .forLine(line -> {
                line.forValue((data,context) -> "-- Entry Time -- : " + context.getEntryDate());
            })
            .forLine(line -> {
                line.forValue((data,context) -> data.pseudo);
            })
            .forLine(line -> {
                line.forValue((data,context) -> data.password);
            })
            .forLine(line -> {
                line
                    .forTab((data,context) -> data.pseudo)
                    .forTab((data,context) -> data.password);
            });
    }
}
