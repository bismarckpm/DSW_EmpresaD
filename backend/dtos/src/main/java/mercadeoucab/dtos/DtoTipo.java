package mercadeoucab.dtos;

/**
 * Name: DtoTipo
 */
public class DtoTipo extends DtoBase{

    private String nombre;
    private DtoSubCategoria subCategoria;

    /**
     * Name: DtoTipo
     * Description: Constructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoTipo(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoTipo
     * Description: Constructor vacio
     */
    public DtoTipo() {
    }

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtoSubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(DtoSubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }
}
