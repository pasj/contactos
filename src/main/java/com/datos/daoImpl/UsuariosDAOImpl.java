package com.datos.daoImpl;

import com.datos.dao.UsuariosDAO;
import com.entidades.Usuarios;
import com.utils.BaseGenericDAOImpl;
import com.utils.BusinessException;
import com.utils.DAOException;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
@Repository
public class UsuariosDAOImpl extends BaseGenericDAOImpl<Usuarios, Integer> implements UsuariosDAO {

    public List<Usuarios> obtenerReunionesUsuario(Integer id) throws BusinessException, DAOException {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("\n" +
        "select usuarios_id AS id, login, nombre, clave, estado,oficina_id as idoficina, idusuariocrea as \"usuariosCreaId\", \n" +
                "fechacrea,idusuariomod as \"usuariosModificaId\",fechamod, esabogado,cambiar,email from catalogos.usuarios\n" +
                "where usuarios_id in (select usuarios_id from agenda.reuniones_usuarios where reunion_id=:ID)");

        query.setParameter("ID", id);
        query.setResultTransformer(new AliasToBeanResultTransformer(Usuarios.class));
        return query.list();
    }

    /*
   private Integer id;
    private String login;
    private String nombre;
    private String clave;
    private Integer estado;
    private Oficinas idoficina;
    private Usuarios usuariosCreaId;
    private Date fechacrea;
    private Usuarios usuariosModificaId;
    private Date fechamod;
    private Integer esabogado;
    private Integer cambiar;     */
}
