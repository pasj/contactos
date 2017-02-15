package com.datos.dao;

import com.entidades.Usuarios;
import com.utils.BaseGenericDAO;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
public interface UsuariosDAO extends BaseGenericDAO<Usuarios, Integer> {

    List<Usuarios> obtenerReunionesUsuario(Integer id) throws BusinessException, DAOException;
}
