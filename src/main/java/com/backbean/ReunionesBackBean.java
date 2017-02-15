package com.backbean;

import com.entidades.Oficinas;
import com.entidades.Reuniones;
import com.entidades.ReunionesUsuarios;
import com.entidades.Usuarios;
import com.servicios.service.OficinasService;
import com.servicios.service.ReunionesService;
import com.servicios.service.ReunionesUsuariosService;
import com.servicios.service.UsuariosService;
import com.utils.FacesUtils;
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
 * Created by Pablo Sevilla on 23/1/2017.
 */
@Named
@Scope("view")
public class ReunionesBackBean implements Serializable {

    @Autowired

    ReunionesService reunionesService;

    @Autowired
    OficinasService oficinasService;

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    ReunionesUsuariosService reunionesUsuariosService;

    private Timestamp fechaGet;
    private Integer iOficina;
    private Reuniones reuniones;
    private String msgError;

    private String txtTitulo;
    private Date fechaInicio;
    private Date fechaFin;
    private String txtDescripcion;
    private List<Reuniones> listaReuniones;
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private List<Usuarios> listaUsuarios;
    private String[] listaUsuariosSeleccionados;
    private List<Oficinas> listaOficinas;


    @PostConstruct
    public void init() throws SystemSecurityException {
        iOficina = 1;
        reuniones = new Reuniones();
        obtenerEventos();
        setEventModel(new DefaultScheduleModel());
        obtenerEventos();
        obtenerUsuarios();
        obtenerOficinas();

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

    public void obtenerUsuarios() {
        try {
            listaUsuarios = usuariosService.obtenerAbogados();
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Obtener Usuarios", getMsgError()));
            return;
        }
    }

    public void obtenerUsuariosSeleccionados(Integer id) {
        try {
            List<ReunionesUsuarios> listaReu = reunionesUsuariosService.obtenerReunionesUsuarios(id);
            listaUsuariosSeleccionados = new String[listaReu.size()];
            for (Integer cont = 0; cont < listaReu.size(); cont++) {

                listaUsuariosSeleccionados[cont] = listaReu.get(cont).getUsuariosPorId().getId().toString();
            }
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Obtener Usuarios", getMsgError()));
            return;
        }
    }

    public void cambioOficina(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            iOficina = Integer.parseInt(event.getNewValue().toString());
            obtenerEventos();
        }

    }

    public void obtenerEventos() {
        try {

            listaReuniones = reunionesService.obtenerReuniones(iOficina);
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Obtener Eventos", getMsgError()));
            return;
        }
        try {
            eventModel = new DefaultScheduleModel();

            for (Reuniones re : listaReuniones) {
                DefaultScheduleEvent evt = new DefaultScheduleEvent();
                evt.setDescription(re.getDescripcion());
                SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fechas = re.getInicio().toString();
                String fechaa[] = re.getInicio().toString().substring(0, 10).split("-");
                String dateInString = fechaa[2] + "/" + fechaa[1] + "/" + fechaa[0] + " " + re.getInicio().toString().substring(10, 21).trim();

                Date date = fecha.parse(dateInString);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                evt.setStartDate(cal.getTime());
                fechaa = re.getFin().toString().substring(0, 10).split("-");
                dateInString = fechaa[2] + "/" + fechaa[1] + "/" + fechaa[0] + " " + re.getFin().toString().substring(10, 21).trim();
                date = fecha.parse(dateInString);
                cal = Calendar.getInstance();
                cal.setTime(date);

                evt.setEndDate(cal.getTime());
                evt.setData(re.getId());
                evt.setTitle(re.getTitulo());
                evt.setEditable(true);
                evt.setAllDay(false);
                eventModel.addEvent(evt);
            }
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Obtener Eventos", getMsgError()));
            return;
        }
    }

    // Agregar un nuevo evento
    public void addEvent(ActionEvent actionEvent) {
        Reuniones reuniones = new Reuniones();
        Calendar cal = Calendar.getInstance();
        Timestamp fechamov = new Timestamp(cal.getTimeInMillis());
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            reuniones.setId(Integer.parseInt(event.getData().toString()));
            eventModel.updateEvent(event);
        }


        try {
            Timestamp fecha = new Timestamp(event.getStartDate().getTime());
            Oficinas oficinas = oficinasService.obtenerUnaOficina(iOficina);
            Usuarios usuarios = usuariosService.obtenerUnUsuario(1);
            reuniones.setInicio(fecha);
            fecha = new Timestamp(event.getEndDate().getTime());
            reuniones.setFin(fecha);
            reuniones.setTitulo(event.getTitle());
            reuniones.setDescripcion(event.getDescription());
            reuniones.setOficinaIdPorId(oficinas);
            reuniones.setUsuariosCreaId(usuarios);
            reuniones.setFechacrea(fechamov);
            reuniones.setEstado(0);

            if (reuniones.getId() == null) {
                reunionesService.guardar(reuniones);
            } else {
                reunionesService.modificar(reuniones);
                List<ReunionesUsuarios> listaReu = reunionesUsuariosService.obtenerReunionesUsuarios(reuniones.getId());
                for(Integer cont=0;cont < listaReu.size(); cont++){
                    ReunionesUsuarios reunionesUsuarios = new ReunionesUsuarios();
                    usuarios = usuariosService.obtenerUnUsuario(Integer.parseInt(listaReu.get(cont).getUsuariosPorId().getId().toString()));
                    reunionesUsuarios.setUsuariosPorId(usuarios);
                    reunionesUsuarios.setReunionesPorId(reuniones);
                    reunionesUsuariosService.eliminar(reunionesUsuarios);
                }
            }
            for (Integer cont = 0; cont < listaUsuariosSeleccionados.length; cont++) {
                usuarios = usuariosService.obtenerUnUsuario(Integer.parseInt(listaUsuariosSeleccionados[cont]));
                ReunionesUsuarios reunionesUsuarios = new ReunionesUsuarios();
                reunionesUsuarios.setReunionesPorId(reuniones);
                reunionesUsuarios.setUsuariosPorId(usuarios);
                reunionesUsuariosService.guardar(reunionesUsuarios);
            }

        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Obtener Eventos", getMsgError()));
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
            reuniones = new Reuniones();
            for (Reuniones re : listaReuniones) {
                if (re.getId() == Integer.parseInt(evento.getData().toString())) {
                    obtenerUsuariosSeleccionados(re.getId());
                }
            }
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Seleccionar Evento", getMsgError()));
            return;
        }
        RequestContext.getCurrentInstance().update("eventDialog");
        RequestContext.getCurrentInstance().execute("PF('eventDialog').show()");


    }

    // Crear un evento nuevo
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        listaUsuariosSeleccionados = null;

        RequestContext.getCurrentInstance().update("eventDialog");

    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    /*
    Propiedades
     */
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

    public Reuniones getReuniones() {
        return reuniones;
    }

    public void setReuniones(Reuniones reuniones) {
        this.reuniones = reuniones;
    }

    public Timestamp getFechaGet() {
        return fechaGet;
    }

    public void setFechaGet(Timestamp fechaGet) {
        this.fechaGet = fechaGet;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }



    public List<Oficinas> getListaOficinas() {
        return listaOficinas;
    }

    public void setListaOficinas(List<Oficinas> listaOficinas) {
        this.listaOficinas = listaOficinas;
    }

    public Integer getiOficina() {
        return iOficina;
    }

    public void setiOficina(Integer iOficina) {
        this.iOficina = iOficina;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
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

    public String[] getListaUsuariosSeleccionados() {
        return listaUsuariosSeleccionados;
    }

    public void setListaUsuariosSeleccionados(String[] listaUsuariosSeleccionados) {
        this.listaUsuariosSeleccionados = listaUsuariosSeleccionados;
    }
}
