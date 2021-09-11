import java.text.SimpleDateFormat;

import loggerManager.LoggerProfile;

class ProfileWrongPath extends LoggerProfile {
    public ProfileWrongPath() {
        createLogger(Integer.class)
            .forFilePath("\\sdfsdfsdf\\sdfsdfsdfsdf\\1og.log")
            .forEntryDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"))
            .forLine(line -> {
                line.forValue((data,context) -> "line 1 : + " + Integer.toString(data));
            });
    }
}

