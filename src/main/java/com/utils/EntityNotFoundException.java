package com.utils;

/**
 * Created by Pablo Sevilla on 25/12/2016.
 */
public class EntityNotFoundException extends DAOException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(int id) {
        super("Registro con identificador: " + id + " no encontrado.");
    }

    public EntityNotFoundException(String id) {
        super("Registro con identificador: " + id + " no encontrado.");
    }
}
