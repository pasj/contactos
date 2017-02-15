package com.servicios.serviceImpl;

import com.datos.dao.GrupoContactoDAO;
import com.entidades.GrupoContacto;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.GrupoContactoService;
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
public class GrupoContactoServiceImpl implements GrupoContactoService {

    @Autowired
    GrupoContactoDAO grupoContactoDAO;

    @Override
    @Transactional
    public void guardar(GrupoContacto grupoContacto) throws BusinessException, DAOException {
        grupoContactoDAO.save(grupoContacto);
    }

    @Override
    @Transactional
    public void modificar(GrupoContacto grupoContacto) throws BusinessException, DAOException {
        grupoContactoDAO.update(grupoContacto);
    }

    @Override
    @Transactional
    public void eliminar(GrupoContacto grupoContacto) throws BusinessException, DAOException {
        grupoContactoDAO.remove(grupoContacto);
    }

    @Override
    @Transactional
    public List<GrupoContacto> obtenerGrupoContacto(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterEqual("grupoId.id", id);
        return grupoContactoDAO.search(search);
    }

    @Override
    @Transactional
    public GrupoContacto obtenerUnContacto(Integer id) throws BusinessException, DAOException {
        return grupoContactoDAO.find(id);
    }
}
