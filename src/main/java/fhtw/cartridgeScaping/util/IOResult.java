package fhtw.cartridgeScaping.util;

/**
 * NOTE TODOs for IOResult
 * TODO Check upstream, if the returned objects are handled appropriately
 * TODO Add some common-usage methods for the Exception
 * TODO Improve log-message-handling to IOResult.success() (Currently using stdout)
 * TODO Improve log-message-handling to IOResult.failure() (Currently using stderr)
 */

import fhtw.cartridgeScaping.cartridge.Cartridge;
import fhtw.cartridgeScaping.controller.ViewManager;

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

    public IOResult() {
        this.ok = false;
    }

    public IOResult(boolean ok, T data, Exception exception) {
        this.ok = ok;
        this.data = data;
        this.exception = exception;
    }

    public IOResult(IOResult<T> ioResult) {
        // TODO check if making a shallow copy is a good idea for IOResult
        this.ok = ioResult.isOk();
        this.data = ioResult.getData();
        this.exception = ioResult.getException();
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean hasData() {
        return data != null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public boolean handleIoResult(String messageOnError,
                                String messageOnSuccess) {
        if(this.isOk()) {
            System.out.println(messageOnSuccess);
        } else {
            System.err.printf("%s\n", messageOnError);
            if(ViewManager.isDeveloperMode()) {
                if(this.getException() != null) {
                    this.getException().printStackTrace();
                }
            }
        }
        return this.isOk();
    }

    public IOResult<T> success(String fileName, T data) {
        this.data = data;
        this.ok = true;
        this.exception = null;
        System.out.println(String.format("%s:", fileName));
        return this;
    }

    public IOResult<T> failure(String fileName, Exception e) {
        this.data = null;
        this.ok = false;
        this.exception = e;
        System.err.println(String.format("%s:", fileName));
        return this;
    }
}
