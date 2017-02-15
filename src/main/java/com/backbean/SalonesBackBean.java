package com.backbean;

import com.entidades.Oficinas;
import com.entidades.Salones;
import com.entidades.Usuarios;
import com.servicios.service.OficinasService;
import com.servicios.service.SalonesService;
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
 * Created by Pablo Sevilla on 4/2/2017.
 */
@Named
@Scope("view")
public class SalonesBackBean implements Serializable {

    @Autowired
    SalonesService salonesService;

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    OficinasService oficinasService;


    private String tituloModal;
    private Integer iOficina;
    private String txtSalon;
    private boolean bNuevo;
    private String msgError;
    private String txtBuscar;

    private List<Salones> listaSalones;
    private Salones selectRow;
    private List<Oficinas> listaOficinas;

    /*
    Metodos
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        obtenerSalones();
        obtenerOficinas();
    }

    public void obtenerOficinas() {
        try {
            listaOficinas = oficinasService.obtenerOficinas();
        } catch (Exception e) {
            msgError = "Error: " + e.getMessage();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Paises", msgError));
        }
    }

    public void obtenerSalones() {
        try {
         listaSalones = salonesService.obtenerSalones();
        } catch (Exception e) {
            msgError = "Error: " + e.getMessage();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Paises", msgError));
        }
    }

    public void nuevoSalon() {
        txtSalon = null;
        iOficina = null;
        setbNuevo(true);
        setTituloModal("Nuevo Salón");
        RequestContext.getCurrentInstance().update("vSalon");
        RequestContext.getCurrentInstance().execute("PF('vSalon').show()");
    }

    public void editarSalon() {
        if (selectRow != null) {
            if (selectRow.getEstado() == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar Salón",  "Salón Inactiva, no se permite modificarla") );
                return;
            }
            txtSalon = selectRow.getNombre();
            iOficina = selectRow.getOficinaIdPorId().getId();
            setbNuevo(false);
            setTituloModal("Editar Salón");
            RequestContext.getCurrentInstance().update("vSalon");
            RequestContext.getCurrentInstance().execute("PF('vSalon').show()");
        }
    }

    public void guardarSalon() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (bNuevo) {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Oficinas oficinas = oficinasService.obtenerUnaOficina(iOficina);
                Salones salones = new Salones();
                salones.setEstado(0);
                salones.setNombre(txtSalon);
                salones.setOficinaIdPorId(oficinas);
                salones.setFechacrea(date);
                salones.setUsuariosCreaId(usuarios);
                salonesService.guardar(salones);
                listaSalones.add(salones);

            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Salón", msgError));
                return;
            }

        } else {
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Oficinas oficinas = oficinasService.obtenerUnaOficina(iOficina);
                Salones salones = salonesService.obtenerUnSalor(selectRow.getId());
                salones.setNombre(txtSalon);
                salones.setOficinaIdPorId(oficinas);
                salones.setFechamod(date);
                salones.setUsuariosModificaId(usuarios);
                salonesService.modificar(salones);
                selectRow = salones;
                for (Salones sa : listaSalones) {
                    if (sa.getId().equals(selectRow.getId())) {
                        listaSalones.set(listaSalones.indexOf(sa), salones);
                    }
                }
            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Salón", msgError));
                return;
            }
        }
        RequestContext.getCurrentInstance().update("formSalones");
        RequestContext.getCurrentInstance().execute("PF('vSalon').hide();");
    }

    public void darDeBaja() {
        if (selectRow != null) {
            Calendar cal = Calendar.getInstance();
            java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
            try {
                Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
                Salones salones = salonesService.obtenerUnSalor(selectRow.getId());
                salones.setEstado(selectRow.getEstado() == 1 ? 0 : 1);
                salones.setFechamod(date);
                salones.setUsuariosModificaId(usuarios);
                salonesService.modificar(salones);
                selectRow = salones;
                for (Salones sa : listaSalones) {
                    if (sa.getId().equals(selectRow.getId())) {
                        listaSalones.set(listaSalones.indexOf(sa), salones);
                    }
                }


            } catch (Exception e) {
                msgError = "Error: " + e.getMessage();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Salón", msgError));
                return;
            }
        }
        RequestContext.getCurrentInstance().update("formSalones");
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

    public Integer getiOficina() {
        return iOficina;
    }

    public void setiOficina(Integer iOficina) {
        this.iOficina = iOficina;
    }

    public String getTxtSalon() {
        return txtSalon;
    }

    public void setTxtSalon(String txtSalon) {
        this.txtSalon = txtSalon;
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

    public List<Salones> getListaSalones() {
        return listaSalones;
    }

    public void setListaSalones(List<Salones> listaSalones) {
        this.listaSalones = listaSalones;
    }

    public List<Oficinas> getListaOficinas() {
        return listaOficinas;
    }

    public void setListaOficinas(List<Oficinas> listaOficinas) {
        this.listaOficinas = listaOficinas;
    }

    public Salones getSelectRow() {
        return selectRow;
    }

    public void setSelectRow(Salones selectRow) {
        this.selectRow = selectRow;
    }
}
