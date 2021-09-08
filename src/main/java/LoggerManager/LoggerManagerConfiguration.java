package loggerManager;

import java.util.ArrayList;

public class LoggerManagerConfiguration {
    ArrayList<LoggerProfile> profiles;
    
    LoggerManagerConfiguration() {
        profiles = new ArrayList<LoggerProfile>();
    }

    /**
     * Add an logger profile in your LoggerManager configuration
     * @param data your logger profile
    */
    public void addProfile(LoggerProfile profile) {
        profiles.add(profile);
    }
}
