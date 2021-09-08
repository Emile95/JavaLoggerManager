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

    /**
     * Define the file path of your log gile
     * @param filePath Path of the file
     * @return LoggerExpressionConfiguration to continue configuration
    */
    public LoggerExpressionConfiguration<T> forFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * Define the date format for when you create an entry in the log file
     * @param entryDateFormat Date format for each entry
     * @return LoggerExpressionConfiguration to continue configuration
    */
    public LoggerExpressionConfiguration<T> forEntryDateFormat(DateFormat entryDateFormat) {
        this.entryDateFormat = entryDateFormat;
        return this;
    }

    /**
     * Define the expression that will map your object into the entry line
     * @param entryExpression Expression who create the entry line
     * @return LoggerExpressionConfiguration to continue configuration
    */
    public LoggerExpressionConfiguration<T> forEntry(Function<T,String> entryExpression) {
        this.entryExpression = entryExpression;
        return this;
    }

    LoggerExpression<T> createLoggerExpression() {
        return new LoggerExpression<>(filePath, entryDateFormat, entryExpression);
    }
}
