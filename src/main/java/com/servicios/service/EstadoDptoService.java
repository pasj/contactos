package com.servicios.service;

import com.entidades.EstadoDpto;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
public interface EstadoDptoService {

    void guardar(EstadoDpto estadoDpto) throws BusinessException, DAOException;

    void modificar(EstadoDpto estadoDpto) throws BusinessException, DAOException;

    void eliminar(EstadoDpto estadoDpto) throws BusinessException, DAOException;

    List<EstadoDpto> obtenerEstadoDpto(Integer id) throws BusinessException, DAOException;

    List<EstadoDpto> obtenerEstadoDptoActivo(Integer id) throws BusinessException, DAOException;

    List<EstadoDpto> buscarEstadoDpto(String nombre) throws BusinessException, DAOException;

    EstadoDpto obtenerUnEstado(Integer id) throws BusinessException, DAOException;

}
