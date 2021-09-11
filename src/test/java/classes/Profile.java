import java.text.SimpleDateFormat;

import loggerManager.LoggerProfile;

class Profile extends LoggerProfile {
    public Profile() {
        createLogger(UserLogEntry.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forLine(line -> {
                line.forValue((data,context) -> context.getCurrentFileIndex() + " -- " + context.getCurrentEntryIndex() + " -- " + "-- Entry Time -- : " + context.getEntryDate());
            })
            .forLine(line -> {
                line.forValue((data,context) -> context.getCurrentFileIndex() + " -- " + context.getCurrentEntryIndex() + " -- " + data.pseudo);
            })
            .forLine(line -> {
                line.forValue((data,context) -> context.getCurrentFileIndex() + " -- " + context.getCurrentEntryIndex() + " -- " + data.password);
            })
            .forLine(line -> {
                line
                    .forTab((data,context) -> context.getCurrentFileIndex() + " -- " + context.getCurrentEntryIndex() + " -- ")
                    .forTab((data,context) -> data.pseudo)
                    .forTab((data,context) -> data.password);
            });
    }
}
