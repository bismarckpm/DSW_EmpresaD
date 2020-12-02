package mercadeoucab.dtos;

public class DtoEsqueletoEncuestaEstudio extends DtoBase {

    private DtoPregunta preguntaDto;

    public DtoEsqueletoEncuestaEstudio(){}

    public DtoPregunta get_Dtopregunta() {return preguntaDto;}
    public void setFk_Dtopregunta(DtoPregunta DTOP) {this.preguntaDto = DTOP;}
}
