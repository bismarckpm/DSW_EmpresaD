package mercadeoucab.dtos;

/**
 * Name: DtoTelefono
 */
public class DtoTelefono extends DtoBase{

    private String telefono;
    private DtoDatoEncuestado datoEncuestado;

    /**
     * Name: DtoTelefono
     * Description: Constructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoTelefono(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoTelefono
     * Description: constructor vacio
     */
    public DtoTelefono() {
    }

    /**
     * Name: getTelefono
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Name: setTelefono
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Name: getDatoEncuestado
     * @return datoEncuestado
     */
    public DtoDatoEncuestado getDatoEncuestado() {
        return datoEncuestado;
    }

    /**
     * Name: setDatoEncuestado
     * @param datoEncuestado
     */
    public void setDatoEncuestado(DtoDatoEncuestado datoEncuestado) {
        this.datoEncuestado = datoEncuestado;
    }
}
