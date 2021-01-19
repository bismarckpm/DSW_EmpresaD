package mercadeoucab.dtos;

import java.sql.Date;

/**
 * Name: DtoPais
 */
public class DtoPais extends  DtoBase{

    private String nombre;

    /**
     * Name: DtoPais
     * Description: Constructor con id
     * @param id
     * @throws Exception
     */
    public DtoPais(long id) throws Exception { super(id); }

    /**
     * Name: DtoPais
     * Description: Constructor vacio
     */
    public DtoPais() {
    }

    /**
     * Name: DtoPais
     * Descripcion: Constructor completo
     * @param _id
     * @param activo
     * @param creado_el
     * @param modificado_el
     * @param nombre
     */
    public DtoPais(long _id, int activo, Date creado_el, Date modificado_el, String nombre) {
        super(_id, activo, creado_el, modificado_el);
        this.nombre = nombre;
    }

    /**
     * Name: DtoPais
     * Descripcion: Constructor con nombre
     * @param nombre
     */
    public DtoPais(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Name: setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
