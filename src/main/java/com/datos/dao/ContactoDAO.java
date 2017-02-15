package com.datos.dao;

import com.entidades.Contacto;
import com.utils.BaseGenericDAO;
import com.utils.BusinessException;
import com.utils.DAOException;

import java.util.List;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
public interface ContactoDAO  extends BaseGenericDAO<Contacto, Integer> {

    List<Contacto> obtenerContactoCumple(Integer mes, Integer dia) throws BusinessException, DAOException;
}
