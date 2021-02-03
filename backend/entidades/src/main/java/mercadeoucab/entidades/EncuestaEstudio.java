package mercadeoucab.entidades;

import mercadeoucab.dtos.DtoEncuestaEstudio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "encuesta_estudio")
public class EncuestaEstudio implements Serializable {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @ManyToOne
    @JoinColumn( name = "fk_pregunta")
    private Pregunta fk_pregunta;

    @ManyToOne
    @JoinColumn( name = "fk_estudio")
    private Estudio fk_estudio;

    @OneToMany( mappedBy = "encuesta_estudio", fetch = FetchType.LAZY )
    private List<Respuesta> respuestas = new ArrayList<>();


    public EncuestaEstudio(long _id) {
        this._id = _id;
    }

    public EncuestaEstudio(){}

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Pregunta getFk_pregunta() {
        return fk_pregunta;
    }

    public void setFk_pregunta(Pregunta fk_pregunta) {
        this.fk_pregunta = fk_pregunta;
    }

    public Estudio getFk_estudio() {
        return fk_estudio;
    }

    public void setFk_estudio(Estudio fk_estudio) {
        this.fk_estudio = fk_estudio;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public void addRespuesta( Respuesta respuesta){
        if ( this.respuestas == null)
            this.respuestas = new ArrayList<>();
        this.respuestas.add( respuesta );
    }
}
