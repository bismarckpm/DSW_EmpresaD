package mercadeoucab.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table( name = "hijo" )
public class Hijo extends EntidadBase{

    @Column(name = "genero")
    private String genero;

    @Column(name = "edad")
    private Date edad;

    @ManyToOne
    @JoinColumn( name = "fk_dato_encuestado")
    private DatoEncuestado fk_dato_encuestado;

    public Hijo(long id) {
        super(id);
    }

    public Hijo() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getEdad() {
        return edad;
    }

    public void setEdad(Date edad) {
        this.edad = edad;
    }

    public DatoEncuestado getFk_dato_encuestado() {
        return fk_dato_encuestado;
    }

    public void setFk_dato_encuestado(DatoEncuestado fk_dato_encuestado) {
        this.fk_dato_encuestado = fk_dato_encuestado;
    }
}
