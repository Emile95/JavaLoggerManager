package loggerManager.exception;

public class NotMappedTypeException extends RuntimeException{
    private Class<?> type;
    public Class<?> getType() { return type; }

    public NotMappedTypeException(Class<?> type) {
        this.type = type;
    }
}
