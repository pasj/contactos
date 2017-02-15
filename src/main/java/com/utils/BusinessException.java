package com.utils;

/**
 * Created by Pablo Sevilla on 25/12/2016.
 */
public class BusinessException extends Exception {
    private static final long serialVersionUID = 5394136727929338065L;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
