package mercadeoucab.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: DtoEstudio
 */
public class DtoEstudio extends DtoBase{


    private String estado;
    private String tipo;
    private int encuestasEsperadas;
    private DtoUsuario fk_usuario;
    private List<DtoPregunta> preguntas;
    private DtoSolicitud solicitud;
    private List<DtoEncuestaEstudio> encuestaEstudio;

    /**
     * Name: DtoEstudio
     * Description: Constructor con id
     * @param id
     * @throws Exception
     */
    public DtoEstudio(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoEstudio
     * Description: constructor vacio
     */
    public DtoEstudio() {
    }

    /**
     * Name: getEstado
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Name: setEstado
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Name: getTipo
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Name: setTipo
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Name: getEncuestasEsperadas
     * @return encuestasEsperadas
     */
    public int getEncuestasEsperadas() {
        return encuestasEsperadas;
    }

    /**
     * Name: setEncuestasEsperadas
     * @param encuestasEsperadas
     */
    public void setEncuestasEsperadas(int encuestasEsperadas) {
        this.encuestasEsperadas = encuestasEsperadas;
    }

    /**
     * Name:  getFk_usuario
     * @return fk_usuario
     */
    public DtoUsuario getFk_usuario() {
        return fk_usuario;
    }

    /**
     * Name: setFk_usuario
     * @param fk_usuario
     */
    public void setFk_usuario(DtoUsuario fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    /**
     * Name: DtoSolicitud
     * @return solicitud
     */
    public DtoSolicitud getSolicitud() {
        return solicitud;
    }

    /**
     * Name: setSolicitud
     * @param solicitud
     */
    public void setSolicitud(DtoSolicitud solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * Name: getPreguntas
     * @return preguntas
     */
    public List<DtoPregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * Name: setPreguntas
     * @param preguntas
     */
    public void setPreguntas(List<DtoPregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * Name: getEncuestaEstudio
     * @return encuestaEstudio
     */
    public List<DtoEncuestaEstudio> getEncuestaEstudio() {
        return encuestaEstudio;
    }
    /**
     * Name: setEncuestaEstudio
     * @param encuestaEstudio
     */
    public void setEncuestaEstudio(List<DtoEncuestaEstudio> encuestaEstudio) {
        this.encuestaEstudio = encuestaEstudio;
    }

    public void addEncuestaEstudio( DtoEncuestaEstudio dtoEncuestaEstudio){
        if ( this.encuestaEstudio == null)
                this.encuestaEstudio = new ArrayList<>();
        this.encuestaEstudio.add( dtoEncuestaEstudio);
    }

    public void addPregunta( DtoPregunta dtoPregunta){
        if ( this.preguntas == null)
            this.preguntas = new ArrayList<>();
        this.preguntas.add( dtoPregunta);
    }
}
