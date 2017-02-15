package com.datos.daoImpl;

import com.datos.dao.SalonesDAO;
import com.entidades.Salones;
import com.utils.BaseGenericDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Pablo Sevilla on 4/2/2017.
 */
@Repository
public class SalonesDAOImpl extends BaseGenericDAOImpl<Salones, Integer> implements SalonesDAO {
}
