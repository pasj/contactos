package com.servicios.serviceImpl;

import com.datos.dao.SalasDAO;
import com.entidades.Salas;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.SalasService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 3/2/2017.
 */
@Service
public class SalasServiceImpl implements SalasService {

    @Autowired
    SalasDAO salasDAO;

    @Override
    @Transactional
    public void guardar(Salas salas) throws BusinessException, DAOException {
        salasDAO.save(salas);
    }

    @Override
    @Transactional
    public void modificar(Salas salas) throws BusinessException, DAOException {
        salasDAO.update(salas);
    }

    @Override
    @Transactional
    public void eliminar(Salas salas) throws BusinessException, DAOException {
        salasDAO.remove(salas);
    }

    @Override
    @Transactional
    public List<Salas> obtenerAgendaSalas(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterEqual("oficinaIdPorId.id", id);
        return salasDAO.search(search);
    }

}
