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

import loggerManager.interaces.LogFunctionContext;

class LoggerExpression<Data> {

    private String filePath;
    private ArrayList<LineExpression<Data>> lineExpressions;
    private LogFunctionContext<Data, String> beginLineExpression;
    private LogFunctionContext<Data, String> endLineExpression;
    private LogContext context;

    LoggerExpression(String filePath, DateFormat entryDateFormat, ArrayList<LineExpression<Data>> lineExpressions, LogFunctionContext<Data, String> beginLineExpression, LogFunctionContext<Data, String> endLineExpression) {
        this.filePath = filePath;
        this.lineExpressions = lineExpressions;
        this.beginLineExpression = beginLineExpression;
        this.endLineExpression = endLineExpression;
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
        String begin; String end;
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            for(LineExpression<Data> lineExpression : lineExpressions) {
                begin = beginLineExpression == null ? null : beginLineExpression.apply(data, context);
                end = endLineExpression == null ? "" : endLineExpression.apply(data, context);
                myWriter.write(lineExpression.createLine(data,context,begin,end)+"\n");
                context.incrementCurrentIndex();
            }  
            myWriter.close();
        } catch (IOException e) {}
    }
}
