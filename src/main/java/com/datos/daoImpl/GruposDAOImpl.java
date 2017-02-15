package com.datos.daoImpl;

import com.datos.dao.GruposDAO;
import com.entidades.Grupos;
import com.utils.BaseGenericDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Pablo Sevilla on 6/2/2017.
 */
@Repository
public class GruposDAOImpl extends BaseGenericDAOImpl<Grupos, Integer> implements GruposDAO {
}
