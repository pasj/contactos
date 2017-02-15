package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * Created by Pablo Sevilla on 19/1/2017.
 */
@Entity
@Table(name = "reuniones", schema = "agenda")
@SequenceGenerator(name = "reu_SEQ", sequenceName = "agenda.reuniones_reunion_id_seq", allocationSize = 1)
public class Reuniones implements Serializable {
    private Integer id;
    private String titulo;
    private Timestamp inicio;
    private Timestamp fin;
    private String descripcion;
    private Oficinas oficinaIdPorId;
    private Usuarios usuariosCreaId;
    private Timestamp fechacrea;
    private Usuarios usuariosModificaId;
    private Timestamp fechamod;
    private Integer estado;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "reu_SEQ")
    @Column(name = "reunion_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "inicio")
    public Timestamp getInicio() {
        return inicio;
    }

    public void setInicio(Timestamp inicio) {
        this.inicio = inicio;
    }

    @Basic
    @Column(name = "fin")
    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToOne
    @JoinColumn(name = "oficina_id", referencedColumnName = "oficina_id")
    public Oficinas getOficinaIdPorId() {
        return oficinaIdPorId;
    }

    public void setOficinaIdPorId(Oficinas oficinaIdPorId) {
        this.oficinaIdPorId = oficinaIdPorId;
    }

    @ManyToOne
    @JoinColumn(name = "idusuariocrea", referencedColumnName = "usuarios_id")
    public Usuarios getUsuariosCreaId() {
        return usuariosCreaId;
    }

    public void setUsuariosCreaId(Usuarios usuariosCreaId) {
        this.usuariosCreaId = usuariosCreaId;
    }

    @Basic
    @Column(name = "fechacrea")
    public Timestamp getFechacrea() {
        return fechacrea;
    }

    public void setFechacrea(Timestamp fechacrea) {
        this.fechacrea = fechacrea;
    }

    @ManyToOne
    @JoinColumn(name = "idusuariomod", referencedColumnName = "usuarios_id")
    public Usuarios getUsuariosModificaId() {
        return usuariosModificaId;
    }

    public void setUsuariosModificaId(Usuarios usuariosModificaId) {
        this.usuariosModificaId = usuariosModificaId;
    }

    @Basic
    @Column(name = "fechamod")
    public Timestamp getFechamod() {
        return fechamod;
    }

    public void setFechamod(Timestamp fechamod) {
        this.fechamod = fechamod;
    }


    @Basic
    @Column(name = "estado")
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reuniones reuniones = (Reuniones) o;

        if (id != null ? !id.equals(reuniones.id) : reuniones.id != null) return false;
        if (titulo != null ? !titulo.equals(reuniones.titulo) : reuniones.titulo != null) return false;
        if (inicio != null ? !inicio.equals(reuniones.inicio) : reuniones.inicio != null) return false;
        if (fin != null ? !fin.equals(reuniones.fin) : reuniones.fin != null) return false;
        if (descripcion != null ? !descripcion.equals(reuniones.descripcion) : reuniones.descripcion != null)
            return false;
        if (estado != null ? !estado.equals(reuniones.estado) : reuniones.estado != null) return false;
        if (oficinaIdPorId != null ? !oficinaIdPorId.equals(reuniones.oficinaIdPorId) : reuniones.oficinaIdPorId != null)
            return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(reuniones.usuariosCreaId) : reuniones.usuariosCreaId != null)
            return false;
        if (fechacrea != null ? !fechacrea.equals(reuniones.fechacrea) : reuniones.fechacrea != null) return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(reuniones.usuariosModificaId) : reuniones.usuariosModificaId != null)
            return false;
        if (fechamod != null ? fechamod.equals(reuniones.fechamod) : reuniones.fechamod == null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (inicio != null ? inicio.hashCode() : 0);
        result = 31 * result + (fin != null ? fin.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (oficinaIdPorId != null ? oficinaIdPorId.hashCode() : 0);
        result = 31 * result + (estado!= null ? estado.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        return result;
    }
}
