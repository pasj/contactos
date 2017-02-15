package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
@Entity
@SequenceGenerator(name = "pai_SEQ", sequenceName = "catalogos.paises_pais_id_seq", allocationSize = 1)
@Table(name = "paises", schema = "catalogos")
public class Paises implements Serializable {
    private Integer id;
    private String pais;
    private Integer estado;
    private Date fechacrea;
    private Date fechamod;
    private Regiones regionPorId;
    private Usuarios usuariosCreaId;
    private Usuarios usuariosModificaId;



    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "pai_SEQ")
    @Column(name = "pais_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pais")
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    public Regiones getRegionPorId() {
        return regionPorId;
    }

    public void setRegionPorId(Regiones regionPorId) {
        this.regionPorId = regionPorId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paises paises = (Paises) o;

        if (id != null ? !id.equals(paises.id) : paises.id != null) return false;
        if (pais != null ? !pais.equals(paises.pais) : paises.pais != null) return false;
        if (estado != null ? !estado.equals(paises.estado) : paises.estado != null) return false;
        if (fechacrea != null ? !fechacrea.equals(paises.fechacrea) : paises.fechacrea != null) return false;
        if (fechamod != null ? !fechamod.equals(paises.fechamod) : paises.fechamod != null) return false;
        if (regionPorId != null ? !regionPorId.equals(paises.regionPorId) : paises.regionPorId != null) return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(paises.usuariosCreaId) : paises.usuariosCreaId != null)
            return false;
        if (usuariosModificaId != null ? usuariosModificaId.equals(paises.usuariosModificaId) : paises.usuariosModificaId == null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        result = 31 * result + (regionPorId != null ? regionPorId.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        return result;
    }


}
