package com.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Pablo Sevilla on 27/1/2017.
 */
@Entity
@Table(name = "contacto", schema = "contactos")
@SequenceGenerator(name = "cont_SEQ", sequenceName = "contactos.contacto_contacto_id_seq", allocationSize = 1)
public class Contacto implements Serializable {
    private Integer id;
    private String nombre;
    private String empresa;
    private String cargo;
    private String direccion;
    private String apartado;
    private String correo;
    private String webpage;
    private String telefono;
    private String extension;
    private String telefonoPersonal;
    private String fax;
    private String movil;
    private Timestamp fechanac;
    private Integer estado;
    private String codigoArea;
    private Date fechacrea;
    private Date fechamod;
    private Paises paisesPorId;
    private Regiones regionesPorId;
    private TipoContacto tipoContactoPorId;
    private EstadoDpto estadoDptoPorId;
    private Usuarios usuariosCreaId;
    private Usuarios usuariosModificaId;


    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "cont_SEQ")
    @Column(name = "contacto_id")
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
    @Column(name = "empresa")
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Basic
    @Column(name = "cargo")
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "apartado")
    public String getApartado() {
        return apartado;
    }

    public void setApartado(String apartado) {
        this.apartado = apartado;
    }

    @Basic
    @Column(name = "correo")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "webpage")
    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "extension")
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Basic
    @Column(name = "telefono_personal")
    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "movil")
    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    @Basic
    @Column(name = "fechanac")
    public Timestamp getFechanac() {
        return fechanac;
    }

    public void setFechanac(Timestamp fechanac) {
        this.fechanac = fechanac;
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
    @Column(name = "codigo_area")
    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
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
    @JoinColumn(name = "pais_id", referencedColumnName = "pais_id")
    public Paises getPaisesPorId() {
        return paisesPorId;
    }

    public void setPaisesPorId(Paises paisesPorId) {
        this.paisesPorId = paisesPorId;
    }

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    public Regiones getRegionesPorId() {
        return regionesPorId;
    }

    public void setRegionesPorId(Regiones regionesPorId) {
        this.regionesPorId = regionesPorId;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_contacto_id", referencedColumnName = "tipo_contacto_id")
    public TipoContacto getTipoContactoPorId() {
        return tipoContactoPorId;
    }

    public void setTipoContactoPorId(TipoContacto tipoContactoPorId) {
        this.tipoContactoPorId = tipoContactoPorId;
    }

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id")
    public EstadoDpto getEstadoDptoPorId() {
        return estadoDptoPorId;
    }

    public void setEstadoDptoPorId(EstadoDpto estadoDptoPorId) {
        this.estadoDptoPorId = estadoDptoPorId;
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

        Contacto contacto = (Contacto) o;

        if (id != null ? !id.equals(contacto.id) : contacto.id != null) return false;
        if (nombre != null ? !nombre.equals(contacto.nombre) : contacto.nombre != null) return false;
        if (empresa != null ? !empresa.equals(contacto.empresa) : contacto.empresa != null) return false;
        if (cargo != null ? !cargo.equals(contacto.cargo) : contacto.cargo != null) return false;
        if (direccion != null ? !direccion.equals(contacto.direccion) : contacto.direccion != null) return false;
        if (apartado != null ? !apartado.equals(contacto.apartado) : contacto.apartado != null) return false;
        if (correo != null ? !correo.equals(contacto.correo) : contacto.correo != null) return false;
        if (webpage != null ? !webpage.equals(contacto.webpage) : contacto.webpage != null) return false;
        if (telefono != null ? !telefono.equals(contacto.telefono) : contacto.telefono != null) return false;
        if (extension != null ? !extension.equals(contacto.extension) : contacto.extension != null) return false;
        if (telefonoPersonal != null ? !telefonoPersonal.equals(contacto.telefonoPersonal) : contacto.telefonoPersonal != null)
            return false;
        if (fax != null ? !fax.equals(contacto.fax) : contacto.fax != null) return false;
        if (movil != null ? !movil.equals(contacto.movil) : contacto.movil != null) return false;
        if (fechanac != null ? !fechanac.equals(contacto.fechanac) : contacto.fechanac != null) return false;
        if (estado != null ? !estado.equals(contacto.estado) : contacto.estado != null) return false;
        if (codigoArea != null ? !codigoArea.equals(contacto.codigoArea) : contacto.codigoArea != null) return false;
        if (fechacrea != null ? !fechacrea.equals(contacto.fechacrea) : contacto.fechacrea != null) return false;
        if (fechamod != null ? !fechamod.equals(contacto.fechamod) : contacto.fechamod != null) return false;
        if (paisesPorId != null ? !paisesPorId.equals(contacto.paisesPorId) : contacto.paisesPorId != null)
            return false;
        if (regionesPorId != null ? !regionesPorId.equals(contacto.regionesPorId) : contacto.regionesPorId != null)
            return false;
        if (tipoContactoPorId != null ? !tipoContactoPorId.equals(contacto.tipoContactoPorId) : contacto.tipoContactoPorId != null)
            return false;
        if (estadoDptoPorId != null ? !estadoDptoPorId.equals(contacto.estadoDptoPorId) : contacto.estadoDptoPorId != null)
            return false;
        if (usuariosCreaId != null ? !usuariosCreaId.equals(contacto.usuariosCreaId) : contacto.usuariosCreaId != null)
            return false;
        if (usuariosModificaId != null ? usuariosModificaId.equals(contacto.usuariosModificaId) : contacto.usuariosModificaId == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (empresa != null ? empresa.hashCode() : 0);
        result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (apartado != null ? apartado.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (webpage != null ? webpage.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (telefonoPersonal != null ? telefonoPersonal.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (movil != null ? movil.hashCode() : 0);
        result = 31 * result + (fechanac != null ? fechanac.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (codigoArea != null ? codigoArea.hashCode() : 0);
        result = 31 * result + (fechacrea != null ? fechacrea.hashCode() : 0);
        result = 31 * result + (fechamod != null ? fechamod.hashCode() : 0);
        result = 31 * result + (paisesPorId != null ? paisesPorId.hashCode() : 0);
        result = 31 * result + (regionesPorId != null ? regionesPorId.hashCode() : 0);
        result = 31 * result + (tipoContactoPorId != null ? tipoContactoPorId.hashCode() : 0);
        result = 31 * result + (estadoDptoPorId != null ? estadoDptoPorId.hashCode() : 0);
        result = 31 * result + (usuariosCreaId != null ? usuariosCreaId.hashCode() : 0);
        result = 31 * result + (usuariosModificaId != null ? usuariosModificaId.hashCode() : 0);
        return result;
    }
}
