package mercadeoucab.dtos;

/**
 * Name: DtoPresentacion
 */
public class DtoPresentacion extends DtoBase{

    private String cantidad;
    private String tipo;

    /**
     * Name: DtoPresentacion
     * Description: COnstructor con id
     * @param id
     * @throws Exception
     */
    public DtoPresentacion(long id)throws Exception { super(id); }

    /**
     * Name: DtoPresentacion
     * Description: constructor vacio
     */
    public DtoPresentacion(){}

    /**
     * Name: getCantidad
     * @return cantidad
     */
    public String getCantidad() { return cantidad; }

    /**
     * Name: setCantidad
     * @param cantidad
     */
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }

    /**
     * Name: getTipo
     * @return tipo
     */
    public String getTipo() { return tipo; }

    /**
     * Name: setTipo
     * @param tipo
     */
    public void setTipo(String tipo) { this.tipo = tipo; }
}
