package mercadeoucab.dtos;

public class DtoSubCategoria extends DtoBase{

    private String nombre;

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

}