package loggerManager;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.function.Function;

import javax.xml.crypto.Data;

class LoggerExpression<Data> {

    private String filePath;
    private DateFormat entryDateFormat;
    private ArrayList<LineExpression<Data>> lineExpressions;

    LoggerExpression(String filePath, DateFormat entryDateFormat, ArrayList<LineExpression<Data>> lineExpressions) {
        this.filePath = filePath;
        this.entryDateFormat = entryDateFormat;
        this.lineExpressions = lineExpressions;
    }

    void log(Data data) {
        Time time = new Time(System.currentTimeMillis());
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            for(LineExpression<Data> lineExpression : lineExpressions) 
                myWriter.write(lineExpression.createLine(data) + "\n");
            myWriter.close();
        } catch (IOException e) {}
    }
}
