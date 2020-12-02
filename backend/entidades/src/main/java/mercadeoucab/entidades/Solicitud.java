package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table(name ="solicitude")
public class Solicitud extends EntidadBase{

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_marca")
    private Marca marca;

    public Solicitud(long id) { super(id); }
    public Solicitud(){}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public Marca getMarca() {return marca;}
    public void setMarca(Marca marca) {this.marca = marca;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
}
