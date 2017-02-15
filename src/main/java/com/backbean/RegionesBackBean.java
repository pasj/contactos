package com.backbean;


import com.entidades.Regiones;
import com.entidades.Usuarios;
import com.servicios.service.RegionesService;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pablo Sevilla on 25/1/2017.
 */
@Named
@Scope("view")
public class RegionesBackBean implements Serializable {

    @Autowired
    RegionesService regionesService;

    @Autowired
    UsuariosService usuariosService;

    private String txtRegion;
    private String tituloModal;
    private boolean bNuevo;
    private  String msgError;
    private String txtBuscar;

    private List<Regiones> listaRegiones;
    private Regiones selectRow;

    /*
    Metodos
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        txtBuscar = null;
        listaRegiones = new ArrayList<>();
        obtenerRegiones();
    }

    public void obtenerRegiones() {
        if (txtBuscar == null) {
            try {
                listaRegiones = regionesService.obtenerRegiones();
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Obtener Regiones", msgError));
            }
        } else {
            try {
                listaRegiones = regionesService.buscarRegion(txtBuscar);
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Obtener Regiones", msgError));
            }
        }
    }


    public void nuevaRegion() {
        txtRegion = null;
        setbNuevo(true);
        setTituloModal("Nueva Región");
        RequestContext.getCurrentInstance().update("vRegion");
        RequestContext.getCurrentInstance().execute("PF('vRegion').show()");

    }

    public void editarRegion() {
        if (selectRow != null) {
            if (selectRow.getEstado() == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Modificar Región",  "Región Inactiva, no se permite modificarlo") );
                return;
            }
            txtRegion = selectRow.getRegion();
            setbNuevo(false);
            setTituloModal("Editar Región");
            RequestContext.getCurrentInstance().update("vRegion");
            RequestContext.getCurrentInstance().execute("PF('vRegion').show()");
        }
    }

    public void darDeBaja() {
        if (selectRow != null) {
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Regiones regiones = regionesService.obtenerUnaRegion(selectRow.getId());
                regiones.setEstado(selectRow.getEstado() == 1 ? 0 : 1);
                regiones.setFechamod(date);
                regiones.setUsuariosCreaId(usuarios);
                regionesService.modificar(regiones);
                for (Regiones r : listaRegiones) {
                    if (r.getId().equals(selectRow.getId())) {
                        listaRegiones.set(listaRegiones.indexOf(r), regiones);
                    }
                }
                selectRow = regiones;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Dar de Baja",  msgError) );
                return;
            }
            RequestContext.getCurrentInstance().update("frmRegiones");
            RequestContext.getCurrentInstance().execute("PF('confirmar').hide();");

        }
    }

    public void guardarRegion() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (bNuevo) {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Regiones regiones = new Regiones();
                regiones.setRegion(txtRegion);
                regiones.setEstado(0);
                regiones.setFechacrea(date);
                regiones.setUsuariosCreaId(usuarios);
                regionesService.guardar(regiones);
                selectRow = regiones;
                listaRegiones.add(regiones);
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Guardar Region",  msgError) );
                return;
            }
        } else {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Regiones regiones = regionesService.obtenerUnaRegion(selectRow.getId());
                regiones.setRegion(txtRegion);
                regiones.setFechamod(date);
                regiones.setUsuariosModificaId(usuarios);
                regionesService.modificar(regiones);
                for (Regiones r : listaRegiones) {
                    if (r.getId().equals(selectRow.getId())) {
                        listaRegiones.set(listaRegiones.indexOf(r), regiones);
                    }
                }
                selectRow = regiones;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Guardar Region",  msgError) );
                return;
            }


        }
        RequestContext.getCurrentInstance().update("frmRegiones");
        RequestContext.getCurrentInstance().execute("PF('vRegion').hide()");
    }

    /*
    Propiedades
     */
    public String getTxtRegion() {
        return txtRegion;
    }

    public void setTxtRegion(String txtRegion) {
        this.txtRegion = txtRegion;
    }

    public List<Regiones> getListaRegiones() {
        return listaRegiones;
    }

    public void setListaRegiones(List<Regiones> listaRegiones) {
        this.listaRegiones = listaRegiones;
    }

    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
    }

    public Regiones getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(Regiones selectRow) {
        this.selectRow = selectRow;
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
}
