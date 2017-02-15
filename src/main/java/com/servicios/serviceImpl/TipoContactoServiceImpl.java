package com.servicios.serviceImpl;

import com.datos.dao.TipoContactoDAO;
import com.entidades.TipoContacto;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.TipoContactoService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 24/1/2017.
 */
@Service
public class TipoContactoServiceImpl implements TipoContactoService {

    @Autowired
    TipoContactoDAO tipoContactoDAO;

    @Override
    @Transactional
    public void agregar(TipoContacto tipoContacto) throws BusinessException, DAOException {
        tipoContactoDAO.save(tipoContacto);
    }

    @Override
    @Transactional
    public void modificar(TipoContacto tipoContacto) throws  BusinessException, DAOException {
        tipoContactoDAO.update(tipoContacto);
    }

    @Override
    @Transactional
    public void eliminar(TipoContacto tipoContacto) throws BusinessException, DAOException {
        tipoContactoDAO.remove(tipoContacto);
    }

    @Override
    @Transactional
    public List<TipoContacto> obtenerTipoContacto() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("tipo");
        return tipoContactoDAO.search(search);
    }

    @Override
    @Transactional
    public List<TipoContacto> buscarTipoContacto(String tipo) throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("tipo");
        search.addFilterLike("tipo", "%" + tipo + "%");
        return tipoContactoDAO.search(search);
    }

    @Override
    @Transactional
    public List<TipoContacto> obtenerTipoContactoActivo() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("tipo");
        search.addFilterEqual("estado", 0);
        return tipoContactoDAO.search(search);
    }

    @Override
    @Transactional
    public TipoContacto obtenerUnTipoContacto(Integer id) throws BusinessException, DAOException {
        return tipoContactoDAO.find(id);
    }
}
