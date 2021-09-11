package loggerManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import loggerManager.exception.*;

import java.io.IOException;

public class LoggerManager {
    private HashMap<Class<?>,LoggerExpression<?>> loggerExpressions;

    public LoggerManager(Consumer<LoggerManagerConfiguration> consumer) {
        loggerExpressions = new HashMap<Class<?>,LoggerExpression<?>>();
        LoggerManagerConfiguration config = new LoggerManagerConfiguration();
        consumer.accept(config);

        ArrayList<String> filePaths = new ArrayList<String>();
        for(LoggerProfile profile : config.profiles) {
            for(LoggerExpressionConfiguration<?> expressionConfiguration : profile.loggerExpressionConfigurations){
                Class<?> c = expressionConfiguration.type;
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

    /**
     * Create a new entry line into a log file depending of the type of your object
     * @param data Your object used to create the entry line
    */
    public <T> void log(T data) {
        if(!loggerExpressions.containsKey(data.getClass()))
            throw new NotMappedTypeException(data.getClass());
        LoggerExpression<T> loggerExpression = (LoggerExpression<T>)(loggerExpressions.get(data.getClass()));
        loggerExpression.log(data);
    }
}
