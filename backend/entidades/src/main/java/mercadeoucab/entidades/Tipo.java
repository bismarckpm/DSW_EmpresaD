package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="tipo")
public class Tipo extends EntidadBase{

    @Column( name = "nombre")
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "sub_categoria_id")
    private SubCategoria subCategoria;

    @OneToMany( mappedBy = "tipo_id", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Presentacion> presentacionList;

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

    public List<Presentacion> getPresentacionList() { return presentacionList; }

    public void setPresentacionList(List<Presentacion> presentacionList) { this.presentacionList = presentacionList; }

    public void addPresentacion(Presentacion presentacion){
        if (this.presentacionList == null)
            this.presentacionList = new ArrayList<>();
        this.presentacionList.add(presentacion);
    }
}
