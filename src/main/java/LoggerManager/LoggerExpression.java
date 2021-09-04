package loggerManager;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.function.Function;

public class LoggerExpression<T> {

    private String filePath;
    private DateFormat entryDateFormat;
    private Function<T,String> entryExpression;

    LoggerExpression(String filePath, DateFormat entryDateFormat, Function<T,String> entryExpression) {
        this.filePath = filePath;
        this.entryDateFormat = entryDateFormat;
        this.entryExpression = entryExpression;
    }

    public void log(T data) {
        Time time = new Time(System.currentTimeMillis());
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(entryDateFormat.format(time) + " - " + entryExpression.apply(data) + "\n");
            myWriter.close();
        } catch (IOException e) {}
    }
}
