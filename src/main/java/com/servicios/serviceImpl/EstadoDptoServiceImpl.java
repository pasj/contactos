package com.servicios.serviceImpl;

import com.datos.dao.EstadoDptoDAO;
import com.entidades.EstadoDpto;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.EstadoDptoService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
@Service
public class EstadoDptoServiceImpl implements EstadoDptoService {

    @Autowired
    EstadoDptoDAO estadoDptoDAO;

    @Override
    @Transactional
    public void guardar(EstadoDpto estadoDpto) throws BusinessException, DAOException {
        estadoDptoDAO.save(estadoDpto);
    }

    @Override
    @Transactional
    public void modificar(EstadoDpto estadoDpto) throws BusinessException, DAOException {
        estadoDptoDAO.update(estadoDpto);
    }

    @Override
    @Transactional
    public void eliminar(EstadoDpto estadoDpto) throws BusinessException, DAOException {
        estadoDptoDAO.remove(estadoDpto);
    }

    @Override
    @Transactional
    public List<EstadoDpto> obtenerEstadoDpto(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("nombre");
        search.addFilterEqual("paisesPorId.id", id);
        return estadoDptoDAO.search(search);
    }

    @Override
    @Transactional
    public List<EstadoDpto> obtenerEstadoDptoActivo(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("nombre");
        search.addFilterEqual("activo", 0);
        search.addFilterEqual("paisesPorId.id", id);
        return estadoDptoDAO.search(search);
    }


    @Override
    @Transactional
    public List<EstadoDpto> buscarEstadoDpto(String nombre) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterLike("nombre", "%" + nombre + "%");
        return estadoDptoDAO.search(search);
    }

    @Override
    @Transactional
    public EstadoDpto obtenerUnEstado(Integer id) throws BusinessException, DAOException {
        return estadoDptoDAO.find(id);
    }
}
