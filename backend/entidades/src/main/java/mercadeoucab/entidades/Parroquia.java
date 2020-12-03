package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "parroquia" )
public class Parroquia extends EntidadBase {

    public Parroquia(long id) {
        super(id);
    }

    public Parroquia() {
    }

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valor_socio_economico")
    private int valor_socio_economico;

    @ManyToOne
    @JoinColumn( name = "Municipio_idPais")
    private Municipio fk_municipio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor_socio_economico() {
        return valor_socio_economico;
    }

    public void setValor_socio_economico(int valor_socio_economico) {
        this.valor_socio_economico = valor_socio_economico;
    }

    public Municipio getFk_municipio() {
        return fk_municipio;
    }

    public void setFk_municipio(Municipio fk_municipio) {
        this.fk_municipio = fk_municipio;
    }
}
