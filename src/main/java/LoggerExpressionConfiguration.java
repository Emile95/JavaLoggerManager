package LoggerManager;

import java.text.DateFormat;
import java.util.function.Function;

public class LoggerExpressionConfiguration<T> {
    
    String filePath;
    private DateFormat entryDateFormat;
    private Function<T,String> entryExpression;

    private Class<T> type;
    public Class<T> getType() {
        return this.type;
    }

    public LoggerExpressionConfiguration(Class<T> type) {
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
    
    LoggerExpression<T> CreateLoggerExpression() {
        return new LoggerExpression<T>(filePath,entryDateFormat,entryExpression);
    }
}
