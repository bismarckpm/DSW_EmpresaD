package mercadeoucab.dtos;


/**
 * Name: DtoMarca
 */
public class DtoMarca extends DtoBase{

    private String nombre;

    /**
     * Name: DtoMarca
     * Description: constructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoMarca(long id)throws Exception{ super(id); }

    /**
     * Name: DtoMarca
     * Description: constructor vacio
     */
    public DtoMarca(){}

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() {return nombre;}

    /**
     * Name: setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {this.nombre = nombre;}
}
