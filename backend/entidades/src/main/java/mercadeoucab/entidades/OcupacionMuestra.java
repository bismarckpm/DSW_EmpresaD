package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table(name = "ocupacion_muestra")
public class OcupacionMuestra extends EntidadBase{

    @ManyToOne
    @JoinColumn(name = "fk_ocupacion")
    private Ocupacion fk_ocupacion;

    @ManyToOne
    @JoinColumn(name = "fk_muestra_poblacion")
    private MuestraPoblacion fk_muestra_poblacion;

    public OcupacionMuestra(){}
    public OcupacionMuestra(long id){super(id);}

    public MuestraPoblacion getFk_muestra_poblacion() { return fk_muestra_poblacion; }
    public void setFk_muestra_poblacion(MuestraPoblacion fk_muestra_poblacion) { this.fk_muestra_poblacion = fk_muestra_poblacion; }

    public Ocupacion getFk_ocupacion() { return fk_ocupacion; }
    public void setFk_ocupacion(Ocupacion fk_ocupacion) { this.fk_ocupacion = fk_ocupacion; }
}
