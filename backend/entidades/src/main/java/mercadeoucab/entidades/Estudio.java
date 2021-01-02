package mercadeoucab.entidades;

import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="estudio")
@NamedQueries({
        @NamedQuery(
                name = "estudios_de_un_analista",
                query = "select e from Estudio e where e.fk_usuario = :fk_usuario"
        ),
        @NamedQuery(
                name = "estudios_aplican_encuestado",
                query = "select e from Estudio e " +
                        "where e.fk_muestra_poblacion.cantidadHijos = :cantidadHijos " +
                        "  and e.fk_muestra_poblacion.genero = :genero " +
                        "  and e.fk_muestra_poblacion.nivelAcademico = :nivelAcademico " +
                        "  and e.fk_muestra_poblacion.nivelEconomico = :nivelEconomico " +
                        "  and e.fk_muestra_poblacion.rangoEdadInicio <= :edad " +
                        "  and e.fk_muestra_poblacion.rangoEdadFin >= :edad " +
                        "  and e.fk_muestra_poblacion.fk_lugar = :lugar " +
                        "  and e.fk_muestra_poblacion.fk_ocupacion = :ocupacion"
        ),
        @NamedQuery(
                name = "preguntas_similares",
                query = "select e from Estudio e " +
                        "join e.solicitud s " +
                        "where s.presentaciones in :presentaciones " +
                        "and s.tipos in :tipos " +
                        "and s.subCategorias in :subcategorias"

        ),
        @NamedQuery(
                name = "poblaciones_similares",
                query = "select e.fk_muestra_poblacion from Estudio e " +
                        "join e.solicitud s " +
                        "where s.presentaciones in :presentaciones " +
                        "and s.tipos in :tipos " +
                        "and s.subCategorias in :subcategorias"
        ),
        @NamedQuery(
                name = "personas_aplican",
                query = "select e.usuario from DatoEncuestado e " +
                        "where  e.nive_economico = :nivelEcon " +
                        "  and  e.genero = :genero " +
                        //"  and  e.hijos.size = :cantidadHijos " +
                        "  and  e.nivelAcademico = :nivelAcademico " +
                        "  and  e.fk_lugar = :lugar " +
                        "  and  e.ocupacion = :ocupacion "
                        //"  and  e.edad between :edadInicial and :edadFinal"
        )
})
public class Estudio extends EntidadBase{

    @Column(name = "estado")
    private String estado;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "encuestas_esperadas")
    private int encuestasEsperadas;

    @ManyToOne
    @JoinColumn(name = "fk_solicitud")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario fk_usuario;

    @ManyToOne
    @JoinColumn(name = "fk_muestra_poblacion")
    private MuestraPoblacion fk_muestra_poblacion;

    @JoinTable(
            name = "encuesta_estudio",
            joinColumns = @JoinColumn(name = "fk_estudio", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_pregunta", nullable = false))
    @ManyToMany()
    private List<Pregunta> preguntas;

    @OneToMany( mappedBy = "fk_estudio", fetch = FetchType.LAZY )
    private List<EncuestaEstudio> encuestaEstudio = new ArrayList<>();

    public Estudio(long id) {
        super(id);
    }

    public Estudio() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEncuestasEsperadas() {
        return encuestasEsperadas;
    }

    public void setEncuestasEsperadas(int encuestasEsperadas) {
        this.encuestasEsperadas = encuestasEsperadas;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Usuario getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(Usuario fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public MuestraPoblacion getFk_muestra_poblacion() {
        return fk_muestra_poblacion;
    }

    public void setFk_muestra_poblacion(MuestraPoblacion fk_muestra_poblacion) {
        this.fk_muestra_poblacion = fk_muestra_poblacion;
    }

    public void addpregunta(Pregunta pregunta){
        if(this.preguntas == null)
            this.preguntas = new ArrayList<>();
        this.preguntas.add(pregunta);
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public List<EncuestaEstudio> getEncuestaEstudio() {
        return encuestaEstudio;
    }

    public void setEncuestaEstudio(List<EncuestaEstudio> encuestaEstudio) {
        this.encuestaEstudio = encuestaEstudio;
    }
}
