package com.servicios.serviceImpl;

import com.datos.dao.UsuariosDAO;
import com.entidades.Usuarios;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.UsuariosService;
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
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    UsuariosDAO usuariosDAO;

    @Override
    @Transactional
    public void guardar(Usuarios usuarios) throws BusinessException, DAOException {
        usuariosDAO.save(usuarios);
    }

    @Override
    @Transactional
    public void modificar(Usuarios usuarios) throws BusinessException, DAOException {
        usuariosDAO.update(usuarios);
    }

    @Override
    @Transactional
    public void eliminar(Usuarios usuarios) throws BusinessException, DAOException {
        usuariosDAO.remove(usuarios);
    }

    @Override
    @Transactional
    public List<Usuarios> obtenerUsuarios(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterEqual("idoficina.id", id);
        return usuariosDAO.search(search);
    }

    @Override
    @Transactional
    public Usuarios obtenerUnUsuario(Integer id) throws BusinessException, DAOException {
        return usuariosDAO.find(id);
    }

    @Override
    @Transactional
    public List<Usuarios> obtenerAbogados() throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterEqual("esabogado", 1);
        return usuariosDAO.search(search);
    }

    @Override
    @Transactional
    public List<Usuarios> obtenerReunionesUsuario(Integer id) throws BusinessException, DAOException {
        return usuariosDAO.obtenerReunionesUsuario(id);
    }
}
