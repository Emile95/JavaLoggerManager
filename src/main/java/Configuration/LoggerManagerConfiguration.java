package LoggerManager.Configuration;

import java.util.function.Consumer;

import LoggerManager.LoggerManager;

public class LoggerManagerConfiguration {
    
    private LoggerManagerConfigurationExpression expession;

    public LoggerManagerConfiguration(Consumer<LoggerManagerConfigurationExpression> consumer) {
        expession = new LoggerManagerConfigurationExpression();
        consumer.accept(expession);
    }

    public LoggerManager createLoggerManager() {
        return new LoggerManager(expession.profiles);
    }
}
