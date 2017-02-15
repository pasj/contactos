package com.backbean;

import com.entidades.Paises;
import com.entidades.Usuarios;
import com.servicios.service.PaisesService;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pablo Sevilla on 30/1/2017.
 */
@Named
@Scope("view")
public class PaisesBackBean implements Serializable {

    @Autowired
    PaisesService paisesService;

    @Autowired
    UsuariosService usuariosService;

    private String txtPais;
    private String tituloModal;
    private boolean bNuevo;
    private String msgError;
    private String txtBuscar;

    private List<Paises> listaPaises;
    private Paises selectRow;

    /*
    Metodos
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        txtBuscar = null;
        listaPaises = new ArrayList<>();
        obtenerPaises();
    }

    public void obtenerPaises() {
        if (txtBuscar == null) {
            try {
                listaPaises = paisesService.obtenerPaises();
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Obtener Regiones", msgError));
            }
        } else {
            try {
                listaPaises = paisesService.buscarPais(txtBuscar);
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Regiones", msgError));
            }
        }
    }

    public void nuevoPais() {
        txtPais = null;
        setbNuevo(true);
        setTituloModal("Nuevo País");
        RequestContext.getCurrentInstance().update("vPaises");
        RequestContext.getCurrentInstance().execute("PF('vPaises').show()");
    }

    public void editarPais() {
        if (selectRow != null) {
            if (selectRow.getEstado() == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Modificar Pais",  "País Inactivo, no se permite modificarlo") );
                return;
            }
            txtPais = selectRow.getPais();
            setbNuevo(false);
            setTituloModal("Editar País");
            RequestContext.getCurrentInstance().update("vPaises");
            RequestContext.getCurrentInstance().execute("PF('vPaises').show()");
        }
    }


     public void darDeBaja() {
        if (selectRow != null) {
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Paises paises = paisesService.obtenerUnPais(selectRow.getId());
                paises.setEstado(selectRow.getEstado() == 1 ? 0 : 1);
                paises.setFechamod(date);
                paises.setUsuariosCreaId(usuarios);
                paisesService.modificar(paises);
                for (Paises p : listaPaises) {
                    if (p.getId().equals(selectRow.getId())) {
                        listaPaises.set(listaPaises.indexOf(p), paises );
                    }
                }
                selectRow = paises;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Guardar Pais",  msgError) );
                return;
            }
            RequestContext.getCurrentInstance().update("formPaises");
            RequestContext.getCurrentInstance().execute("PF('confirmar').hide();");

        }
    }

    public void guardarPais() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (bNuevo) {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Paises paises = new Paises();
                paises.setPais(txtPais);
                paises.setEstado(0);
                paises.setUsuariosCreaId(usuarios);
                paises.setFechacrea(date);
                paisesService.guardar(paises);
                listaPaises.add(paises);
                selectRow = paises;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Guardar Pais",  msgError) );
                return;
            }

        } else {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Paises paises = paisesService.obtenerUnPais(selectRow.getId());
                paises.setPais(txtPais);
                paises.setUsuariosModificaId(usuarios);
                paises.setFechamod(date);
                paisesService.modificar(paises);
                selectRow = paises;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Guardar Pais",  msgError) );
                return;
            }
        }
        RequestContext.getCurrentInstance().update("formPaises");
        RequestContext.getCurrentInstance().execute("PF('vPaises').hide();");
    }



    /*
    Propiedades
     */

    public String getTxtPais() {
        return txtPais;
    }

    public void setTxtPais(String txtPais) {
        this.txtPais = txtPais;
    }

    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
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

    public List<Paises> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(List<Paises> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public Paises getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(Paises selectRow) {
        this.selectRow = selectRow;
    }
}
