package mercadeoucab.dtos;

import java.util.List;

public class DtoEstado extends DtoBase{
    private String nombre;
    private DtoPais fk_pais;

    public DtoEstado(long id) throws Exception { super(id); }

    public DtoEstado() {
    }

    public DtoEstado(String nombre, DtoPais fk_pais) {
        this.nombre = nombre;
        this.fk_pais = fk_pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtoPais getFk_pais() {
        return fk_pais;
    }

    public void setFk_pais(DtoPais fk_pais) {
        this.fk_pais = fk_pais;
    }

}
