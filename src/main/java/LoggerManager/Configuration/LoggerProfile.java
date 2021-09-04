package loggerManager.configuration;

import java.util.ArrayList;

import loggerManager.LoggerExpressionConfiguration;

public class LoggerProfile {
    public ArrayList<LoggerExpressionConfiguration<?>> loggerExpressionConfigurations;

    public LoggerProfile() {
        loggerExpressionConfigurations = new ArrayList<LoggerExpressionConfiguration<?>>();
    }

    protected <T> LoggerExpressionConfiguration<T> createLogger(Class<T> c) {
        LoggerExpressionConfiguration<T> exp = new LoggerExpressionConfiguration<T>(c);
        loggerExpressionConfigurations.add(exp);
        return exp;
    }
}
