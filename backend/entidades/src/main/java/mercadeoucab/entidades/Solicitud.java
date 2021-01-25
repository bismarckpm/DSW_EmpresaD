package mercadeoucab.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="solicitud")
@NamedQueries({
        @NamedQuery(
                name = "solicitudes_de_un_cliente",
                query = "select s from Solicitud s where s.usuario = :usuario"
        ),
        @NamedQuery(
                name = "solicitudes_segun_estado",
                query = "select s from Solicitud s where s.estado = :estado and s.activo = 1"
        )
})
public class Solicitud extends EntidadBase{

    @Column(name = "estado")
    private String estado;

    @Column(name = "marca")
    private String marca;

    @Column(name = "comentarios")
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

   @JoinTable(
           name = "solicitudcaracteristicas",
           joinColumns = @JoinColumn(name = "solicitud_id", nullable = false),
           inverseJoinColumns = @JoinColumn(name="presentacion_id", nullable = false))
   @ManyToMany()
   private List<Presentacion> presentaciones;


    public Solicitud(long id) { super(id); }
    public Solicitud(){}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public void addPresentacion(Presentacion presentacion){
        if(this.presentaciones == null)
            this.presentaciones = new ArrayList<>();
        this.presentaciones.add(presentacion);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(List<Presentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }
}
