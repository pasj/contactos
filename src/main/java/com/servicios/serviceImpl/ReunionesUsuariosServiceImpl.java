package com.servicios.serviceImpl;

import com.datos.dao.ReunionesUsuariosDAO;
import com.entidades.ReunionesUsuarios;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.ReunionesUsuariosService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 29/1/2017.
 */
@Service
public class ReunionesUsuariosServiceImpl implements ReunionesUsuariosService {

    @Autowired
    ReunionesUsuariosDAO reunionesUsuariosDAO;

    @Override
    @Transactional
    public void guardar(ReunionesUsuarios reunionesUsuarios) throws BusinessException, DAOException {
        reunionesUsuariosDAO.save(reunionesUsuarios);
    }

    @Override
    @Transactional
    public void modificar(ReunionesUsuarios reunionesUsuarios) throws BusinessException, DAOException {
        reunionesUsuariosDAO.update(reunionesUsuarios);
    }

    @Override
    @Transactional
    public void eliminar(ReunionesUsuarios reunionesUsuarios) throws BusinessException, DAOException {
        reunionesUsuariosDAO.remove(reunionesUsuarios);
    }

    @Override
    @Transactional
    public List<ReunionesUsuarios> obtenerReunionesUsuarios(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addFilterEqual("reunionesPorId.id", id );
        return reunionesUsuariosDAO.search(search);
    }
}
