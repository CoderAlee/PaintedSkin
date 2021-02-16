package org.alee.component.skin.exception;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/11
 * @description: xxxx
 *
 *********************************************************/
public final class StrictModeException extends RuntimeException {

    public StrictModeException(String message) {
        super(message);
    }

    public StrictModeException(Throwable throwable) {
        super(throwable);
    }
}
