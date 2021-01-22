package mercadeoucab.dtos;

import java.util.List;

/**
 * Name: DtoEncuestaEstudio
 */
public class DtoEncuestaEstudio extends DtoBase{

    private List<DtoRespuesta> respuestas;
    private DtoPregunta fk_pregunta;
    private DtoEstudio fk_estudio;

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

    /**
     * Name: getFk_pregunta
     * @return
     */
    public DtoPregunta getFk_pregunta() {
        return fk_pregunta;
    }

    /**
     * Name: setFk_pregunta
     * @param fk_pregunta
     */
    public void setFk_pregunta(DtoPregunta fk_pregunta) {
        this.fk_pregunta = fk_pregunta;
    }

    /**
     * Name: getFk_estudio
     * @return
     */
    public DtoEstudio getFk_estudio() {
        return fk_estudio;
    }

    /**
     * Name: setFk_estudio
     * @param fk_estudio
     */
    public void setFk_estudio(DtoEstudio fk_estudio) {
        this.fk_estudio = fk_estudio;
    }
}
