package loggerManager;

import java.util.ArrayList;
import java.util.function.Function;

import loggerManager.interaces.LogFunctionContext;

public class LineExpressionConfiguration<Data> {
    
    LogFunctionContext<Data,String> valueExpression;
    ArrayList<LogFunctionContext<Data,String>> tabValueExpressions;

    LineExpressionConfiguration() {
        tabValueExpressions = new ArrayList<LogFunctionContext<Data,String>>();
    }

    /**
     * Define the line value expression
     * @param valueExpression value expression for the line
     * @return LineExpressionConfiguration to continue configuration
    */
    public LineExpressionConfiguration<Data> forValue(LogFunctionContext<Data,String> valueExpression) {
        this.valueExpression = valueExpression;
        return this;
    }

    /**
     * Define the tab value expression
     * @param tabValueExpression value expression for the tab
     * @return LineExpressionConfiguration to continue configuration
    */
    public LineExpressionConfiguration<Data> forTab(LogFunctionContext<Data,String> tabValueExpression) {
        this.tabValueExpressions.add(tabValueExpression);
        return this;
    }

    LineExpression<Data> createLineExpression() {
        return new LineExpression<Data>(valueExpression,tabValueExpressions);
    }
}
