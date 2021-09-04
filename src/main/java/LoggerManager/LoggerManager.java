package LoggerManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.io.IOException;

import LoggerManager.Exception.*;
import LoggerManager.Configuration.*;

public class LoggerManager {
    private HashMap<Class<?>,LoggerExpression<?>> loggerExpressions;

    public LoggerManager(Consumer<LoggerManagerConfiguration> consumer) {
        loggerExpressions = new HashMap<Class<?>,LoggerExpression<?>>();
        LoggerManagerConfiguration config = new LoggerManagerConfiguration();
        consumer.accept(config);

        ArrayList<String> filePaths = new ArrayList<String>();
        for(LoggerProfile profile : config.profiles) {
            for(LoggerExpressionConfiguration<?> expressionConfiguration : profile.loggerExpressionConfigurations){
                Class<?> c = expressionConfiguration.getType();
                if(loggerExpressions.containsKey(c))
                    throw new DuplicateTypeException(c);
                if(filePaths.contains(expressionConfiguration.filePath))
                    throw new DuplicateFilePathException(expressionConfiguration.filePath);
                filePaths.add(expressionConfiguration.filePath);
                loggerExpressions.put(c, expressionConfiguration.createLoggerExpression());
            }
        }
        filePaths.forEach(filePath -> {
            try {
                File file = new File(filePath);
                file.createNewFile();
            } catch (IOException e) {
                throw new CreateLogFileException(filePath);
            }
        });
    }

    public <T> void log(T data) {
        LoggerExpression<T> loggerExpression = (LoggerExpression<T>)(loggerExpressions.get(data.getClass()));
        loggerExpression.log(data);
    }
}