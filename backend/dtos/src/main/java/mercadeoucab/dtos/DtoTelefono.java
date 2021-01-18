package mercadeoucab.dtos;

public class DtoTelefono extends DtoBase{

    private String telefono;
    private DtoDatoEncuestado datoEncuestado;

    public DtoTelefono(long id) throws Exception {
        super(id);
    }

    public DtoTelefono() {
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public DtoDatoEncuestado getDatoEncuestado() {
        return datoEncuestado;
    }

    public void setDatoEncuestado(DtoDatoEncuestado datoEncuestado) {
        this.datoEncuestado = datoEncuestado;
    }
}
