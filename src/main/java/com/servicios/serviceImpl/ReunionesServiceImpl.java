package com.servicios.serviceImpl;

import com.datos.dao.ReunionesDAO;
import com.entidades.Reuniones;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.ReunionesService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
@Service
public class ReunionesServiceImpl implements ReunionesService {

    @Autowired
    ReunionesDAO reunionesDAO;

    @Override
    @Transactional
    public void guardar(Reuniones reuniones) throws BusinessException, DAOException {
        reunionesDAO.save(reuniones);
    }

    @Override
    @Transactional
    public void modificar(Reuniones reuniones) throws BusinessException, DAOException {
        reunionesDAO.update(reuniones);
    }

    @Override
    @Transactional
    public void eliminar(Reuniones reuniones) throws BusinessException, DAOException {
        reunionesDAO.remove(reuniones);
    }

    @Override
    @Transactional
    public List<Reuniones> obtenerReuniones(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("inicio");
        search.addFilterEqual("oficinaIdPorId.id",id);
        return  reunionesDAO.search(search);
    }


}
