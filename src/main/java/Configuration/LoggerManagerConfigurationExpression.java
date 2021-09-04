package LoggerManager.Configuration;

import java.util.ArrayList;

public class LoggerManagerConfigurationExpression {
    ArrayList<LoggerProfile> profiles;
    
    public LoggerManagerConfigurationExpression() {
        profiles = new ArrayList<LoggerProfile>();
    }

    public void addProfile(LoggerProfile profile) {
        profiles.add(profile);
    }
}
