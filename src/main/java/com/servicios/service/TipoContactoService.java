package com.servicios.service;

import com.entidades.TipoContacto;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 24/1/2017.
 */
public interface TipoContactoService {

    void agregar(TipoContacto tipoContacto) throws BusinessException, DAOException;

    void modificar(TipoContacto tipoContacto) throws  BusinessException, DAOException;

    void eliminar(TipoContacto tipoContacto) throws BusinessException, DAOException;

    List<TipoContacto> obtenerTipoContacto() throws BusinessException, DAOException;

    List<TipoContacto> buscarTipoContacto(String tipo) throws BusinessException, DAOException;

    List<TipoContacto> obtenerTipoContactoActivo() throws BusinessException, DAOException;

    TipoContacto obtenerUnTipoContacto(Integer id) throws BusinessException, DAOException;
}
