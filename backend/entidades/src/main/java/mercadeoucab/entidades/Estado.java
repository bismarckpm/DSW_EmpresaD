package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "estado" )
public class Estado extends EntidadBase{

    public Estado() {
    }

    public Estado(long id) {
        super(id);
    }

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn( name = "fk_pais")
    private Pais fk_pais;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getFk_pais() {
        return fk_pais;
    }

    public void setFk_pais(Pais fk_pais) {
        this.fk_pais = fk_pais;
    }
}
