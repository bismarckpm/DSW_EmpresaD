package mercadeoucab.entidades;

import javax.persistence.*;
import java.io.Serializable;

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
}
