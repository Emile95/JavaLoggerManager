package loggerManager.exception;

import java.lang.RuntimeException;

public class DuplicateTypeException extends RuntimeException {
    private Class<?> c;
    public Class<?> getCLass() {
        return c;
    }

    public DuplicateTypeException(Class<?> c) {
        super("Duplicate Class : " + c.getName());
        this.c = c;
    }
}
