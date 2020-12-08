package mercadeoucab.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table( name = "hijo" )
public class Hijo extends EntidadBase{

    @Column(name = "genero")
    private String genero;

    @Column(name = "edad")
    private Date edad;

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
}
