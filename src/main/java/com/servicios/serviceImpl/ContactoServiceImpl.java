package com.servicios.serviceImpl;

import com.datos.dao.ContactoDAO;
import com.entidades.Contacto;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.ContactoService;
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
public class ContactoServiceImpl implements ContactoService {

    @Autowired
    ContactoDAO contactoDAO;


    @Override
    @Transactional
    public void guardar(Contacto contacto) throws BusinessException, DAOException {
        contactoDAO.save(contacto);
    }

    @Override
    @Transactional
    public void modificar(Contacto contacto) throws BusinessException, DAOException {
        contactoDAO.update(contacto);
    }

    @Override
    @Transactional
    public void eliminar(Contacto contacto) throws BusinessException, DAOException {
        contactoDAO.remove(contacto);
    }

    @Override
    @Transactional
    public List<Contacto> obtenerContactos() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("nombre");
        return contactoDAO.search(search);
    }

    @Override
    @Transactional
    public List<Contacto> buscarContacto(String nombre) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterLike("nombre", "%" + nombre + "%");
        return contactoDAO.search(search);
    }

    @Override
    @Transactional
    public Contacto obtenerUnContacto(Integer id) throws BusinessException, DAOException {
        return contactoDAO.find(id);
    }

    @Override
    @Transactional
    public List<Contacto> obtenerContactoCumple(Integer mes, Integer dia) throws BusinessException, DAOException {
        return contactoDAO.obtenerContactoCumple(mes,dia);
    }

}
