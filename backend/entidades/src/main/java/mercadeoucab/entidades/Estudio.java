package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table(name ="estudio")
public class Estudio extends EntidadBase{

    @Column(name = "estado")
    private String estado;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "encuestas_esperadas")
    private int escuestasEsperadas;

    @ManyToOne
    @JoinColumn(name = "fk_solicitud")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario fk_usuario;

    @ManyToOne
    @JoinColumn(name = "fk_muestra_poblacion")
    private MuestraPoblacion fk_muestra_poblacion;

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

    public int getEscuestasEsperadas() {
        return escuestasEsperadas;
    }

    public void setEscuestasEsperadas(int escuestasEsperadas) {
        this.escuestasEsperadas = escuestasEsperadas;
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
}
