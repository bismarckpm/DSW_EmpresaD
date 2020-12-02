package mercadeoucab.dtos;

import java.util.List;

public class DtoPais extends  DtoBase{

    private String nombre;

    public DtoPais(long id) throws Exception { super(id); }

    public DtoPais() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
