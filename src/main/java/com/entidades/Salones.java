package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Pablo Sevilla on 4/2/2017.
 */
@Entity
@SequenceGenerator(name = "sala_SEQ", sequenceName = "catalogos.salones_salon_id_seq", allocationSize = 1)
@Table(name = "salones", schema = "catalogos")
public class Salones implements Serializable {
    private Integer id;
    private String nombre;
    private Integer estado;
    private Oficinas oficinaIdPorId;
    private Usuarios usuariosCreaId;
    private Date fechacrea;
    private Usuarios usuariosModificaId;
    private Date fechamod;



    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "sala_SEQ")
    @Column(name = "salon_id")
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
    public Date getFechacrea() {
        return fechacrea;
    }

    public void setFechacrea(Date fechacrea) {
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
    public Date getFechamod() {
        return fechamod;
    }

    public void setFechamod(Date fechamod) {
        this.fechamod = fechamod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salones salones = (Salones) o;

        if (!id.equals(salones.id)) return false;
        if (nombre != null ? !nombre.equals(salones.nombre) : salones.nombre != null) return false;
        if (estado != null ? !estado.equals(salones.estado) : salones.estado != null) return false;
        if (oficinaIdPorId != null ? !oficinaIdPorId.equals(salones.oficinaIdPorId) : salones.oficinaIdPorId != null)
            return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(salones.usuariosCreaId) : salones.usuariosCreaId != null)
            return false;
        if (fechacrea != null ? !fechacrea.equals(salones.fechacrea) : salones.fechacrea != null) return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(salones.usuariosModificaId) : salones.usuariosModificaId != null)
            return false;
        if (fechamod != null ? fechamod.equals(salones.fechamod) : salones.fechamod == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (oficinaIdPorId != null ? oficinaIdPorId.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        return result;
    }
}
