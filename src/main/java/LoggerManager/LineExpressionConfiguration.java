package loggerManager;

import java.util.ArrayList;
import java.util.function.Function;

public class LineExpressionConfiguration<Data> {
    
    Function<Data,String> valueExpression;
    ArrayList<Function<Data,String>> tabValueExpressions;

    LineExpressionConfiguration() {
        tabValueExpressions = new ArrayList<Function<Data,String>>();
    }

    /**
     * Define the line value expression
     * @param valueExpression value expression for the line
     * @return LineExpressionConfiguration to continue configuration
    */
    public LineExpressionConfiguration<Data> forValue(Function<Data,String> valueExpression) {
        this.valueExpression = valueExpression;
        return this;
    }

    /**
     * Define the tab value expression
     * @param valueExpression value expression for the tab
     * @return LineExpressionConfiguration to continue configuration
    */
    public LineExpressionConfiguration<Data> forTab(Function<Data,String> tabValueExpression) {
        this.tabValueExpressions.add(tabValueExpression);
        return this;
    }

    LineExpression<Data> createLineExpression() {
        return new LineExpression<Data>(valueExpression,tabValueExpressions);
    }
}
