package mercadeoucab.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DtoRespuesta extends DtoBase{

    private String respuesta;
    private DtoOpcion dtoopcion;
    private DtoEsqueletoEncuestaEstudio dto_esqueleto_encuesta_estudio;
    private DtoUsuario Dtousuario;

    public String getRespuesta() {return respuesta;}
    public void setRespuesta(String respuesta) {this.respuesta = respuesta;}

    public DtoOpcion get_dtoopcion() {return dtoopcion;}
    public void set_dtoopcion(DtoOpcion DTOO) {this.dtoopcion = DTOO;}

    public DtoEsqueletoEncuestaEstudio getDto_esqueleto_encuesta_estudio() {return dto_esqueleto_encuesta_estudio;}
    public void setDto_esqueleto_encuesta_estudio(DtoEsqueletoEncuestaEstudio dto_esqueleto_encuesta_estudio) { this.dto_esqueleto_encuesta_estudio = dto_esqueleto_encuesta_estudio;}

    public DtoUsuario getDtousuario() { return Dtousuario; }
    public void setDtousuario(DtoUsuario dtousuario) { Dtousuario = dtousuario; }

}
