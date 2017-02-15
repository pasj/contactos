package com.servicios.service;

import com.entidades.Reuniones;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
public interface ReunionesService {

    void guardar(Reuniones reuniones) throws BusinessException, DAOException;

    void modificar(Reuniones reuniones) throws BusinessException, DAOException;

    void eliminar(Reuniones reuniones) throws BusinessException, DAOException;

    List<Reuniones> obtenerReuniones(Integer id) throws BusinessException, DAOException;


}
