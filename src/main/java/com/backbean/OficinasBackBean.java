package com.backbean;

import com.entidades.Oficinas;
import com.entidades.Usuarios;
import com.servicios.service.OficinasService;
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
public class OficinasBackBean implements Serializable {
    
    @Autowired
    OficinasService oficinasService;

    @Autowired
    UsuariosService usuariosService;

    private String txtOficina;
    private String tituloModal;
    private boolean bNuevo;
    private String msgError;
    private String txtBuscar;
    
    private List<Oficinas> listaOficinas;
    private Oficinas selectRow;

    /*
    Metodos
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        obtenerOficinas();

    }
    
    public void obtenerOficinas() {
        try {
            listaOficinas = oficinasService.obtenerOficinas();
        } catch (Exception e) {
            msgError = "Error: " + e.getMessage();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Oficinas", msgError));
        }
    }

    public void nuevaOficina() {
        txtOficina = null;
        setbNuevo(true);
        setTituloModal("NuevOficina");
        RequestContext.getCurrentInstance().update("vOficinas");
        RequestContext.getCurrentInstance().execute("PF('vOficinas').show()");
    }

    public void editarOficina() {
        if (selectRow != null) {
            if (selectRow.getEstado() == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Modificar Oficina",  "Oficina Inactiva, no se permite modificarla") );
                return;
            }
            txtOficina = selectRow.getOficina();
            setbNuevo(false);
            setTituloModal("Editar Oficina");
            RequestContext.getCurrentInstance().update("vOficinas");
            RequestContext.getCurrentInstance().execute("PF('vOficinas').show()");
        }
    }

    public void guardarOficinas() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (bNuevo) {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Oficinas oficinas = new Oficinas();
                oficinas.setEstado(0);
                oficinas.setOficina(txtOficina);
                oficinas.setFechacrea(date);
                oficinas.setUsuariosCreaId(usuarios);
                oficinasService.guardar(oficinas);
                listaOficinas.add(oficinas);

            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Oficinas", msgError));
            }
        } else {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Oficinas oficinas = new Oficinas();
                oficinas.setOficina(txtOficina);
                oficinas.setFechamod(date);
                oficinas.setUsuariosModificaId(usuarios);
                oficinasService.modificar(oficinas);
                for (Oficinas of : listaOficinas) {
                    if (of.getId().equals(selectRow.getId())) {
                        listaOficinas.set(listaOficinas.indexOf(of), oficinas);
                    }
                }
                selectRow = oficinas;

            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Oficinas", msgError));
            }
        }
        RequestContext.getCurrentInstance().update("formOficinas");
        RequestContext.getCurrentInstance().execute("PF('vOficinas').hide();");
    }

    public  void darDeBaja() {
        if (selectRow != null) {
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Oficinas oficinas = oficinasService.obtenerUnaOficina(selectRow.getId());
                oficinas.setEstado(selectRow.getEstado() == 1 ? 0 : 1);
                oficinas.setFechamod(date);
                oficinas.setUsuariosModificaId(usuarios);
                oficinasService.modificar(oficinas);
                for (Oficinas of : listaOficinas) {
                    if (of.getId().equals(selectRow.getId())) {
                        listaOficinas.set(listaOficinas.indexOf(of), oficinas);
                    }
                }
                selectRow = oficinas;
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Activar/Inactivar Oficinas", msgError));
            }
        }
        RequestContext.getCurrentInstance().update("formOficinas");
        RequestContext.getCurrentInstance().execute("PF('confirmar').hide();");
    }
    /*
    Propiedades
     */
    public String getTxtOficina() {
        return txtOficina;
    }

    public void setTxtOficina(String txtOficina) {
        this.txtOficina = txtOficina;
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

    public List<Oficinas> getListaOficinas() {
        return listaOficinas;
    }

    public void setListaOficinas(List<Oficinas> listaOficinas) {
        this.listaOficinas = listaOficinas;
    }

    public Oficinas getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(Oficinas selectRow) {
        this.selectRow = selectRow;
    }
}
