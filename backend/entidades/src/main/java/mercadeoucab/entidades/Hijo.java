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
    private DatoEncuestado datoEncuestado;

    public Hijo(long id) {
        super(id);
    }

    public DatoEncuestado getDatoEncuestado() {
        return datoEncuestado;
    }

    public void setDatoEncuestado(DatoEncuestado datoEncuestado) {
        this.datoEncuestado = datoEncuestado;
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

}
