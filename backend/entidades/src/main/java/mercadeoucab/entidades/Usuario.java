package mercadeoucab.entidades;


import javax.persistence.*;
import java.util.List;

@Entity
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

    public Usuario(){  }


    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return this.apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getRol() { return rol;}
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public List<Pregunta> getPregunta() {return pregunta;}
    public void setPregunta(List<Pregunta> pregunta) {this.pregunta = pregunta;}
}
