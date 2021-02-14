package mercadeoucab.dtos;

/**
 * Name: DtoOpcion
 */
public class DtoOpcion extends DtoBase {


    private String nombre_opcion;
    private DtoPregunta Dtopregunta;

    /**
     * Name: DtoOpcion
     * Description: Constructor vacio
     */
    public DtoOpcion(){}

    /**
     * Name: DtoOpcion
     * Description: Constructor con id
     * @param id
     * @throws Exception
     */
    public DtoOpcion(long id)throws Exception{super(id);}

    /**
     * Name: getNombre_opcion
     * @return nombre_opcion
     */
    public String getNombre_opcion() {return nombre_opcion;}

    /**
     * Name: setNombre_opcion
     * @param nombre_opcion
     */
    public void setNombre_opcion(String nombre_opcion) {this.nombre_opcion = nombre_opcion;}

    /**
     * Name: get_Dtopregunta
     * @return Dtopregunta
     */
    public DtoPregunta get_Dtopregunta() {return Dtopregunta;}

    /**
     * Name: set_Dtopregunta
     * @param DTOP
     */
    public void set_Dtopregunta(DtoPregunta DTOP) {this.Dtopregunta = DTOP;}
}
