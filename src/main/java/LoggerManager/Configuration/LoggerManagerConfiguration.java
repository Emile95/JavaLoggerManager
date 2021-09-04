package loggerManager.configuration;

import java.util.ArrayList;

public class LoggerManagerConfiguration {
    public ArrayList<LoggerProfile> profiles;
    
    public LoggerManagerConfiguration() {
        profiles = new ArrayList<LoggerProfile>();
    }

    public void addProfile(LoggerProfile profile) {
        profiles.add(profile);
    }
}
