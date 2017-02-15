package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Pablo Sevilla on 6/2/2017.
 */
@Entity
@Table(name = "grupos", schema = "catalogos")
@SequenceGenerator(name = "gru_SEQ", sequenceName = "catalogos.grupos_grupo_id_seq", allocationSize = 1)
public class Grupos implements Serializable {
    private Integer id;
    private String grupo;
    private Integer estado;
    private Usuarios usuariosCreaId;
    private Date fechacrea;
    private Usuarios usuariosModificaId;
    private Date fechamod;



    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "gru_SEQ")
    @Column(name = "grupo_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "grupo")
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupos grupos = (Grupos) o;

        if (id != null ? !id.equals(grupos.id) : grupos.id != null) return false;
        if (grupo != null ? !grupo.equals(grupos.grupo) : grupos.grupo != null) return false;
        if (estado != null ? !estado.equals(grupos.estado) : grupos.estado != null) return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(grupos.usuariosCreaId) : grupos.usuariosCreaId != null)
            return false;
        if (fechacrea != null ? !fechacrea.equals(grupos.fechacrea) : grupos.fechacrea != null) return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(grupos.usuariosModificaId) : grupos.usuariosModificaId != null)
            return false;
        if (fechamod != null ? fechamod.equals(grupos.fechamod) : grupos.fechamod == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (grupo != null ? grupo.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        return result;
    }
}
