package com.backbean;

import com.entidades.Oficinas;
import com.entidades.Usuarios;
import com.servicios.service.OficinasService;
import com.servicios.service.UsuariosService;
import com.utils.FacesUtils;
import com.utils.SystemSecurityException;
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
 * Created by Pablo Sevilla on 24/1/2017.
 */
@Named
@Scope("view")
public class UsuariosBackBean implements Serializable {

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    OficinasService oficinasService;


    private boolean bPrincipal;
    private boolean bEditar;
    private boolean bNuevo;
    private  String msgError;
    private String key;
    private String iv;

    private String txtLogin;
    private String txtNombre;
    private String txtClave;
    private String txtConfirma;
    private Integer idOficina;
    private Integer iEstado;
    private boolean bCambiar;
    private boolean bAbogado;

    private String tituloModal;

    private List<Usuarios> listaUsuarios;
    private Usuarios selectedRow;

    /*
    Metodos
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        obtenerUsuarios();
        key = "92AE31A79FEEB2A3"; //llave
        iv = "0123456789ABCDEF"; // vector de inicialización
        bPrincipal = true;
        bEditar = false;
    }

    public void obtenerUsuarios() {
        try {
            listaUsuarios = usuariosService.obtenerUsuarios(1);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
    }

    public void editarUsuario() {
        if (selectedRow != null) {
            bEditar = true;
            bPrincipal = false;
            bNuevo = false;
            txtLogin = selectedRow.getLogin();
            txtNombre = selectedRow.getNombre();
            bAbogado = selectedRow.getEsabogado() == 1 ? true : false;

        }
    }

    public void cancelar() {
        bPrincipal = true;
        bEditar = false;
    }

    public void nuevoUsuario() {
        txtNombre = null;
        txtLogin = null;
        txtClave = null;
        txtConfirma = null;
        idOficina = null;
        iEstado = null;
        bEditar = true;
        bPrincipal = false;
        bNuevo = true;


    }

    public void guardarUsuario() {
        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        if (bNuevo) {
            try {
                Oficinas oficinas = oficinasService.obtenerUnaOficina(1);
                Usuarios usuariosc = usuariosService.obtenerUnUsuario(1);
                Usuarios usuarios = new Usuarios();
                usuarios.setEstado(1);
                usuarios.setLogin(txtLogin);
                usuarios.setNombre(txtNombre);
                usuarios.setIdoficina(oficinas);
                usuarios.setUsuariosCreaId(usuariosc);
                usuarios.setFechacrea(date);
                usuariosService.guardar(usuarios);
                listaUsuarios.add(usuarios);
                selectedRow = usuarios;

            } catch (Exception e) {
                setMsgError("Error: " + e.getMessage());
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Guardar Region", getMsgError()) );
                return;
            }

        } else {
            try {
                Oficinas oficinas = oficinasService.obtenerUnaOficina(1);
                Usuarios usuariosc = usuariosService.obtenerUnUsuario(1);
                Usuarios usuarios = usuariosService.obtenerUnUsuario(selectedRow.getId());
                usuarios.setEstado(1);
                usuarios.setLogin(txtLogin);
                usuarios.setNombre(txtNombre);
                usuarios.setIdoficina(oficinas);
                usuarios.setUsuariosCreaId(usuariosc);
                usuarios.setFechacrea(date);
                usuariosService.modificar(usuarios);
                selectedRow = usuarios;
            } catch (Exception e) {
                setMsgError("Error: " + e.getMessage());
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Guardar Region", getMsgError()) );
                return;
            }
        }
    }

    /*
    Propiedades
     */


    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuarios getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(Usuarios selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(String txtLogin) {
        this.txtLogin = txtLogin;
    }

    public String getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(String txtNombre) {
        this.txtNombre = txtNombre;
    }

    public String getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(String txtClave) {
        this.txtClave = txtClave;
    }

    public String getTxtConfirma() {
        return txtConfirma;
    }

    public void setTxtConfirma(String txtConfirma) {
        this.txtConfirma = txtConfirma;
    }

    public Integer getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    public Integer getiEstado() {
        return iEstado;
    }

    public void setiEstado(Integer iEstado) {
        this.iEstado = iEstado;
    }

    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
    }

    public boolean isbCambiar() {
        return bCambiar;
    }

    public void setbCambiar(boolean bCambiar) {
        this.bCambiar = bCambiar;
    }

    public boolean isbAbogado() {
        return bAbogado;
    }

    public void setbAbogado(boolean bAbogado) {
        this.bAbogado = bAbogado;
    }

    public boolean isbPrincipal() {
        return bPrincipal;
    }

    public void setbPrincipal(boolean bPrincipal) {
        this.bPrincipal = bPrincipal;
    }

    public boolean isbEditar() {
        return bEditar;
    }

    public void setbEditar(boolean bEditar) {
        this.bEditar = bEditar;
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
}
