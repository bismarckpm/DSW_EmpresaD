package mercadeoucab.dtos;

import mercadeoucab.excepciones.PruebaExcepcion;

import java.sql.Date;

/**
 * Name: DtoBase
 */
public class DtoBase
{
    private long _id;
    private int activo;
    private Date creado_el;
    private Date modificado_el;

    /**
     * Name: DtoBase
     * Description: contructuctor para un DtoBase
     * @param _id
     * @param activo
     * @param creado_el
     * @param modificado_el
     */
    public DtoBase(long _id, int activo, Date creado_el, Date modificado_el) {
        this._id = _id;
        this.activo = activo;
        this.creado_el = creado_el;
        this.modificado_el = modificado_el;
    }

    /**
     * Name: get_id
     * @return _id
     */
    public long get_id() {
        return _id;
    }

    /**
     * Name: set_id
     * @param _id
     */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**
     * Name: getActivo
     * @return
     */
    public int getActivo() {
        return activo;
    }

    /**
     * Name: setActivo
     * @param activo
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }

    /**
     * Name: getCreado_el
     * @return creado_el
     */
    public Date getCreado_el() {
        return creado_el;
    }

    /**
     * Name: setCreado_el
     * @param creado_el
     */
    public void setCreado_el(Date creado_el) {
        this.creado_el = creado_el;
    }

    /**
     * Name: getModificado_el
     * @return modificado_el
     */
    public Date getModificado_el() {
        return modificado_el;
    }

    /**
     * Name: setModificado_el
     * @param modificado_el
     */
    public void setModificado_el(Date modificado_el) {
        this.modificado_el = modificado_el;
    }

    /**
     * Name: DtoBase
     * Description: constructor para un DtoBase con un id
     * @param id
     * @throws Exception
     */
    public DtoBase(long id ) throws Exception
    {
        setId( id );
    }

    /**
     * Name: DtoBase
     * Description: constructor vacio
     */
    public DtoBase()
    {
    }

    /**
     * Name: seId
     * @param id
     * @throws PruebaExcepcion
     */
    public void setId( long id ) throws PruebaExcepcion
    {
        if ( id >= 0 )
        {
            this._id = id;
        }
        else
        {
            throw new PruebaExcepcion( );
        }
    }
}
