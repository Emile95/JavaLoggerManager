package loggerManager;

import java.util.ArrayList;
import java.util.function.Function;

import loggerManager.interaces.LogFunctionContext;

class LineExpression<Data> {
    LogFunctionContext<Data,String> valueExpression;
    ArrayList<LogFunctionContext<Data,String>> tabValueExpressions;

    LineExpression(LogFunctionContext<Data,String> valueExpression, ArrayList<LogFunctionContext<Data,String>> tabValueExpressions) {
        this.valueExpression = valueExpression;
        this.tabValueExpressions = tabValueExpressions;
    }

    String createLine(Data data, LogContext context) {
        if(valueExpression != null) return valueExpression.apply(data,context);
        String line = "";
        for(LogFunctionContext<Data,String> tabValueExpression : tabValueExpressions)
            line += tabValueExpression.apply(data,context) + "\t";
        return line;
    }
}
