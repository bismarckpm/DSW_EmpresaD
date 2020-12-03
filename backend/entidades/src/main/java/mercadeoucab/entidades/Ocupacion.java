package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ocupacion")
public class Ocupacion extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "fk_ocupacion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OcupacionMuestra> ocupacionMuestras;
    @OneToMany(mappedBy = "fk_ocupacion", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OcupacionEncuestado> ocupacionEncuestados;

    public Ocupacion(){}
    public Ocupacion(long id){super(id);}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<OcupacionMuestra> getOcupacionMuestras() { return ocupacionMuestras; }
    public void setOcupacionMuestras(List<OcupacionMuestra> ocupacionMuestras) { this.ocupacionMuestras = ocupacionMuestras; }

    public List<OcupacionEncuestado> getOcupacionEncuestados() { return ocupacionEncuestados; }
    public void setOcupacionEncuestados(List<OcupacionEncuestado> ocupacionEncuestados) { this.ocupacionEncuestados = ocupacionEncuestados; }
}
