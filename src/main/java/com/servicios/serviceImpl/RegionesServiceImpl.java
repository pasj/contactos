package com.servicios.serviceImpl;

import com.datos.dao.RegionesDAO;
import com.entidades.Oficinas;
import com.entidades.Regiones;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.RegionesService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 25/1/2017.
 */
@Service
public class RegionesServiceImpl implements RegionesService {

    @Autowired
    RegionesDAO regionesDAO;

    @Override
    @Transactional
    public void guardar(Regiones regiones) throws BusinessException, DAOException {
        regionesDAO.save(regiones);
    }

    @Override
    @Transactional
    public void modificar(Regiones regiones) throws BusinessException, DAOException {
        regionesDAO.update(regiones);
    }

    @Override
    @Transactional
    public void eliminar(Regiones regiones) throws BusinessException, DAOException {
        regionesDAO.remove(regiones);
    }

    @Override
    @Transactional
    public List<Regiones> obtenerRegiones() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("region");
        return regionesDAO.search(search);
    }

    @Override
    @Transactional
    public List<Regiones> buscarRegion(String region) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterILike("region", "%" + region + "%");
        return  regionesDAO.search(search);

    }

    @Override
    @Transactional
    public Regiones obtenerUnaRegion(Integer id) throws BusinessException, DAOException {
        return regionesDAO.find(id);
    }

    @Override
    @Transactional
    public List<Regiones> obtenerRegionesActivas() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("region");
        search.addFilterEqual("estado", 0);
        return regionesDAO.search(search);
    }
}
