package com.servicios.service;

import com.entidades.Paises;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
public interface PaisesService {

    void guardar(Paises paises) throws BusinessException, DAOException;

    void modificar(Paises paises) throws BusinessException, DAOException;

    void eliminar(Paises paises) throws BusinessException, DAOException;

    List<Paises> obtenerPaises() throws BusinessException, DAOException;

    List<Paises> buscarPais(String pais) throws BusinessException, DAOException;

    Paises obtenerUnPais(Integer id) throws BusinessException, DAOException;

    List<Paises> obtenerPaisesActivos() throws BusinessException, DAOException;
}
