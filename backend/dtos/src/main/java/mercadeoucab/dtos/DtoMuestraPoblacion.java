package mercadeoucab.dtos;

public class DtoMuestraPoblacion extends DtoBase{

    private String genero;
    private  int nivelEconomico;
    private String nivelAcademico;
    private int rangoEdadInicio;
    private int rangoEdadFin;
    private int cantidadHijos;
    private DtoParroquia fk_lugar;

    public DtoMuestraPoblacion(){}
    public DtoMuestraPoblacion(long id)throws Exception{super(id);}

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNivelEconomico() {
        return nivelEconomico;
    }

    public void setNivelEconomico(int nivelEconomico) {
        this.nivelEconomico = nivelEconomico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public int getRangoEdadInicio() {
        return rangoEdadInicio;
    }

    public void setRangoEdadInicio(int rangoEdadInicio) {
        this.rangoEdadInicio = rangoEdadInicio;
    }

    public int getRangoEdadFin() {
        return rangoEdadFin;
    }

    public void setRangoEdadFin(int rangoEdadFin) {
        this.rangoEdadFin = rangoEdadFin;
    }

    public int getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(int cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public DtoParroquia getFk_lugar() {
        return fk_lugar;
    }

    public void setFk_lugar(DtoParroquia fk_lugar) {
        this.fk_lugar = fk_lugar;
    }
}
