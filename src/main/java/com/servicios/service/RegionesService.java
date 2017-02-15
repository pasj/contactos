package com.servicios.service;


import com.entidades.Regiones;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 25/1/2017.
 */
public interface RegionesService {

    void guardar(Regiones regiones) throws BusinessException, DAOException;

    void modificar(Regiones regiones) throws BusinessException, DAOException;

    void eliminar(Regiones regiones) throws BusinessException, DAOException;

    List<Regiones> obtenerRegiones() throws BusinessException, DAOException;

    List<Regiones> buscarRegion(String region) throws BusinessException, DAOException;

    Regiones obtenerUnaRegion(Integer id) throws BusinessException, DAOException;

    List<Regiones> obtenerRegionesActivas() throws BusinessException, DAOException;

}
