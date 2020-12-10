package mercadeoucab.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ocupacion_Encuestado")
public class OcupacionEncuestado extends EntidadBase {

    @ManyToOne
    @JoinColumn(name = "fk_ocupacion")
    private Ocupacion fk_ocupacion;

    @ManyToOne
    @JoinColumn(name = "fk_dato_encuestado")
    private DatoEncuestado fk_dato_encuestado;

    public OcupacionEncuestado(){}
    public OcupacionEncuestado(long id){super(id);}

    public Ocupacion getFk_ocupacion() { return fk_ocupacion; }
    public void setFk_ocupacion(Ocupacion fk_ocupacion) { this.fk_ocupacion = fk_ocupacion; }

    public DatoEncuestado getFk_dato_encuestado() {
        return fk_dato_encuestado;
    }

    public void setFk_dato_encuestado(DatoEncuestado fk_dato_encuestado) {
        this.fk_dato_encuestado = fk_dato_encuestado;
    }
}
