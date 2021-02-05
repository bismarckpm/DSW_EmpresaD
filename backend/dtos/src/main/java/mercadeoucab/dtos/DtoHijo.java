package mercadeoucab.dtos;

import java.sql.Date;

/**
 * Name: DtoHijo
 */
public class DtoHijo extends DtoBase{


    private String genero;
    private Date edad;
    private DtoDatoEncuestado fk_dato_encuestado;

    /**
     * Name: DtoHijo
     * Description: constructor con id
     * @param id
     * @throws Exception
     */
    public DtoHijo(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoHijo
     * Description: Constructor vacio
     */
    public DtoHijo() {
    }

    /**
     * Name: DtoHijo
     * Desription: constructor completo
     * @param genero
     * @param edad
     * @param fk_dato_encuestado
     */
    public DtoHijo(String genero, Date edad, DtoDatoEncuestado fk_dato_encuestado) {
        this.genero = genero;
        this.edad = edad;
        this.fk_dato_encuestado = fk_dato_encuestado;
    }

    /**
     * Name: getGenero
     * @return genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Name: setGenero
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Name: getEdad
     * @return edad
     */
    public Date getEdad() {
        return edad;
    }

    /**
     * Name: setEdad
     * @param edad
     */
    public void setEdad(Date edad) {
        this.edad = edad;
    }

    /**
     * Name: DtoDatoEncuestado
     * @return fk_dato_encuestado
     */
    public DtoDatoEncuestado getFk_dato_encuestado() {
        return fk_dato_encuestado;
    }

    /**
     * Name: setFk_dato_encuestado
     * @param fk_dato_encuestado
     */
    public void setFk_dato_encuestado(DtoDatoEncuestado fk_dato_encuestado) {
        this.fk_dato_encuestado = fk_dato_encuestado;
    }
}
