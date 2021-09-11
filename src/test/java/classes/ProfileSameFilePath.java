import java.text.SimpleDateFormat;

import loggerManager.LoggerProfile;

class ProfileSameFilePath extends LoggerProfile {
    public ProfileSameFilePath() {
        createLogger(Integer.class)
            .forFilePath("log.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forLine(line -> {
                line.forValue((data,context) -> "line 1 : + " + Integer.toString(data));
            });
    }
}
