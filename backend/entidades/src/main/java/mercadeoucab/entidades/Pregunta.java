package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Pregunta")
public class Pregunta extends EntidadBase{

    @Column(name = "nombre_pregunta")
    private String nombre_pregunta;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "rango")
    private String rango;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario fk_usuario;

    @OneToMany(mappedBy = "fk_pregunta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<opcion> Opcion;

    @OneToMany(mappedBy = "fk_pregunta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<esqueletoEncuesta_Estudio> encuesta_estudios;

    public String getNombre_pregunta() {return nombre_pregunta;}
    public void setNombre_pregunta(String nombre_pregunta) {this.nombre_pregunta = nombre_pregunta;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getRango() {return rango;}
    public void setRango(String rango) {this.rango = rango;}

    public Usuario getFk_usuario() { return fk_usuario; }
    public void setFk_usuario(Usuario fk_usuario) {this.fk_usuario = fk_usuario;}

    public List<opcion> getOpcion() {return Opcion;}
    public void setOpcion(List<opcion> opcion) {Opcion = opcion;}

    public List<esqueletoEncuesta_Estudio> getEncuesta_estudios() {return encuesta_estudios;}
    public void setEncuesta_estudios(List<esqueletoEncuesta_Estudio> ES) {encuesta_estudios = ES;}
}
