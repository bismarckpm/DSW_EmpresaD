package mercadeoucab.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class DtoEstudio extends DtoBase{


    private String estado;
    private String tipo;
    private int escuestasEsperadas;
    private DtoSolicitud solicitud;
    private DtoUsuario fk_usuario;
    private DtoMuestraPoblacion fk_muestra_poblacion;
    private List<DtoPregunta> preguntas;

    public DtoEstudio(long id) throws Exception {
        super(id);
    }

    public DtoEstudio() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEscuestasEsperadas() {
        return escuestasEsperadas;
    }

    public void setEscuestasEsperadas(int escuestasEsperadas) {
        this.escuestasEsperadas = escuestasEsperadas;
    }

    public DtoSolicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(DtoSolicitud solicitud) {
        this.solicitud = solicitud;
    }

    public DtoUsuario getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(DtoUsuario fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public DtoMuestraPoblacion getFk_muestra_poblacion() {
        return fk_muestra_poblacion;
    }

    public void setFk_muestra_poblacion(DtoMuestraPoblacion fk_muestra_poblacion) {
        this.fk_muestra_poblacion = fk_muestra_poblacion;
    }

    public List<DtoPregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<DtoPregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
