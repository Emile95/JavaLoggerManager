package loggerManager;

import java.util.ArrayList;

public class LoggerProfile {
    ArrayList<LoggerExpressionConfiguration<?>> loggerExpressionConfigurations;

    public LoggerProfile() {
        loggerExpressionConfigurations = new ArrayList<LoggerExpressionConfiguration<?>>();
    }

    protected <T> LoggerExpressionConfiguration<T> createLogger(Class<T> c) {
        LoggerExpressionConfiguration<T> exp = new LoggerExpressionConfiguration<T>(c);
        loggerExpressionConfigurations.add(exp);
        return exp;
    }
}
