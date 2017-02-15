package com.datos.daoImpl;

import com.datos.dao.PaisesDAO;
import com.entidades.Paises;
import com.utils.BaseGenericDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
@Repository
public class PaisesDAOImpl extends BaseGenericDAOImpl<Paises, Integer> implements PaisesDAO {
}
