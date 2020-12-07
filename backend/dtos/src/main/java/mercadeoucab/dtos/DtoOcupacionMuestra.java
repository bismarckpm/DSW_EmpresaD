package mercadeoucab.dtos;


public class DtoOcupacionMuestra extends DtoBase{

    private DtoOpcion fk_ocupacion;

    private DtoMuestraPoblacion fk_muestra_poblacion;

    public DtoOcupacionMuestra(){}
    public DtoOcupacionMuestra(long id)throws Exception{super(id);}

    public DtoMuestraPoblacion getFk_muestra_poblacion() { return fk_muestra_poblacion; }
    public void setFk_muestra_poblacion(DtoMuestraPoblacion fk_muestra_poblacion) { this.fk_muestra_poblacion = fk_muestra_poblacion; }

    public DtoOpcion getFk_ocupacion() { return fk_ocupacion; }
    public void setFk_ocupacion(DtoOpcion fk_ocupacion) { this.fk_ocupacion = fk_ocupacion; }
}
