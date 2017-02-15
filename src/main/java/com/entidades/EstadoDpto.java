package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
@Entity
@SequenceGenerator(name = "est_SEQ", sequenceName = "catalogos.estado_dpto_estado_id_seq", allocationSize = 1)
@Table(name = "estado_dpto", schema = "catalogos")
public class EstadoDpto implements Serializable {
    private Integer id;
    private String nombre;
    private Integer estado;
    private Date fechacrea;
    private Date fechamod;
    private Usuarios usuariosCreaId;
    private Usuarios usuariosModificaId;
    private Paises paisesPorId;



    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "est_SEQ")
    @Column(name = "estado_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "estado")
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "fechacrea")
    public Date getFechacrea() {
        return fechacrea;
    }

    public void setFechacrea(Date fechacrea) {
        this.fechacrea = fechacrea;
    }

    @Basic
    @Column(name = "fechamod")
    public Date getFechamod() {
        return fechamod;
    }

    public void setFechamod(Date fechamod) {
        this.fechamod = fechamod;
    }

    @ManyToOne
    @JoinColumn(name = "idusuariocrea", referencedColumnName = "usuarios_id")
    public Usuarios getUsuariosCreaId() {
        return usuariosCreaId;
    }

    public void setUsuariosCreaId(Usuarios usuariosCreaId) {
        this.usuariosCreaId = usuariosCreaId;
    }

    @ManyToOne
    @JoinColumn(name = "idusuariomod", referencedColumnName = "usuarios_id")
    public Usuarios getUsuariosModificaId() {
        return usuariosModificaId;
    }

    public void setUsuariosModificaId(Usuarios usuariosModificaId) {
        this.usuariosModificaId = usuariosModificaId;
    }

    @ManyToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "pais_id")
    public Paises getPaisesPorId() {
        return paisesPorId;
    }

    public void setPaisesPorId(Paises paisesPorId) {
        this.paisesPorId = paisesPorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoDpto that = (EstadoDpto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        if (fechacrea != null ? !fechacrea.equals(that.fechacrea) : that.fechacrea != null) return false;
        if (fechamod != null ? !fechamod.equals(that.fechamod) : that.fechamod != null) return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(that.usuariosCreaId) : that.usuariosCreaId != null)
            return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(that.usuariosModificaId) : that.usuariosModificaId != null)
            return false;
        if (paisesPorId != null ? paisesPorId.equals(that.paisesPorId) : that.paisesPorId == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (paisesPorId != null ? paisesPorId.hashCode() : 0);
        return result;
    }
}
