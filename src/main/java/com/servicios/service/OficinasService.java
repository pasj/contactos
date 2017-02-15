package com.servicios.service;

import com.entidades.Oficinas;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
public interface OficinasService {


    void guardar(Oficinas oficinas) throws BusinessException, DAOException;

    void modificar(Oficinas oficinas) throws BusinessException, DAOException;

    void eliminar(Oficinas oficinas) throws BusinessException, DAOException;

    List<Oficinas> obtenerOficinas() throws BusinessException, DAOException;

    Oficinas obtenerUnaOficina(Integer id) throws BusinessException, DAOException;

}
