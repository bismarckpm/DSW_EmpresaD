package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "muestra_poblacion" )
public class Muestra_poblacional extends EntidadBase{

    @ManyToOne
    @JoinColumn( name = "fk_lugar")
    private Parroquia fk_lugar_muestra;

    public Parroquia getFk_lugar() {
        return fk_lugar_muestra;
    }

    public void setFk_lugar(Parroquia fk_lugar) {
        this.fk_lugar_muestra = fk_lugar;
    }
}
