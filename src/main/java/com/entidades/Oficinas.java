package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
@Entity
@Table(name = "oficinas", schema = "catalogos")
@SequenceGenerator(name = "ofi_SEQ", sequenceName = "catalogos.oficinas_oficina_id_seq", allocationSize = 1)
public class Oficinas implements Serializable {
    private Integer id;
    private String oficina;
    private Integer estado;
    private Usuarios usuariosCreaId;
    private Date fechacrea;
    private Usuarios usuariosModificaId;
    private Date fechamod;


    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "ofi_SEQ")
    @Column(name = "oficina_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "oficina")
    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
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

        Oficinas oficinas = (Oficinas) o;

        if (id != null ? !id.equals(oficinas.id) : oficinas.id != null) return false;
        if (oficina != null ? !oficina.equals(oficinas.oficina) : oficinas.oficina != null) return false;
        if (estado != null ? !estado.equals(oficinas.estado) : oficinas.estado != null) return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(oficinas.usuariosCreaId) : oficinas.usuariosCreaId != null)
            return false;
        if (fechacrea != null ? !fechacrea.equals(oficinas.fechacrea) : oficinas.fechacrea != null) return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(oficinas.usuariosModificaId) : oficinas.usuariosModificaId != null)
            return false;
        if (fechamod != null ? fechamod.equals(oficinas.fechamod) : oficinas.fechamod == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (oficina != null ? oficina.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        return result;
    }
}
