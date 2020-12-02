package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "municipio" )
public class Municipio extends EntidadBase{
    public Municipio(long id) {
        super(id);
    }

    public Municipio() {
    }

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn( name = "Estado_idPais")
    private Estado fk_estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getFk_estado() {
        return fk_estado;
    }

    public void setFk_estado(Estado fk_estado) {
        this.fk_estado = fk_estado;
    }
}




