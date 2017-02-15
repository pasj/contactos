package com.servicios.service;

import com.entidades.Contacto;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
public interface ContactoService {

    void guardar(Contacto contacto) throws BusinessException, DAOException;

    void modificar(Contacto contacto) throws BusinessException, DAOException;

    void eliminar(Contacto contacto) throws BusinessException, DAOException;

    List<Contacto> obtenerContactos() throws BusinessException, DAOException;

    List<Contacto> buscarContacto(String region) throws BusinessException, DAOException;

    Contacto obtenerUnContacto(Integer id) throws BusinessException, DAOException;

    List<Contacto> obtenerContactoCumple(Integer mes, Integer dia) throws BusinessException, DAOException;


}
