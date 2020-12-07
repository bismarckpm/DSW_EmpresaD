package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Opcion")
public class Opcion extends EntidadBase {

    @Column(name = "nombre_opcion")
    private String nombre_opcion;

    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    private Pregunta fk_pregunta;


    public String getNombre_opcion() {return nombre_opcion;}
    public void setNombre_opcion(String nombre_opcion) {this.nombre_opcion = nombre_opcion;}

    public Pregunta getFk_pregunta() {return fk_pregunta;}
    public void setFk_pregunta(Pregunta P) {this.fk_pregunta = P;}
}
