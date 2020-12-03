package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "dato_encuestado" )
public class DatoEncuestado extends EntidadBase{

    @ManyToOne
    @JoinColumn( name = "fk_lugar")
    private Parroquia fk_lugar_encuestado;

    public Parroquia getFk_lugar() {
        return fk_lugar_encuestado;
    }

    public void setFk_lugar(Parroquia fk_lugar) {
        this.fk_lugar_encuestado = fk_lugar;
    }
}
