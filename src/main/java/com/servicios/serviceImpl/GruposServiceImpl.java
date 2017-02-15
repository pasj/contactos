package com.servicios.serviceImpl;

import com.datos.dao.GruposDAO;
import com.entidades.Grupos;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.GruposService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 6/2/2017.
 */
@Service
public class GruposServiceImpl implements GruposService {

    @Autowired
    GruposDAO gruposDAO;

    @Override
    @Transactional
    public void guardar(Grupos grupos) throws BusinessException,DAOException {
        gruposDAO.save(grupos);
    }

    @Override
    @Transactional
    public void modificar(Grupos grupos) throws BusinessException,DAOException {
        gruposDAO.update(grupos);
    }

    @Override
    @Transactional
    public void eliminar(Grupos grupos) throws BusinessException, DAOException {
        gruposDAO.remove(grupos);
    }

    @Override
    @Transactional
    public List<Grupos> obtenerGrupos() throws BusinessException,DAOException {
        Search search = new Search();
        search.addSortAsc("grupo");
        return gruposDAO.search(search);
    }

    @Override
    @Transactional
    public Grupos obtenerUnGrupo(Integer id) throws BusinessException, DAOException {
        return gruposDAO.find(id);
    }
}
