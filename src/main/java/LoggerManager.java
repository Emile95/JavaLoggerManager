package LoggerManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;
import java.io.IOException;

import LoggerManager.Configuration.LoggerProfile;
import LoggerManager.Exception.*;

public class LoggerManager {
    private HashMap<Class<?>,LoggerExpression<?>> loggerExpressions;

    public LoggerManager(ArrayList<LoggerProfile> profiles) {
        loggerExpressions = new HashMap<Class<?>,LoggerExpression<?>>();

        ArrayList<String> filePaths = new ArrayList<String>();
        for(LoggerProfile profile : profiles) {
            for(LoggerExpressionConfiguration<?> expressionConfiguration : profile.loggerExpressionConfigurations){
                Class<?> c = expressionConfiguration.getType();
                if(loggerExpressions.containsKey(c))
                    throw new DuplicateTypeException(c);
                if(filePaths.contains(expressionConfiguration.filePath))
                    throw new DuplicateFilePathException(expressionConfiguration.filePath);
                filePaths.add(expressionConfiguration.filePath);
                loggerExpressions.put(c, expressionConfiguration.CreateLoggerExpression());
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
