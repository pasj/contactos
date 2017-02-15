package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Pablo Sevilla on 25/1/2017.
 */
@Entity
@SequenceGenerator(name = "reg_SEQ", sequenceName = "catalogos.regiones_region_id_seq", allocationSize = 1)
@Table(name = "regiones", schema = "catalogos")
public class Regiones implements Serializable{
    private Integer id;
    private String region;
    private Integer estado;
    private Usuarios usuariosCreaId;
    private Date fechacrea;
    private Usuarios usuariosModificaId;
    private Date fechamod;



    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "reg_SEQ")
    @Column(name = "region_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

        Regiones regiones = (Regiones) o;

        if (id != null ? !id.equals(regiones.id) : regiones.id != null) return false;
        if (region != null ? !region.equals(regiones.region) : regiones.region != null) return false;
        if (estado != null ? !estado.equals(regiones.estado) : regiones.estado != null) return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(regiones.usuariosCreaId) : regiones.usuariosCreaId != null)
            return false;
        if (fechacrea != null ? !fechacrea.equals(regiones.fechacrea) : regiones.fechacrea != null) return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(regiones.usuariosModificaId) : regiones.usuariosModificaId != null)
            return false;
        if (fechamod != null ? fechamod.equals(regiones.fechamod) : regiones.fechamod == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        return result;
    }
}
