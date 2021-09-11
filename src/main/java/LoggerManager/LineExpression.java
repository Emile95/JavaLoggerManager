package loggerManager;

import java.util.ArrayList;
import java.util.function.Function;

class LineExpression<Data> {
    Function<Data,String> valueExpression;
    ArrayList<Function<Data,String>> tabValueExpressions;

    LineExpression(Function<Data,String> valueExpression, ArrayList<Function<Data,String>> tabValueExpressions) {
        this.valueExpression = valueExpression;
        this.tabValueExpressions = tabValueExpressions;
    }

    String createLine(Data data) {
        if(valueExpression != null) return valueExpression.apply(data);
        String line = "";
        for(Function<Data,String> tabValueExpression : tabValueExpressions)
            line += tabValueExpression.apply(data) + "\t";
        return line;
    }
}
