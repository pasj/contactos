package com.servicios.service;

import com.entidades.Usuarios;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
public interface UsuariosService {

    void guardar(Usuarios usuarios) throws BusinessException, DAOException;

    void modificar(Usuarios usuarios) throws BusinessException, DAOException;

    void eliminar(Usuarios usuarios) throws BusinessException, DAOException;

    List<Usuarios> obtenerUsuarios(Integer id) throws BusinessException, DAOException;

    Usuarios obtenerUnUsuario(Integer id) throws BusinessException, DAOException;

    List<Usuarios> obtenerAbogados() throws BusinessException, DAOException;

    List<Usuarios> obtenerReunionesUsuario(Integer id) throws BusinessException, DAOException;
}
