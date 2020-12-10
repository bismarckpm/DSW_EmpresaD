package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table(name = "respuesta")
public class Respuesta extends EntidadBase {

    @Column(name = "respuesta")
    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "fk_opcion")
    private Opcion fk_opcion;

    @ManyToOne
    @JoinColumn(name = "fk_encuesta_estudio")
    private EncuestaEstudio encuesta_estudio;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario fk_usuario;

    public Respuesta(){}
    public Respuesta(long id){super(id);}

    public String getRespuesta() {return respuesta;}
    public void setRespuesta(String respuesta) {this.respuesta = respuesta;}
    public Usuario getFk_usuario() {return fk_usuario;}
    public void setFk_usuario(Usuario fk_usuario) {this.fk_usuario = fk_usuario;}

    public Opcion getFk_opcion() {
        return fk_opcion;
    }

    public void setFk_opcion(Opcion fk_opcion) {
        this.fk_opcion = fk_opcion;
    }

    public EncuestaEstudio getEncuesta_estudio() {
        return encuesta_estudio;
    }

    public void setEncuesta_estudio(EncuestaEstudio encuesta_estudio) {
        this.encuesta_estudio = encuesta_estudio;
    }
}