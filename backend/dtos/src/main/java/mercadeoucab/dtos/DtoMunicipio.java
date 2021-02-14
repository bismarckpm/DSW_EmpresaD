package mercadeoucab.dtos;


/**
 * Name: DtoMunicipio
 */
public class DtoMunicipio extends DtoBase{
    private String nombre;
    private DtoEstado fk_estado;

    /**
     * Name: DtoMunicipio
     * Description: constructor con id
     * @param id
     * @throws Exception
     */
    public DtoMunicipio(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoMunicipio
     * Description: constructor vacio
     */
    public DtoMunicipio() {
    }

    /**
     * Name:DtoMunicipio
     * Description: constructor completo
     * @param nombre
     * @param fk_estado
     */
    public DtoMunicipio(String nombre, DtoEstado fk_estado) {
        this.nombre = nombre;
        this.fk_estado = fk_estado;
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

    /**
     * Name: getFk_estado
     * @return fk_estado
     */
    public DtoEstado getFk_estado() {
        return fk_estado;
    }

    /**
     * Name: setFk_estado
     * @param fk_estado
     */
    public void setFk_estado(DtoEstado fk_estado) {
        this.fk_estado = fk_estado;
    }
}
