package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table(name ="tipo")
public class Tipo extends EntidadBase{

    @Column( name = "nombre")
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "sub_categoria_id")
    private SubCategoria subCategoria;

    public Tipo(long id) {
        super(id);
    }

    public Tipo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }
}
