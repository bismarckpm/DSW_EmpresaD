package mercadeoucab.dtos;

import java.util.List;

/**
 * Name: DtoEncuestaEstudio
 */
public class DtoEncuestaEstudio extends DtoBase{

    List<DtoRespuesta> respuestas;

    /**
     * Name:DtoEncuestaEstudio
     * Description: constructor con id
     * @param id
     * @throws Exception
     */
    public DtoEncuestaEstudio(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoEncuestaEstudio
     * Description: constructor vacio
     */
    public DtoEncuestaEstudio() {
    }

    /**
     * Name: getRespuestas
     * @return respuestas
     */
    public List<DtoRespuesta> getRespuestas() {
        return respuestas;
    }

    /**
     * Name: setRespuestas
     * @param respuestas
     */
    public void setRespuestas(List<DtoRespuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
