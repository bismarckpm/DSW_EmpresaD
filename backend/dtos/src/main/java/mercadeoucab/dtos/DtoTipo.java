package mercadeoucab.dtos;

public class DtoTipo extends DtoBase{

    private String nombre;

    public DtoTipo(long id) throws Exception {
        super(id);
    }

    public DtoTipo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
