package com.datos.daoImpl;

import com.datos.dao.ReunionesDAO;
import com.entidades.Reuniones;
import com.utils.BaseGenericDAOImpl;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
@Repository
public class ReunionesDAOImpl extends BaseGenericDAOImpl<Reuniones, Integer> implements ReunionesDAO {

}
