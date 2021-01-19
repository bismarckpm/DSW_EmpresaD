package mercadeoucab.dtos;

/**
 * Name:DtoCategoria
 */
public class DtoCategoria  extends DtoBase{
    private String nombre;

    /**
     * Name: DtoCategoria
     * Description: crea un nuevo DtoCategoria con un id
     * @param id
     * @throws Exception
     */
    public DtoCategoria(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoCategoria
     * Description: constructor vacio
     */
    public DtoCategoria() {
    }

    /**
     * Name: getNombre
     * Description: obtiene el nombre de la categoria
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Name: setCategoria
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
