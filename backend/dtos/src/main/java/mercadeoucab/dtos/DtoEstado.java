package mercadeoucab.dtos;

/**
 * Name: DtoEstado
 */
public class DtoEstado extends DtoBase{
    private String nombre;
    private DtoPais fk_pais;

    /**
     * Name: DtoEstado
     * Description: Constructor con id
     * @param id
     * @throws Exception
     */
    public DtoEstado(long id) throws Exception { super(id); }

    /**
     * Name: DtoEstado
     * Description: constructor vacio
     */
    public DtoEstado() {
    }

    /**
     * Name: DtoEstado
     * Description: constructor completo
     * @param nombre
     * @param fk_pais
     */
    public DtoEstado(String nombre, DtoPais fk_pais) {
        this.nombre = nombre;
        this.fk_pais = fk_pais;
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
     * Name: getFk_pais
     * @return fk_pais
     */
    public DtoPais getFk_pais() {
        return fk_pais;
    }

    /**
     * Name: setFk_pais
     * @param fk_pais
     */
    public void setFk_pais(DtoPais fk_pais) {
        this.fk_pais = fk_pais;
    }

}
