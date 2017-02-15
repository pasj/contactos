package com.servicios.service;

import com.entidades.GrupoContacto;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 6/2/2017.
 */
public interface GrupoContactoService {

    void guardar(GrupoContacto grupoContacto) throws BusinessException, DAOException;

    void modificar(GrupoContacto grupoContacto) throws BusinessException, DAOException;

    void eliminar(GrupoContacto grupoContacto) throws BusinessException, DAOException;

    List<GrupoContacto> obtenerGrupoContacto(Integer id) throws BusinessException, DAOException;

    GrupoContacto obtenerUnContacto(Integer id) throws BusinessException, DAOException;


}
