package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Pregunta")
public class Pregunta extends EntidadBase{

    @Column(name = "nombre_pregunta")
    private String nombrePregunta;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "rango")
    private String rango;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @OneToMany( mappedBy = "fk_pregunta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Opcion> opciones = new ArrayList<>();

    public Pregunta(long id) {
        super(id);
    }

    public Pregunta() {
    }

    public String getNombrePregunta() {
        return nombrePregunta;
    }

    public void setNombrePregunta(String nombrePregunta) {
        this.nombrePregunta = nombrePregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public void addOpcion(Opcion opcion){
        this.opciones.add( opcion);
        opcion.setFk_pregunta( this);
    }
}
