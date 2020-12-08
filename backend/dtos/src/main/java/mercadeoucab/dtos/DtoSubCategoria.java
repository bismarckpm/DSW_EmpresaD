package mercadeoucab.dtos;

public class DtoSubCategoria extends DtoBase{

    private String nombre;
    private DtoCategoria categoria;

    public DtoSubCategoria(long id) throws Exception {
        super(id);
    }

    public DtoSubCategoria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(DtoCategoria categoria) {
        this.categoria = categoria;
    }
}
