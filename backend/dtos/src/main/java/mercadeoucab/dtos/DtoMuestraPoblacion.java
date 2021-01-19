package mercadeoucab.dtos;

/**
 * Name: DtoMuestraPoblacion
 */
public class DtoMuestraPoblacion extends DtoBase{

    private String genero;
    private  int nivelEconomico;
    private String nivelAcademico;
    private int rangoEdadInicio;
    private int rangoEdadFin;
    private int cantidadHijos;
    private DtoParroquia fk_lugar;
    private DtoOcupacion dtoOcupacion;

    /**
     * Name: DtoMuestraPoblacion
     * Description: Constructor vacio
     */
    public DtoMuestraPoblacion(){}

    /**
     * Name: DtoMuestraPoblacion
     * Description: constructor con ud
     * @param id
     * @throws Exception
     */
    public DtoMuestraPoblacion(long id)throws Exception{super(id);}

    /**
     * Name: DtoMuestraPoblacion
     * Description: Constructor completo
     * @param genero
     * @param nivelEconomico
     * @param nivelAcademico
     * @param rangoEdadInicio
     * @param rangoEdadFin
     * @param cantidadHijos
     */
    public DtoMuestraPoblacion(String genero, int nivelEconomico, String nivelAcademico, int rangoEdadInicio, int rangoEdadFin, int cantidadHijos) {
        this.genero = genero;
        this.nivelEconomico = nivelEconomico;
        this.nivelAcademico = nivelAcademico;
        this.rangoEdadInicio = rangoEdadInicio;
        this.rangoEdadFin = rangoEdadFin;
        this.cantidadHijos = cantidadHijos;
    }

    /**
     * Name: getGenero
     * @return genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Name: setGenero
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Name: getNivelEconomico
     * @return nivelEconomico
     */
    public int getNivelEconomico() {
        return nivelEconomico;
    }

    /**
     * Name: setNivelEconomico
     * @param nivelEconomico
     */
    public void setNivelEconomico(int nivelEconomico) {
        this.nivelEconomico = nivelEconomico;
    }

    /**
     * Name: getNivelAcademico
     * @return nivelAcademico
     */
    public String getNivelAcademico() {
        return nivelAcademico;
    }

    /**
     * Name: setNivelAcademico
     * @param nivelAcademico
     */
    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    /**
     * Name: getRangoEdadInicio
     * @return rangoEdadInicio
     */
    public int getRangoEdadInicio() {
        return rangoEdadInicio;
    }

    /**
     * Name: setRangoEdadInicio
     * @param rangoEdadInicio
     */
    public void setRangoEdadInicio(int rangoEdadInicio) {
        this.rangoEdadInicio = rangoEdadInicio;
    }

    /**
     * Name: getRangoEdadFin
     * @return rangoEdadFin
     */
    public int getRangoEdadFin() {
        return rangoEdadFin;
    }

    /**
     * Name: setRangoEdadFin
     * @param rangoEdadFin
     */
    public void setRangoEdadFin(int rangoEdadFin) {
        this.rangoEdadFin = rangoEdadFin;
    }

    /**
     * Name: getCantidadHijos
     * @return cantidadHijos
     */
    public int getCantidadHijos() {
        return cantidadHijos;
    }

    /**
     * Name: setCantidadHijos
     * @param cantidadHijos
     */
    public void setCantidadHijos(int cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    /**
     * Name: getFk_lugar
     * @return fk_lugar
     */
    public DtoParroquia getFk_lugar() {
        return fk_lugar;
    }

    /**
     * Name: setFk_lugar
     * @param fk_lugar
     */
    public void setFk_lugar(DtoParroquia fk_lugar) {
        this.fk_lugar = fk_lugar;
    }

    /**
     * Name: getDtoOcupacion
     * @return dtoOcupacion
     */
    public DtoOcupacion getDtoOcupacion() {
        return dtoOcupacion;
    }

    /**
     * Name: setDtoOcupacion
     * @param dtoOcupacion
     */
    public void setDtoOcupacion(DtoOcupacion dtoOcupacion) {
        this.dtoOcupacion = dtoOcupacion;
    }
}
