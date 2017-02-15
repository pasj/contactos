package com.backbean;

import com.entidades.GrupoContacto;
import com.entidades.Grupos;
import com.servicios.service.GrupoContactoService;
import com.servicios.service.GruposService;
import com.servicios.service.UsuariosService;
import com.utils.SystemSecurityException;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Pablo Sevilla on 6/2/2017.
 */
@Named
@Scope("view")
public class GrupoContactoBackBean  implements Serializable {

    @Autowired
    GrupoContactoService grupoContactoService;

    @Autowired
    GruposService gruposService;

    @Autowired
    UsuariosService usuariosService;

    private String msgError;
    private String tituloModal;
    private Integer iGrupo;
    private boolean bNuevo;
    private String txtGrupo;
    private boolean principal;
    private boolean edicion;



    private List<Grupos> listaGrupos;
    private Grupos selectRow;
    private List<GrupoContacto> listaGrupoContacto;
    private GrupoContacto selectContacto;

    /*
    Metodos
     */

    @PostConstruct
    public void init() throws SystemSecurityException {
        obtenerGrupos();
        setPrincipal(true);
        setEdicion(false);
    }

    public void obtenerGrupoContacto(Integer id) {
        try {
            listaGrupoContacto = grupoContactoService.obtenerGrupoContacto(id);
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Oficinas", getMsgError()));
        }
    }
    public void obtenerGrupos() {
        try {
            listaGrupos = gruposService.obtenerGrupos();

        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Oficinas", getMsgError()));
        }
    }




    public void nuevoGrupo() {
        txtGrupo = null;
        setbNuevo(true);
        listaGrupoContacto = null;
        setTituloModal("Nuevo Grupo de Contacto");
        setPrincipal(false);
        setEdicion(true);
        RequestContext.getCurrentInstance().update("formGrupos");
    }

    public void cerrarGrupo() {
        setPrincipal(true);
        setEdicion(false);
    }

    public void eliminarContacto() {
        if (selectContacto != null) {
            try {
                GrupoContacto grupoContacto = grupoContactoService.obtenerUnContacto(selectContacto.getId());
                grupoContactoService.eliminar(grupoContacto);
                for (GrupoContacto gc : listaGrupoContacto) {
                    if (gc.getId().equals(selectContacto.getId())) {
                        listaGrupoContacto.remove( listaGrupoContacto.indexOf(gc));
                        break;

                    }
                }
            }  catch (Exception e) {
                setMsgError("Error: " + e.getMessage());
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Oficinas", getMsgError()));
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('confirmar').hide();");
            RequestContext.getCurrentInstance().update("formGrupos");
        }


    }

    public void editarGrupo() {
        txtGrupo = selectRow.getGrupo();
        setbNuevo(true);
        setTituloModal("Editar Grupo de Contacto");
        setPrincipal(false);
        setEdicion(true);
        obtenerGrupoContacto(selectRow.getId());
    }

    public void guardarGrupo() {

    }

    /*
    Propiedades
     */
    public List<Grupos> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupos> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public Grupos getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(Grupos selectRow) {
        this.selectRow = selectRow;
    }

    public List<GrupoContacto> getListaGrupoContacto() {
        return listaGrupoContacto;
    }

    public void setListaGrupoContacto(List<GrupoContacto> listaGrupoContacto) {
        this.listaGrupoContacto = listaGrupoContacto;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
    }

    public Integer getiGrupo() {
        return iGrupo;
    }

    public void setiGrupo(Integer iGrupo) {
        this.iGrupo = iGrupo;
    }

    public boolean isbNuevo() {
        return bNuevo;
    }

    public void setbNuevo(boolean bNuevo) {
        this.bNuevo = bNuevo;
    }

    public String getTxtGrupo() {
        return txtGrupo;
    }

    public void setTxtGrupo(String txtGrupo) {
        this.txtGrupo = txtGrupo;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean isEdicion() {
        return edicion;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    public GrupoContacto getSelectContacto() {
        return selectContacto;
    }

    public void setSelectContacto(GrupoContacto selectContacto) {
        this.selectContacto = selectContacto;
    }
}
