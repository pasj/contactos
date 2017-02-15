package com.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pablo Sevilla on 29/1/2017.
 */
@Entity
@Table(name = "reuniones_usuarios", schema = "agenda")
public class ReunionesUsuarios implements Serializable {
    private Integer id;
    private Reuniones reunionesPorId;
    private Usuarios usuariosPorId;



    @Id
    @Column(name = "reuniones_usuarios_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "reunion_id", referencedColumnName = "reunion_id")
    public Reuniones getReunionesPorId() {
        return reunionesPorId;
    }

    public void setReunionesPorId(Reuniones reunionesPorId) {
        this.reunionesPorId = reunionesPorId;
    }

    @ManyToOne
    @JoinColumn(name = "usuarios_id", referencedColumnName = "usuarios_id")
    public Usuarios getUsuariosPorId() {
        return usuariosPorId;
    }

    public void setUsuariosPorId(Usuarios usuariosPorId) {
        this.usuariosPorId = usuariosPorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReunionesUsuarios that = (ReunionesUsuarios) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reunionesPorId != null ? !reunionesPorId.equals(that.reunionesPorId) : that.reunionesPorId != null)
            return false;
        if (usuariosPorId != null ? usuariosPorId.equals(that.usuariosPorId) : that.usuariosPorId == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reunionesPorId != null ? reunionesPorId.hashCode() : 0);
        result = 31 * result + (usuariosPorId != null ? usuariosPorId.hashCode() : 0);
        return result;
    }
}
