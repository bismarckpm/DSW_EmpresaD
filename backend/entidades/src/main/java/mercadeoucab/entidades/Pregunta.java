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

    @JoinTable(
            name = "encuesta_estudio",
            joinColumns = @JoinColumn(name = "fk_pregunta", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_estudio", nullable = false))
    @ManyToMany()
    private List<Estudio> estudios;

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

    public List<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<Estudio> estudios) {
        this.estudios = estudios;
    }

    public void addEstudio(Estudio estudio){
        if(this.estudios == null)
            this.estudios = new ArrayList<>();
        this.estudios.add(estudio);
    }
}
