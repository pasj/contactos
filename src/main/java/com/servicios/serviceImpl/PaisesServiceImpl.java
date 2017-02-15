package com.servicios.serviceImpl;

import com.datos.dao.PaisesDAO;
import com.entidades.Paises;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.PaisesService;
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
public class PaisesServiceImpl implements PaisesService {

    @Autowired
    PaisesDAO paisesDAO;

    @Override
    @Transactional
    public void guardar(Paises paises) throws BusinessException, DAOException {
        paisesDAO.save(paises);
    }

    @Override
    @Transactional
    public void modificar(Paises paises) throws BusinessException, DAOException {
        paisesDAO.update(paises);
    }

    @Override
    @Transactional
    public void eliminar(Paises paises) throws BusinessException, DAOException {
        paisesDAO.remove(paises);
    }

    @Override
    @Transactional
    public List<Paises> obtenerPaises() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("pais");
        return paisesDAO.search(search);
    }

    @Override
    @Transactional
    public List<Paises> buscarPais(String pais) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterLike("pais", "%" + pais + "%");
        return paisesDAO.search(search);
    }

    @Override
    @Transactional
    public Paises obtenerUnPais(Integer id) throws BusinessException, DAOException {
        return paisesDAO.find(id);
    }

    @Override
    @Transactional
    public List<Paises> obtenerPaisesActivos() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("pais");
        search.addFilterEqual("estado", 0);
        return paisesDAO.search(search);
    }
}
