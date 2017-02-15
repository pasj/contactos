package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Pablo Sevilla on 23/1/2017.
 */
@Entity
@Table(name = "usuarios", schema = "catalogos")
@SequenceGenerator(name = "usua_SEQ", sequenceName = "catalogos.usuarios_usuarios_id_seq", allocationSize = 1)
public class Usuarios implements Serializable {
    private Integer id;
    private String login;
    private String nombre;
    private String clave;
    private Integer estado;
    private Oficinas idoficina;
    private Usuarios usuariosCreaId;
    private Date fechacrea;
    private Usuarios usuariosModificaId;
    private Date fechamod;
    private Integer esabogado;
    private Integer cambiar;


    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "usua_SEQ")
    @Column(name = "usuarios_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    @Column(name = "clave")
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
    public Oficinas getIdoficina() {
        return idoficina;
    }

    public void setIdoficina(Oficinas idoficina) {
        this.idoficina = idoficina;
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

    @Basic
    @Column(name = "esabogado")
    public Integer getEsabogado() {
        return esabogado;
    }


    public void setEsabogado(Integer esabogado) {
        this.esabogado = esabogado;
    }

    @Basic
    @Column(name = "cambiar")
    public Integer getCambiar() {
        return cambiar;
    }

    public void setCambiar(Integer cambiar) {
        this.cambiar = cambiar;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuarios usuarios = (Usuarios) o;

        if (id != null ? !id.equals(usuarios.id) : usuarios.id != null) return false;
        if (login != null ? !login.equals(usuarios.login) : usuarios.login != null) return false;
        if (nombre != null ? !nombre.equals(usuarios.nombre) : usuarios.nombre != null) return false;
        if (clave != null ? !clave.equals(usuarios.clave) : usuarios.clave != null) return false;
        if (estado != null ? !estado.equals(usuarios.estado) : usuarios.estado != null) return false;
        if (idoficina != null ? !idoficina.equals(usuarios.idoficina) : usuarios.idoficina != null) return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(usuarios.usuariosCreaId) : usuarios.usuariosCreaId != null)
            return false;
        if (fechacrea != null ? !fechacrea.equals(usuarios.fechacrea) : usuarios.fechacrea != null) return false;
        if (usuariosModificaId != null ? !usuariosModificaId.equals(usuarios.usuariosModificaId) : usuarios.usuariosModificaId != null)
            return false;
        if (fechamod != null ? !fechamod.equals(usuarios.fechamod) : usuarios.fechamod != null) return false;
        if (esabogado != null ? !esabogado.equals(usuarios.esabogado) : usuarios.esabogado != null) return false;
        if (cambiar != null ? cambiar.equals(usuarios.cambiar) : usuarios.cambiar == null) return false ;

        return  true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (clave != null ? clave.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (idoficina != null ? idoficina.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        result = 31 * result + (esabogado != null ? esabogado.hashCode() : 0);
        result = 31 * result + (cambiar != null ? cambiar.hashCode() : 0);
        return result;
    }
}
