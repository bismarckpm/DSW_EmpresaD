package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "encuesta_estudio")
public class EncuestaEstudio extends EntidadBase{

    public EncuestaEstudio(long id) {
        super(id);
    }

    public EncuestaEstudio() {
    }
}
