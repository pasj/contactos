package com.servicios.service;

import com.entidades.Grupos;
import com.utils.BusinessException;
import com.utils.DAOException;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by Pablo Sevilla on 6/2/2017.
 */
public interface GruposService {

    void guardar(Grupos grupos) throws BusinessException,DAOException;

    void modificar(Grupos grupos) throws BusinessException,DAOException;

    void eliminar(Grupos grupos) throws BusinessException, DAOException;

    List<Grupos> obtenerGrupos() throws BusinessException,DAOException;

    Grupos obtenerUnGrupo(Integer id) throws BusinessException, DAOException;

}
