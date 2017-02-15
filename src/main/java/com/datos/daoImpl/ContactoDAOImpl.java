package com.datos.daoImpl;

import com.datos.dao.ContactoDAO;
import com.entidades.Contacto;
import com.utils.BaseGenericDAOImpl;
import com.utils.BusinessException;
import com.utils.DAOException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
@Repository
public class ContactoDAOImpl extends BaseGenericDAOImpl<Contacto, Integer> implements ContactoDAO {

    public List<Contacto> obtenerContactoCumple(Integer mes, Integer dia) throws BusinessException, DAOException {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select {v.*} from contactos.contacto v where EXTRACT(DAY from fechanac) = :dia and EXTRACT(MONTH from fechanac) =:mes").addEntity("v",Contacto.class);
        query.setParameter("dia", dia);
        query.setParameter("mes", mes);
        return  query.list();

    }
}
