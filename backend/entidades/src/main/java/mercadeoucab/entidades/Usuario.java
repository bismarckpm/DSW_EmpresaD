package mercadeoucab.entidades;


import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "usuario" )
public class Usuario extends EntidadBase {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "fk_usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Pregunta> pregunta;

    public Usuario(long id) {
        super(id);
    }

    public Usuario() {
    }
}