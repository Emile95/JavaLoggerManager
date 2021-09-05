package loggerManager;

import java.util.ArrayList;

public class LoggerManagerConfiguration {
    ArrayList<LoggerProfile> profiles;
    
    LoggerManagerConfiguration() {
        profiles = new ArrayList<LoggerProfile>();
    }

    public void addProfile(LoggerProfile profile) {
        profiles.add(profile);
    }
}
