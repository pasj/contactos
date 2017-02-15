package com.utils;

/**
 * Created by Pablo Sevilla on 25/12/2016.
 */
public class DAOException extends Exception  {
    private static final long serialVersionUID = 5394136727929338065L;

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
}