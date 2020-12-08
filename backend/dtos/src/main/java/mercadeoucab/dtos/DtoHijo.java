package mercadeoucab.dtos;

import java.sql.Date;

public class DtoHijo extends DtoBase{


    private String genero;
    private Date edad;
    private DtoDatoEncuestado fk_dato_encuestado;

    public DtoHijo(long id) throws Exception {
        super(id);
    }

    public DtoHijo() {
    }

    public DtoHijo(String genero, Date edad, DtoDatoEncuestado fk_dato_encuestado) {
        this.genero = genero;
        this.edad = edad;
        this.fk_dato_encuestado = fk_dato_encuestado;
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

    public DtoDatoEncuestado getFk_dato_encuestado() {
        return fk_dato_encuestado;
    }

    public void setFk_dato_encuestado(DtoDatoEncuestado fk_dato_encuestado) {
        this.fk_dato_encuestado = fk_dato_encuestado;
    }
}
