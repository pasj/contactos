package com.servicios.service;

import com.entidades.Salas;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 3/2/2017.
 */
public interface SalasService {

    void guardar(Salas salas) throws BusinessException, DAOException;

    void modificar(Salas salas) throws BusinessException, DAOException;

    void eliminar(Salas salas) throws BusinessException, DAOException;

    List<Salas> obtenerAgendaSalas(Integer id) throws BusinessException, DAOException;


}
