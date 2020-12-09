package mercadeoucab.dtos;

import java.sql.Date;
import java.util.List;

public class DtoPais extends  DtoBase{

    private String nombre;

    public DtoPais(long id) throws Exception { super(id); }

    public DtoPais() {
    }

    public DtoPais(long _id, int activo, Date creado_el, Date modificado_el, String nombre) {
        super(_id, activo, creado_el, modificado_el);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
