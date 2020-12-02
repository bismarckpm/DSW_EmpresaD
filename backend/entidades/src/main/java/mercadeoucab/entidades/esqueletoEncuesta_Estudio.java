package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "encuesta_estudio")
public class esqueletoEncuesta_Estudio extends EntidadBase{

    @ManyToOne
    @JoinColumn(name="fk_pregunta")
    private Pregunta fk_pregunta;

    public Pregunta getFk_pregunta() {return fk_pregunta;}
    public void setFk_pregunta(Pregunta fk_pregunta) {this.fk_pregunta = fk_pregunta;}
}
