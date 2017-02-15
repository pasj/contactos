package com.datos.daoImpl;

import com.datos.dao.TipoContactoDAO;
import com.entidades.TipoContacto;
import com.utils.BaseGenericDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Pablo Sevilla on 24/1/2017.
 */
@Repository
public class TipoContactoDAOImpl extends BaseGenericDAOImpl<TipoContacto, Integer> implements TipoContactoDAO {
}
