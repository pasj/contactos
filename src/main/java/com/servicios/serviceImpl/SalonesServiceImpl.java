package com.servicios.serviceImpl;

import com.datos.dao.SalonesDAO;
import com.entidades.Salones;
import com.googlecode.genericdao.search.Search;
import com.servicios.service.SalonesService;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pablo Sevilla on 4/2/2017.
 */
@Service
public class SalonesServiceImpl implements SalonesService {

    @Autowired
    SalonesDAO salonesDAO;

    @Override
    @Transactional
    public void guardar(Salones salones) throws BusinessException, DAOException {
        salonesDAO.save(salones);
    }

    @Override
    @Transactional
    public void modificar(Salones salones) throws BusinessException, DAOException {
        salonesDAO.update(salones);
    }

    @Override
    @Transactional
    public void eliminar(Salones salones) throws BusinessException, DAOException {
        salonesDAO.remove(salones);
    }

    @Override
    @Transactional
    public List<Salones> obtenerSalonesOficina(Integer id) throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("nombre");
        search.addFilterEqual("oficinaIdPorId.id", id);
        return salonesDAO.search(search);

    }

    @Override
    @Transactional
    public List<Salones> obtenerSalones() throws BusinessException, DAOException {
        Search search = new Search();
        search.addSortAsc("oficinaIdPorId.oficina");
        return salonesDAO.search(search);
    }

    @Override
    @Transactional
    public Salones obtenerUnSalor(Integer id) throws BusinessException, DAOException {
        return salonesDAO.find(id);
    }

}
