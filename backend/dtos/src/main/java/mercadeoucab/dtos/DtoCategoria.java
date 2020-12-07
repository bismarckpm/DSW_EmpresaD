package mercadeoucab.dtos;

public class DtoCategoria  extends DtoBase{
    private String nombre;

    public DtoCategoria(long id) throws Exception {
        super(id);
    }

    public DtoCategoria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
