package com.backbean;

import com.entidades.*;
import com.servicios.service.OficinasService;
import com.servicios.service.SalasService;
import com.servicios.service.UsuariosService;
import com.utils.SystemSecurityException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Pablo Sevilla on 3/2/2017.
 */
@Named
@Scope("view")
public class SalasBackBean implements Serializable {

    @Autowired
    SalasService salasService;

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    OficinasService oficinasService;

    private Timestamp fechaGet;
    private Integer iOficina;
    private String msgError;
    private Salas salas;
    private String txtTitulo;
    private Date fechaInicio;
    private Date fechaFin;
    private String txtDescripcion;
    private List<Reuniones> listaReuniones;
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    private List<Oficinas> listaOficinas;
    private List<Salas> listaSalas;

    /*
    Metodos
     */

    @PostConstruct
    public void init() throws SystemSecurityException {
        iOficina = 1;
        obtenerOficinas();
        obtenerEventos();
    }

    public void obtenerOficinas() {
        try {
            listaOficinas = oficinasService.obtenerOficinas();
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Oficinas", getMsgError()));
            return;
        }
    }

    public void obtenerEventos() {
        try {

            listaSalas = salasService.obtenerAgendaSalas(iOficina);
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Eventos", getMsgError()));
            return;
        }
        try {
            eventModel = new DefaultScheduleModel();

            for (Salas sa : listaSalas) {
                DefaultScheduleEvent evt = new DefaultScheduleEvent();
                evt.setDescription(sa.getDescripcion());
                SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fechas = sa.getInicio().toString();
                String fechaa[] = sa.getInicio().toString().substring(0, 10).split("-");
                String dateInString = fechaa[2] + "/" + fechaa[1] + "/" + fechaa[0] + " " + sa.getInicio().toString().substring(10, 21).trim();

                Date date = fecha.parse(dateInString);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                evt.setStartDate(cal.getTime());
                fechaa = sa.getFin().toString().substring(0, 10).split("-");
                dateInString = fechaa[2] + "/" + fechaa[1] + "/" + fechaa[0] + " " + sa.getFin().toString().substring(10, 21).trim();
                date = fecha.parse(dateInString);
                cal = Calendar.getInstance();
                cal.setTime(date);

                evt.setEndDate(cal.getTime());
                evt.setData(sa.getId());
                evt.setTitle(sa.getTitulo());
                evt.setEditable(true);
                evt.setAllDay(false);
                eventModel.addEvent(evt);
            }
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar Evento", getMsgError()));
            return;
        }
    }

        // Agregar un nuevo evento

    public void addEvent(ActionEvent actionEvent) {
        Salas salas = new Salas();
        Calendar cal = Calendar.getInstance();
        Timestamp fechamov = new Timestamp(cal.getTimeInMillis());
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            salas.setId(Integer.parseInt(event.getData().toString()));
            eventModel.updateEvent(event);
        }


        try {
            Timestamp fecha = new Timestamp(event.getStartDate().getTime());
            Oficinas oficinas = oficinasService.obtenerUnaOficina(iOficina);
            Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
            salas.setInicio(fecha);
            fecha = new Timestamp(event.getEndDate().getTime());
            salas.setFin(fecha);
            salas.setTitulo(event.getTitle());
            salas.setDescripcion(event.getDescription());
            salas.setOficinaIdPorId(oficinas);
            salas.setUsuariosCreaId(usuarios);
            salas.setFechacrea(fechamov);
            salas.setEstado(0);

            if (salas.getId() == null) {
                salasService.guardar(salas);
            } else {
                salasService.modificar(salas);
            }

        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guarda Evento", getMsgError()));
            return;
        }
        event = new DefaultScheduleEvent();

        RequestContext.getCurrentInstance().update("myschedule");
        RequestContext.getCurrentInstance().execute("PF('eventDialog').hide();");
    }

    // Selecciona un evento programado
    public void onEventSelect(SelectEvent selectEvent) {
        ScheduleEvent evento = (ScheduleEvent) selectEvent.getObject();
        event = (ScheduleEvent) selectEvent.getObject();

        try {
            salas = new Salas();
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccionar Evento", getMsgError()));
            return;
        }
        RequestContext.getCurrentInstance().update("eventDialog");
        RequestContext.getCurrentInstance().execute("PF('eventDialog').show()");
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    // Crear un evento nuevo
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        RequestContext.getCurrentInstance().update("eventDialog");

    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void cambioOficina(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            iOficina = Integer.parseInt(event.getNewValue().toString());
            obtenerEventos();
        }

    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /*
    Propiedades
     */
    public Timestamp getFechaGet() {
        return fechaGet;
    }

    public void setFechaGet(Timestamp fechaGet) {
        this.fechaGet = fechaGet;
    }

    public Integer getiOficina() {
        return iOficina;
    }

    public void setiOficina(Integer iOficina) {
        this.iOficina = iOficina;
    }

    public String getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(String txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(String txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public List<Reuniones> getListaReuniones() {
        return listaReuniones;
    }

    public void setListaReuniones(List<Reuniones> listaReuniones) {
        this.listaReuniones = listaReuniones;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public List<Oficinas> getListaOficinas() {
        return listaOficinas;
    }

    public void setListaOficinas(List<Oficinas> listaOficinas) {
        this.listaOficinas = listaOficinas;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public Salas getSalas() {
        return salas;
    }

    public void setSalas(Salas salas) {
        this.salas = salas;
    }

    public List<Salas> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(List<Salas> listaSalas) {
        this.listaSalas = listaSalas;
    }
}
