package com.datos.daoImpl;

import com.datos.dao.SalasDAO;
import com.entidades.Salas;
import com.utils.BaseGenericDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Pablo Sevilla on 3/2/2017.
 */
@Repository
public class SalasDAOImpl extends BaseGenericDAOImpl<Salas, Integer> implements SalasDAO {
}
