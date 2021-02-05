package mercadeoucab.dtos;


/**
 * Name: DtoParroquia
 */
public class DtoParroquia extends DtoBase{

    private String nombre;
    private int valor_socio_economico;
    private DtoMunicipio fk_municipio;

    /**
     * Name: DtoParroquia
     * Description: Constructor con id
     * @param id
     * @throws Exception
     */
    public DtoParroquia(long id) throws Exception { super(id); }

    /**
     * Name: DtoParroquia
     * Descrption: constructor vacio
     */
    public DtoParroquia() {
    }

    /**
     * Name: DtoParroquia
     * Description: Constructor completo
     * @param nombre
     * @param valor_socio_economico
     * @param fk_municipio
     */
    public DtoParroquia(String nombre, int valor_socio_economico, DtoMunicipio fk_municipio) {
        this.nombre = nombre;
        this.valor_socio_economico = valor_socio_economico;
        this.fk_municipio = fk_municipio;
    }

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Name: setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Name: getValor_socio_economico
     * @return valor_socio_economico
     */
    public int getValor_socio_economico() {
        return valor_socio_economico;
    }

    /**
     * Name: setValor_socio_economico
     * @param valor_socio_economico
     */
    public void setValor_socio_economico(int valor_socio_economico) {
        this.valor_socio_economico = valor_socio_economico;
    }

    /**
     * Name: getFk_municipio
     * @return fk_municipio
     */
    public DtoMunicipio getFk_municipio() {
        return fk_municipio;
    }

    /**
     * Name: setFk_municipio
     * @param fk_municipio
     */
    public void setFk_municipio(DtoMunicipio fk_municipio) {
        this.fk_municipio = fk_municipio;
    }
}
