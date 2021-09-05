package loggerManager;

import java.text.DateFormat;
import java.util.function.Function;

public class LoggerExpressionConfiguration<T> {
    
    String filePath;
    DateFormat entryDateFormat;
    Function<T,String> entryExpression;
    Class<T> type;

    LoggerExpressionConfiguration(Class<T> type) {
        this.type = type;
    }

    public LoggerExpressionConfiguration<T> forFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public LoggerExpressionConfiguration<T> forEntryDateFormat(DateFormat entryDateFormat) {
        this.entryDateFormat = entryDateFormat;
        return this;
    }

    public LoggerExpressionConfiguration<T> forEntry(Function<T,String> entryExpression) {
        this.entryExpression = entryExpression;
        return this;
    }

    LoggerExpression<T> createLoggerExpression() {
        return new LoggerExpression<>(filePath, entryDateFormat, entryExpression);
    }
}
