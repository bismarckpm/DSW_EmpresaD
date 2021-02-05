package mercadeoucab.dtos;


/**
 * Name: DtoRespuesta
 */
public class DtoRespuesta extends DtoBase{

    private String respuesta;
    private DtoOpcion dtoopcion;
    private DtoEncuestaEstudio dtoEncuestaEstudio;
    private DtoUsuario Dtousuario;

    /**
     * Name: DtoRespuesta
     * Description: Constructor vacio
     */
    public DtoRespuesta(){}

    /**
     * Name: DtoRespuesta
     * Description: Constructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoRespuesta(long id) throws Exception {super(id);}

    /**
     * Name: getRespuesta
     * @return respuesta
     */
    public String getRespuesta() {return respuesta;}

    /**
     * Name: setRespuesta
     * @param respuesta
     */
    public void setRespuesta(String respuesta) {this.respuesta = respuesta;}

    /**
     * Name: get_dtoopcion
     * Description: dtoopcion
     * @return
     */
    public DtoOpcion get_dtoopcion() {return dtoopcion;}

    /**
     * Name: set_dtoopcion
     * @param DTOO
     */
    public void set_dtoopcion(DtoOpcion DTOO) {this.dtoopcion = DTOO;}

    /**
     * Name: getDtoopcion
     * @return dtoopcion
     */
    public DtoOpcion getDtoopcion() {
        return dtoopcion;
    }

    /**
     * Name: setDtoopcion
     * @param dtoopcion
     */
    public void setDtoopcion(DtoOpcion dtoopcion) {
        this.dtoopcion = dtoopcion;
    }

    /**
     * Name: getDtoEncuestaEstudio
     * @return dtoEncuestaEstudio
     */
    public DtoEncuestaEstudio getDtoEncuestaEstudio() {
        return dtoEncuestaEstudio;
    }

    /**
     * Name: setDtoEncuestaEstudio
     * @param dtoEncuestaEstudio
     */
    public void setDtoEncuestaEstudio(DtoEncuestaEstudio dtoEncuestaEstudio) {
        this.dtoEncuestaEstudio = dtoEncuestaEstudio;
    }

    /**
     * Name: getDtousuario
     * @return Dtousuario
     */
    public DtoUsuario getDtousuario() { return Dtousuario; }

    /**
     * Name: setDtousuario
     * @param dtousuario
     */
    public void setDtousuario(DtoUsuario dtousuario) { Dtousuario = dtousuario; }

}
