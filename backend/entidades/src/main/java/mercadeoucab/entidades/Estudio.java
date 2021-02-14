package mercadeoucab.entidades;

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
                        "where e.solicitud.fk_muestra_poblacion.genero = :genero " +
                        "  and (e.solicitud.fk_muestra_poblacion.nivelAcademico = :nivelAcademico or e.solicitud.fk_muestra_poblacion.nivelEconomico = :nivelEconomico or e.solicitud.fk_muestra_poblacion.fk_ocupacion = :ocupacion) " +
                        "  and :edad between e.solicitud.fk_muestra_poblacion.rangoEdadInicio and  e.solicitud.fk_muestra_poblacion.rangoEdadFin" +
                        "  and e.solicitud.fk_muestra_poblacion.fk_lugar = :lugar " +
                        "  and e.estado = 'En ejecucion'" +
                        "  and e.activo = 1"
        ),
        @NamedQuery(
                name = "preguntas_similares",
                query = "select e from Estudio e " +
                        "join e.solicitud s " +
                        "where s.presentaciones in :presentaciones "

        ),
        @NamedQuery(
                name = "poblaciones_similares",
                query = "select e.solicitud.fk_muestra_poblacion from Estudio e " +
                        "join e.solicitud s " +
                        "where s.presentaciones in :presentaciones "
        ),
        @NamedQuery(
                name = "personas_aplican",
                query = "select e.usuario from DatoEncuestado e " +
                        "where  e.genero = :genero " + //estricto
                        "  and  (e.nivelAcademico = :nivelAcademico or e.ocupacion = :ocupacion or e.nive_economico = :nivelEcon) " + //Puede no ser estricto
                        "  and  e.fk_lugar = :lugar " + //estricto
                        "  and  e.edad between :edadInicial and :edadFinal" //estricto
        ),
        @NamedQuery(
                name = "estudios_cliente",
                query = "select e from Estudio e " +
                        "where e.solicitud.usuario = :usuario"
        )
})
public class Estudio extends EntidadBase{

    @Column(name = "estado")
    private String estado;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "encuestas_esperadas")
    private int encuestasEsperadas;

    @Column(name = "comentarios")
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "fk_solicitud")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario fk_usuario;

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

    public String getComentarios() { return comentarios; }

    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

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

    public void addEncuestaEstudio(EncuestaEstudio encuestaEstudio){
        if(this.encuestaEstudio == null)
            this.encuestaEstudio = new ArrayList<>();
        this.encuestaEstudio.add(encuestaEstudio);
    }

    public List<EncuestaEstudio> getEncuestaEstudio() {
        return encuestaEstudio;
    }

    public void setEncuestaEstudio(List<EncuestaEstudio> encuestaEstudio) {
        this.encuestaEstudio = encuestaEstudio;
    }
}
