package mercadeoucab.dtos;


/**
 * Name: DtoOcupacion
 */
public class DtoOcupacion extends DtoBase {


    private String nombre;

    /**
     * Name: DtoOcupacion
     * Description: Constructor vacio
     */
    public DtoOcupacion(){}

    /**
     * Name: DtoOcupacion
     * Description: COnstructor con id
     * @param id
     * @throws Exception
     */
    public DtoOcupacion(long id)throws Exception{super(id);}

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() { return nombre; }

    /**
     * Name: setNombre
     * @param nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
}