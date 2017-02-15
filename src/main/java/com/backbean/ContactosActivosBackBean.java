package com.backbean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.entidades.Contacto;
import com.entidades.Grupos;
import com.entidades.TipoContacto;
import com.servicios.service.ContactoService;
import com.servicios.service.GruposService;
import com.servicios.service.TipoContactoService;
import com.utils.SystemSecurityException;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pablo Sevilla on 20/1/2017.
 */
@Named
@Scope("view")
public class ContactosActivosBackBean implements Serializable{

    @Autowired
    ContactoService contactoService;

    @Autowired
    GruposService gruposService;

    @Autowired
    TipoContactoService tipoContactoService;


    //Definicion de Variables
    private String cadenaBusqueda;
    private Integer iTipo;
    private String txtNombre;
    private Date fecha;
    private String txtDireccion;
    private Integer iReggion;
    private Integer iPais;
    private Integer iEstado;

    private String txtEmpresa;
    private String txtCargo;
    private String txtWebPage;
    private String txtCodigoArea;
    private String txtTelefono;
    private String txtExtension;
    private String txtFax;
    private String txtApartado;

    private String txtCorreo;
    private String txtTelefonoPersonal;
    private String txtMovil;



    private String tituloModal;
    private boolean principal;
    private boolean edicion;
    private String msgError;
    private String[] grupoSeleccionados;

    private List<Contacto> listaContacto;
    private List<Grupos> listaGrupos;
    private List<TipoContacto> listaTipoContacto;
    /****************************************************************
     METODOS
     ****************************************************************/
    @PostConstruct
    public void init() throws SystemSecurityException {
        setPrincipal(true);
        setEdicion(false);
        obtenerGrupos();
        obtenerTipoContacto();
    }

    public void obtenerTipoContacto() {
        try {
            listaTipoContacto = tipoContactoService.obtenerTipoContacto();
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "obtenerTipoContacto", getMsgError()));
            return;
        }

    }

    public void obtenerGrupos() {
        try {
            listaGrupos = gruposService.obtenerGrupos();
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "obtenerGrupos", getMsgError()));
            return;
        }
    }

    public void nuevoContacto() {
        iTipo = 1;
        txtNombre = null;
        fecha = null;
        txtDireccion = null;
        iReggion = null;
        iPais = null;
        iEstado = null;
        txtEmpresa = null;
        txtCargo = null;
        txtWebPage = null;
        txtCodigoArea = null;
        txtTelefono = null;
        txtExtension = null;
        txtFax = null;
        txtApartado = null;
        txtCorreo = null;
        txtTelefonoPersonal = null;
        txtMovil = null;
        grupoSeleccionados =  new String[listaGrupos.size()];;
        setPrincipal(false);
        setEdicion(true);

        setTituloModal("Nuevo Contacto");
        RequestContext.getCurrentInstance().update("formContacto");

    }

    public void cancelarEdicion() {
        setPrincipal(true);
        setEdicion(false);
    }
    //Propiedades


    public String getCadenaBusqueda() {
        return cadenaBusqueda;
    }

    public void setCadenaBusqueda(String cadenaBusqueda) {
        this.cadenaBusqueda = cadenaBusqueda;
    }

    public Integer getiTipo() {
        return iTipo;
    }

    public void setiTipo(Integer iTipo) {
        this.iTipo = iTipo;
    }

    public String getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(String txtNombre) {
        this.txtNombre = txtNombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(String txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public Integer getiReggion() {
        return iReggion;
    }

    public void setiReggion(Integer iReggion) {
        this.iReggion = iReggion;
    }

    public Integer getiPais() {
        return iPais;
    }

    public void setiPais(Integer iPais) {
        this.iPais = iPais;
    }

    public Integer getiEstado() {
        return iEstado;
    }

    public void setiEstado(Integer iEstado) {
        this.iEstado = iEstado;
    }

    public String getTxtEmpresa() {
        return txtEmpresa;
    }

    public void setTxtEmpresa(String txtEmpresa) {
        this.txtEmpresa = txtEmpresa;
    }

    public String getTxtCargo() {
        return txtCargo;
    }

    public void setTxtCargo(String txtCargo) {
        this.txtCargo = txtCargo;
    }

    public String getTxtWebPage() {
        return txtWebPage;
    }

    public void setTxtWebPage(String txtWebPage) {
        this.txtWebPage = txtWebPage;
    }

    public String getTxtCodigoArea() {
        return txtCodigoArea;
    }

    public void setTxtCodigoArea(String txtCodigoArea) {
        this.txtCodigoArea = txtCodigoArea;
    }

    public String getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(String txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public String getTxtExtension() {
        return txtExtension;
    }

    public void setTxtExtension(String txtExtension) {
        this.txtExtension = txtExtension;
    }

    public String getTxtFax() {
        return txtFax;
    }

    public void setTxtFax(String txtFax) {
        this.txtFax = txtFax;
    }

    public String getTxtApartado() {
        return txtApartado;
    }

    public void setTxtApartado(String txtApartado) {
        this.txtApartado = txtApartado;
    }

    public String getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(String txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public String getTxtTelefonoPersonal() {
        return txtTelefonoPersonal;
    }

    public void setTxtTelefonoPersonal(String txtTelefonoPersonal) {
        this.txtTelefonoPersonal = txtTelefonoPersonal;
    }

    public String getTxtMovil() {
        return txtMovil;
    }

    public void setTxtMovil(String txtMovil) {
        this.txtMovil = txtMovil;
    }

    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
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

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String[] getGrupoSeleccionados() {
        return grupoSeleccionados;
    }

    public void setGrupoSeleccionados(String[] grupoSeleccionados) {
        this.grupoSeleccionados = grupoSeleccionados;
    }

    public List<Contacto> getListaContacto() {
        return listaContacto;
    }

    public void setListaContacto(List<Contacto> listaContacto) {
        this.listaContacto = listaContacto;
    }

    public List<Grupos> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupos> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<TipoContacto> getListaTipoContacto() {
        return listaTipoContacto;
    }

    public void setListaTipoContacto(List<TipoContacto> listaTipoContacto) {
        this.listaTipoContacto = listaTipoContacto;
    }
}
