package loggerManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.xml.crypto.Data;

class LoggerExpression<Data> {

    private String filePath;
    private ArrayList<LineExpression<Data>> lineExpressions;
    private LogContext context;

    LoggerExpression(String filePath, DateFormat entryDateFormat, ArrayList<LineExpression<Data>> lineExpressions) {
        this.filePath = filePath;
        this.lineExpressions = lineExpressions;
        int lineCount = 0;
        try {
            LineNumberReader reader = new LineNumberReader(new FileReader(filePath));
            while ((reader.readLine()) != null);
            lineCount = reader.getLineNumber();
            reader.close();
        } catch(Exception e) {}
        context = new LogContext(entryDateFormat, lineCount);
    }

    void log(Data data) {
        Time time = new Time(System.currentTimeMillis());
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            for(LineExpression<Data> lineExpression : lineExpressions) {
                myWriter.write(lineExpression.createLine(data,context) + "\n");
                context.incrementCurrentIndex();
            }  
            myWriter.close();
        } catch (IOException e) {}
    }
}
