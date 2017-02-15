package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Pablo Sevilla on 3/2/2017.
 */
@Entity
@Table(name = "salas", schema = "agenda")
@SequenceGenerator(name = "sal_SEQ", sequenceName = "agenda.salas_sala_id_seq", allocationSize = 1)
public class Salas implements Serializable {
    private Integer id;
    private String titulo;
    private Timestamp inicio;
    private Timestamp fin;
    private String descripcion;
    private Integer estado;
    private Oficinas oficinaIdPorId;
    private Usuarios usuariosCreaId;
    private Timestamp fechacrea;
    private Usuarios usuariosModificaId;
    private Timestamp fechamod;



    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "sal_SEQ")
    @Column(name = "sala_id")
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

    @Basic
    @Column(name = "estado")
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salas salas = (Salas) o;

        if (id != null ? !id.equals(salas.id) : salas.id != null) return false;
        if (titulo != null ? !titulo.equals(salas.titulo) : salas.titulo != null) return false;
        if (inicio != null ? !inicio.equals(salas.inicio) : salas.inicio != null) return false;
        if (fin != null ? !fin.equals(salas.fin) : salas.fin != null) return false;
        if (descripcion != null ? !descripcion.equals(salas.descripcion) : salas.descripcion != null) return false;
        if (estado != null ? !estado.equals(salas.estado) : salas.estado != null) return false;
        if (oficinaIdPorId != null ? !oficinaIdPorId.equals(salas.oficinaIdPorId) : salas.oficinaIdPorId != null)
            return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(salas.usuariosCreaId) : salas.usuariosCreaId != null)
            return false;
        if (fechacrea != null ? !fechacrea.equals(salas.fechacrea) : salas.fechacrea != null) return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(salas.usuariosModificaId) : salas.usuariosModificaId != null)
            return false;
        if (fechamod != null ? !fechamod.equals(salas.fechamod) : salas.fechamod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (inicio != null ? inicio.hashCode() : 0);
        result = 31 * result + (fin != null ? fin.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (oficinaIdPorId != null ? oficinaIdPorId.hashCode() : 0);
        result = 31 * result + (estado!= null ? estado.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        return result;
    }
}
