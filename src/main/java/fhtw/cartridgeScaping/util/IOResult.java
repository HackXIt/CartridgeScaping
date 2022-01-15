package fhtw.cartridgeScaping.util;

/**
 * NOTE TODOs for IOResult
 * TODO Check upstream, if the returned objects are handled appropriately
 * TODO Add some common-usage methods for the Exception
 * TODO Improve log-message-handling to IOResult.success() (Currently using stdout)
 * TODO Improve log-message-handling to IOResult.failure() (Currently using stderr)
 */

/**
 * INFO Header of IOResult.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/util
 * @project CartridgeScaping
 */
public class IOResult<T> {

    private boolean ok;
    private T data;
    private Exception exception;

    public IOResult(boolean ok, T data) {
        this.ok = ok;
        this.data = data;
    }

    public IOResult(boolean ok, T data, Exception exception) {
        this(ok, data);
        this.exception = exception;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean hasData() {
        return data != null;
    }

    public T getData() {
        return data;
    }

    public Exception getException() {
        return exception;
    }

    public static IOResult<?> success(String logMessage, Object data) {
        System.out.println(logMessage);
        return new IOResult<>(true, data);
    }

    public static IOResult<?> failure(String logMessage, Exception e) {
        System.err.println(logMessage);
        return new IOResult<>(false, null, e);
    }
}
