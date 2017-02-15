package com.backbean;

import com.entidades.TipoContacto;
import com.entidades.Usuarios;
import com.servicios.service.TipoContactoService;
import com.servicios.service.UsuariosService;
import com.utils.SystemSecurityException;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pablo Sevilla on 2/2/2017.
 */
@Named
@Scope("view")
public class TipoContactoBackBean implements Serializable {

    @Autowired
    TipoContactoService tipoContactoService;

    @Autowired
    UsuariosService usuariosService;

    private String tituloModal;
    private String txtTipoContacto;
    private boolean bNuevo;
    private String msgError;
    private String txtBuscar;


    private List<TipoContacto> listTipoContacto;
    private TipoContacto selectRow;

    /*
    Metodo
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        obtenerTipoContacto();
    }

    public void obtenerTipoContacto() {
        if (txtBuscar == null) {
            try {
                listTipoContacto = tipoContactoService.obtenerTipoContacto();
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Regiones", msgError));
            }
        } else {
            try {
                listTipoContacto = tipoContactoService.buscarTipoContacto(txtBuscar);
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Regiones", msgError));
            }
        }
    }

    public void nuevoTipoContacto() {
        txtTipoContacto = null;
        setbNuevo(true);
        setTituloModal("Nuevo Tipo de Contacto");
        RequestContext.getCurrentInstance().update("vTipoContacto");
        RequestContext.getCurrentInstance().execute("PF('vTipoContacto').show()");
    }

    public void editarTipoContacto() {
        if (selectRow != null) {
            if (selectRow.getEstado() == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Modificar Tipo Contacto",  "Tipo Contacto Inactivo, no se permite modificarlo") );
                return;
            }
            txtTipoContacto = selectRow.getTipo();
            setbNuevo(false);
            setTituloModal("Editar Tipo de Contacto");
            RequestContext.getCurrentInstance().update("vTipoContacto");
            RequestContext.getCurrentInstance().execute("PF('vTipoContacto').show()");
        }
    }

    public void guardarTipoContacto() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (bNuevo) {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                TipoContacto tipoContacto = new TipoContacto();
                tipoContacto.setEstado(0);
                tipoContacto.setTipo(txtTipoContacto);
                tipoContacto.setUsuariosCreaId(usuarios);
                tipoContacto.setFechacrea(date);
                tipoContactoService.agregar(tipoContacto);
                listTipoContacto.add(tipoContacto);
            }  catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Tipo Contacto", msgError));
                return;
            }
        } else {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                TipoContacto tipoContacto = tipoContactoService.obtenerUnTipoContacto(selectRow.getId());
                tipoContacto.setTipo(txtTipoContacto);
                tipoContacto.setUsuariosModificaId(usuarios);
                tipoContacto.setFechamod(date);
                tipoContactoService.modificar(tipoContacto);

                for (TipoContacto t: listTipoContacto) {
                    if (t.getId().equals(selectRow.getId())) {
                        listTipoContacto.set(listTipoContacto.indexOf(t), tipoContacto);
                    }
                }
                selectRow = tipoContacto;
            }  catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Tipo Contacto", msgError));
                return;
            }
        }
        RequestContext.getCurrentInstance().update("formTipo");
        RequestContext.getCurrentInstance().execute("PF('vTipoContacto').hide();");

    }

    public void darDeBaja() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (selectRow != null) {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                TipoContacto tipoContacto = tipoContactoService.obtenerUnTipoContacto(selectRow.getId());
                tipoContacto.setEstado(selectRow.getEstado() == 1 ? 0 : 1);
                tipoContacto.setFechamod(date);
                tipoContacto.setUsuariosModificaId(usuarios);
                tipoContactoService.modificar(tipoContacto);

                for (TipoContacto t: listTipoContacto) {
                    if (t.getId().equals(selectRow.getId())) {
                        listTipoContacto.set(listTipoContacto.indexOf(t), tipoContacto);
                    }
                }
                selectRow = tipoContacto;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Baja Tipo Contacto", msgError));
            }
        }
        RequestContext.getCurrentInstance().update("formTipo");
        RequestContext.getCurrentInstance().execute("PF('confirmar').hide();");
    }


    /*
    Propiedades
     */
    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
    }

    public String getTxtTipoContacto() {
        return txtTipoContacto;
    }

    public void setTxtTipoContacto(String txtTipoContacto) {
        this.txtTipoContacto = txtTipoContacto;
    }

    public boolean isbNuevo() {
        return bNuevo;
    }

    public void setbNuevo(boolean bNuevo) {
        this.bNuevo = bNuevo;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public List<TipoContacto> getListTipoContacto() {
        return listTipoContacto;
    }

    public void setListTipoContacto(List<TipoContacto> listTipoContacto) {
        this.listTipoContacto = listTipoContacto;
    }

    public TipoContacto getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(TipoContacto selectRow) {
        this.selectRow = selectRow;
    }
}
