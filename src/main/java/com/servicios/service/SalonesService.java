package com.servicios.service;

import com.entidades.Salones;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 4/2/2017.
 */
public interface SalonesService {

    void guardar(Salones salones) throws BusinessException, DAOException;

    void modificar(Salones salones) throws BusinessException, DAOException;

    void eliminar(Salones salones) throws BusinessException, DAOException;

    List<Salones> obtenerSalonesOficina(Integer id) throws BusinessException, DAOException;

    List<Salones> obtenerSalones() throws BusinessException, DAOException;

    Salones obtenerUnSalor(Integer id) throws BusinessException, DAOException;
}
