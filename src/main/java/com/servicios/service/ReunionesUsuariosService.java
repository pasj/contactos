package com.servicios.service;

import com.entidades.ReunionesUsuarios;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 29/1/2017.
 */
public interface ReunionesUsuariosService {

    void guardar(ReunionesUsuarios reunionesUsuarios) throws BusinessException, DAOException;

    void modificar(ReunionesUsuarios reunionesUsuarios) throws BusinessException, DAOException;

    void eliminar(ReunionesUsuarios reunionesUsuarios) throws BusinessException, DAOException;

    List<ReunionesUsuarios> obtenerReunionesUsuarios(Integer id) throws BusinessException, DAOException;


}
