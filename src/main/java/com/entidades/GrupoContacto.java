package com.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pablo Sevilla on 6/2/2017.
 */
@Entity
@SequenceGenerator(name = "oficon_SEQ", sequenceName = "ccatalogos.grupo_contacto_grupo_contacto_id_seq", allocationSize = 1)
@Table(name = "grupo_contacto", schema = "catalogos")
public class GrupoContacto implements Serializable{
    private Integer id;
    private Grupos grupoId;
    private Contacto contactoId;



    @Id
    @Column(name = "grupo_contacto_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @ManyToOne
    @JoinColumn(name = "grupo_id", columnDefinition = "grupo_id")
    public Grupos getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Grupos grupoId) {
        this.grupoId = grupoId;
    }


    @ManyToOne
    @JoinColumn(name = "contacto_id", referencedColumnName = "contacto_id")
    public Contacto getContactoId() {
        return contactoId;
    }

    public void setContactoId(Contacto contactoId) {
        this.contactoId = contactoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrupoContacto that = (GrupoContacto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (grupoId != null ? !grupoId.equals(that.grupoId) : that.grupoId != null) return false;
        if (contactoId != null ? contactoId.equals(that.contactoId) : that.contactoId == null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (grupoId != null ? grupoId.hashCode() : 0);
        result = 31 * result + (contactoId != null ? contactoId.hashCode() : 0);
        return result;
    }
}
