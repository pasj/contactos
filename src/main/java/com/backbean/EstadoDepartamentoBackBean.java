package com.backbean;

import com.entidades.EstadoDpto;
import com.entidades.Paises;
import com.entidades.Usuarios;
import com.servicios.service.EstadoDptoService;
import com.servicios.service.PaisesService;
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
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pablo Sevilla on 1/2/2017.
 */
@Named
@Scope("view")
public class EstadoDepartamentoBackBean implements Serializable {

    @Autowired
    EstadoDptoService estadoDptoService;

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    PaisesService paisesService;

    private String tituloModal;
    private Integer iPais;
    private String txtEstado;
    private boolean bNuevo;
    private String msgError;
    private String txtBuscar;
    private String sPais;

    private List<EstadoDpto> listaEstado;
    private List<Paises> listaPaises;
    private EstadoDpto selectRow;


    /*
    Metodos
     */

    @PostConstruct
    public void init() throws SystemSecurityException {
        obtenerPaises();
        obtenerEstados(listaPaises.get(0).getId());
    }


    public void obtenerPaises() {
        try {
            listaPaises = paisesService.obtenerPaisesActivos();
        } catch (Exception e) {
            msgError = "Error: " + e.getMessage();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Paises", msgError));
        }
    }

    public void obtenerEstados(Integer id) {
        try {
            listaEstado = estadoDptoService.obtenerEstadoDpto(id);
            Paises pai = paisesService.obtenerUnPais(id);
            sPais = pai.getPais();
        } catch (Exception e) {
            msgError = "Error: " + e.getMessage();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Obtener Estados/Departamentos", msgError));
        }
    }

    public void cambioEstados(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            obtenerEstados(Integer.parseInt(event.getNewValue().toString()));

        }
    }

    public void nuevoEstado() {
        txtEstado = null;
        setbNuevo(true);
        setTituloModal("Nuevo Estado/Departamento");
        RequestContext.getCurrentInstance().update("vEstados");
        RequestContext.getCurrentInstance().execute("PF('vEstados').show()");
    }

    public void editarEstado() {
        if (selectRow != null) {
            if (selectRow.getEstado() == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar Estado/Departamento",  "Estado/Departamento Inactivo, no se permite modificarlo") );
                return;
            }
            txtEstado = selectRow.getNombre();
            setbNuevo(false);
            setTituloModal("Editar Estado");
            RequestContext.getCurrentInstance().update("vEstados");
            RequestContext.getCurrentInstance().execute("PF('vEstados').show()");
        }

    }


    public void guardarEstado() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (bNuevo) {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Paises paises = paisesService.obtenerUnPais(iPais);
                EstadoDpto estadoDpto = new EstadoDpto();
                estadoDpto.setEstado(0);
                estadoDpto.setNombre(txtEstado);
                estadoDpto.setPaisesPorId(paises);
                estadoDpto.setUsuariosCreaId(usuarios);
                estadoDpto.setFechacrea(date);
                estadoDptoService.guardar(estadoDpto);
                listaEstado.add(estadoDpto);
                selectRow = estadoDpto;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Estado/Deparetamento", msgError));
                return;
            }

        } else {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Paises paises = paisesService.obtenerUnPais(iPais);
                EstadoDpto estadoDpto = new EstadoDpto();
                estadoDpto.setNombre(txtEstado);
                estadoDpto.setPaisesPorId(paises);
                estadoDpto.setUsuariosModificaId(usuarios);
                estadoDpto.setFechamod(date);
                estadoDptoService.modificar(estadoDpto);
                for (EstadoDpto es : listaEstado) {
                    if (es.getId().equals(selectRow.getId())) {
                        listaEstado.set(listaEstado.indexOf(es), estadoDpto);
                    }
                }
                selectRow = estadoDpto;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Estado/Deparetamento", msgError));
                return;
            }
        }
        RequestContext.getCurrentInstance().update("formPaises");
        RequestContext.getCurrentInstance().execute("PF('vEstados').hide();");
    }

    public void darDeBaja() {
        if (selectRow != null) {
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                EstadoDpto estadoDpto = estadoDptoService.obtenerUnEstado(selectRow.getId());
                estadoDpto.setEstado(selectRow.getEstado() == 1 ? 0 : 1);
                estadoDpto.setFechamod(date);
                estadoDpto.setUsuariosCreaId(usuarios);
                estadoDptoService.modificar(estadoDpto);
                for (EstadoDpto e : listaEstado) {
                    if (e.getId().equals(selectRow.getId())) {
                        listaEstado.set(listaEstado.indexOf(e), estadoDpto);
                    }
                }
                selectRow = estadoDpto;
            }  catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Dar de Baja Estado/Departamento", msgError));
                return;
            }
            RequestContext.getCurrentInstance().update("formEstados");
            RequestContext.getCurrentInstance().execute("PF('confirmar').hide();");
        }
    }
    /*
    Propiedades
     */
    public Integer getiPais() {
        return iPais;
    }

    public void setiPais(Integer iPais) {
        this.iPais = iPais;
    }

    public String getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(String txtEstado) {
        this.txtEstado = txtEstado;
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

    public List<EstadoDpto> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<EstadoDpto> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public List<Paises> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(List<Paises> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public EstadoDpto getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(EstadoDpto selectRow) {
        this.selectRow = selectRow;
    }

    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
    }

    public String getsPais() {
        return sPais;
    }

    public void setsPais(String sPais) {
        this.sPais = sPais;
    }
}
