package mercadeoucab.dtos;

import java.util.List;

public class DtoEncuestaEstudio extends DtoBase{

    List<DtoRespuesta> respuestas;

    public DtoEncuestaEstudio(long id) throws Exception {
        super(id);
    }

    public DtoEncuestaEstudio() {
    }

    public List<DtoRespuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<DtoRespuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
