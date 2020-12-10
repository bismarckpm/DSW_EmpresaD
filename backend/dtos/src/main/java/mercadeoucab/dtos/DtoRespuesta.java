package mercadeoucab.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DtoRespuesta extends DtoBase{

    private String respuesta;
    private DtoOpcion dtoopcion;
    private DtoEncuestaEstudio dtoEncuestaEstudio;
    private DtoUsuario Dtousuario;

    public String getRespuesta() {return respuesta;}
    public void setRespuesta(String respuesta) {this.respuesta = respuesta;}

    public DtoOpcion get_dtoopcion() {return dtoopcion;}
    public void set_dtoopcion(DtoOpcion DTOO) {this.dtoopcion = DTOO;}

    public DtoOpcion getDtoopcion() {
        return dtoopcion;
    }

    public void setDtoopcion(DtoOpcion dtoopcion) {
        this.dtoopcion = dtoopcion;
    }

    public DtoEncuestaEstudio getDtoEncuestaEstudio() {
        return dtoEncuestaEstudio;
    }

    public void setDtoEncuestaEstudio(DtoEncuestaEstudio dtoEncuestaEstudio) {
        this.dtoEncuestaEstudio = dtoEncuestaEstudio;
    }

    public DtoUsuario getDtousuario() { return Dtousuario; }
    public void setDtousuario(DtoUsuario dtousuario) { Dtousuario = dtousuario; }

}
