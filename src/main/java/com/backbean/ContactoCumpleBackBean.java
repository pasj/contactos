package com.backbean;

import com.entidades.Contacto;
import com.servicios.service.ContactoService;
import com.utils.SystemSecurityException;


import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Pablo Sevilla on 9/2/2017.
 */
@Named
@Scope("view")
public class ContactoCumpleBackBean implements Serializable {

    @Autowired
    ContactoService contactoService;

    private Integer iDia;
    private Integer iMes;
    private String msgError;

    private List<Contacto> listaContactos;



    /****************************************************************
     * METODOS
     ****************************************************************/
    @PostConstruct
    public void init() throws SystemSecurityException {
        Calendar cal = Calendar.getInstance();
        iDia = cal.get(Calendar.DAY_OF_MONTH);
        iMes = cal.get(Calendar.MONTH) + 1;
        obtenerCumple(iMes, iDia);

    }

    public  void enviarCorreo() {
        if (listaContactos.size() > 0 ) {
            ExternalContext ctx =
                    FacesContext.getCurrentInstance().getExternalContext();
            String ctxPath =
                    ((ServletContext) ctx.getContext()).getContextPath();
            try {
                // Usar el contexto de JSF para invalidar la sesión,
                // NO EL DE SERVLETS (nada de HttpServletRequest)
                ((HttpSession) ctx.getSession(false)).invalidate();

                // Redirección de nuevo con el contexto de JSF,
                // si se usa una HttpServletResponse fallará.
                // Sin embargo, como ya está fuera del ciclo de vida
                // de JSF se debe usar la ruta completa -_-U
                ctx.redirect(ctxPath + "/envarcorreocumple.xhtml");
            } catch (IOException ex) {
                setMsgError("Error: " + ex.getMessage());
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "obtenerGrupos", getMsgError()));
            }
        } else {
            setMsgError("No existen contactos que cumplan años");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "obtenerGrupos", getMsgError()));
        }
    }

    public void obtenerCumple(Integer mes, Integer dia) {
        try {
            listaContactos = contactoService.obtenerContactoCumple(mes, dia);
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "obtenerGrupos", getMsgError()));
            return;
        }
    }

    /*
    Variables
     */

    public Integer getiDia() {
        return iDia;
    }

    public void setiDia(Integer iDia) {
        this.iDia = iDia;
    }

    public Integer getiMes() {
        return iMes;
    }

    public void setiMes(Integer iMes) {
        this.iMes = iMes;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }


}
