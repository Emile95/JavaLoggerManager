package loggerManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class LoggerExpressionConfiguration<Data> {
    
    String filePath;
    DateFormat entryDateFormat;
    private ArrayList<LineExpression<Data>> lineExpressions;
    Class<Data> type;

    LoggerExpressionConfiguration(Class<Data> type) {
        lineExpressions = new ArrayList<LineExpression<Data>>();
        this.type = type;
    }

    /**
     * Define the file path of your log gile
     * @param filePath Path of the file
     * @return LoggerExpressionConfiguration to continue configuration
    */
    public LoggerExpressionConfiguration<Data> forFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * Define the date format for when you create an entry in the log file
     * @param entryDateFormat Date format for each entry
     * @return LoggerExpressionConfiguration to continue configuration
    */
    public LoggerExpressionConfiguration<Data> forEntryDateFormat(DateFormat entryDateFormat) {
        this.entryDateFormat = entryDateFormat;
        return this;
    }

    /**
     * Define the expression that will map your object into the entry line
     * @param entryExpression Expression who create the entry line
     * @return LoggerExpressionConfiguration to continue configuration
    */
    public LoggerExpressionConfiguration<Data> forLine(Consumer<LineExpressionConfiguration<Data>> consumer) {
        LineExpressionConfiguration<Data> config = new LineExpressionConfiguration<Data>();
        consumer.accept(config);
        lineExpressions.add(config.createLineExpression());
        return this;
    }

    LoggerExpression<Data> createLoggerExpression() {
        return new LoggerExpression<Data>(filePath, entryDateFormat, lineExpressions);
    }
}
