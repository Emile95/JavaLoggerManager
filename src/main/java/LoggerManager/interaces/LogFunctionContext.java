package loggerManager.interaces;

import loggerManager.LogContext;

public interface LogFunctionContext<Parameter,Return> {
    public Return apply(Parameter param, LogContext context);
}
