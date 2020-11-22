package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "Lugar" )
public class Lugar extends EntidadBase{

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "valor_socio_economico")
    private long valor_socio_economico;

    @ManyToOne
    @JoinColumn( name = "fk_lugar" )
    private Lugar fk_lugar;

    @OneToMany( mappedBy = "fk_lugar", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Lugar> fk_lugarList;

    @OneToMany( mappedBy = "dato_lugar", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Dato_Encuestado> dato_encuestadoList;

    @OneToMany( mappedBy = "id_lugar", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Muestra_poblacional> muestraList;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getValor_socio_economico() {
        return valor_socio_economico;
    }

    public void setValor_socio_economico(long valor_socio_economico) {
        this.valor_socio_economico = valor_socio_economico;
    }

    public Lugar getFk_lugar() {
        return fk_lugar;
    }

    public void setFk_lugar(Lugar fk_lugar) {
        this.fk_lugar = fk_lugar;
    }

    public List<Lugar> getFk_lugarList() {
        return fk_lugarList;
    }

    public void setFk_lugarList(List<Lugar> fk_lugarList) {
        this.fk_lugarList = fk_lugarList;
    }

    public List<Dato_Encuestado> getDato_encuestadoList() {
        return dato_encuestadoList;
    }

    public void setDato_encuestadoList(List<Dato_Encuestado> dato_encuestadoList) {
        this.dato_encuestadoList = dato_encuestadoList;
    }

    public List<Muestra_poblacional> getMuestraList() {
        return muestraList;
    }

    public void setMuestraList(List<Muestra_poblacional> muestraList) {
        this.muestraList = muestraList;
    }
}
