package com.servicios.serviceImpl;

import com.datos.dao.OficinasDAO;
import com.entidades.Oficinas;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.OficinasService;
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
public class OficinasServiceImpl implements OficinasService {

    @Autowired
    OficinasDAO oficinasDAO;

    @Override
    @Transactional
    public void guardar(Oficinas oficinas) throws BusinessException, DAOException {
        oficinasDAO.save(oficinas);
    }

    @Override
    @Transactional
    public void modificar(Oficinas oficinas) throws BusinessException, DAOException {
        oficinasDAO.update(oficinas);
    }

    @Override
    @Transactional
    public void eliminar(Oficinas oficinas) throws BusinessException, DAOException {
        oficinasDAO.remove(oficinas);
    }

    @Override
    @Transactional
    public List<Oficinas> obtenerOficinas() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("oficina");
        return oficinasDAO.search(search);
    }

    @Override
    @Transactional
    public Oficinas obtenerUnaOficina(Integer id) throws BusinessException, DAOException {
        return oficinasDAO.find(id);
    }
}
