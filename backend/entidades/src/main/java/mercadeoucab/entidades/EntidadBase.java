package mercadeoucab.entidades;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Date;

@MappedSuperclass
public class EntidadBase implements Serializable
{

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "activo")
    private int activo;

    @Column( name = "creado_el")
    private Date creado_el;

    @Column( name = "modificado_el")
    private  Date modificado_el;

    public void set_id(long _id) {
        this._id = _id;
    }

    public EntidadBase(int activo, Date creado_el) {
        this.activo = activo;
        this.creado_el = creado_el;
    }

    public EntidadBase(long _id, Date modificado_el) {
        this._id = _id;
        this.modificado_el = modificado_el;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Date getCreado_el() {
        return creado_el;
    }

    public void setCreado_el(Date creado_el) {
        this.creado_el = creado_el;
    }

    public Date getModificado_el() {
        return modificado_el;
    }

    public void setModificado_el(Date modificado_el) {
        this.modificado_el = modificado_el;
    }

    public EntidadBase(long id )
    {
        _id = id;
    }

    public EntidadBase()
    {
    }

    public long get_id()
    {
        return _id;
    }

}
