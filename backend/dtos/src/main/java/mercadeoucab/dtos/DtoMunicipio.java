package mercadeoucab.dtos;

import java.util.List;

public class DtoMunicipio extends DtoBase{
    private String nombre;
    private DtoEstado fk_estado;

    public DtoMunicipio(long id) throws Exception {
        super(id);
    }

    public DtoMunicipio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtoEstado getFk_estado() {
        return fk_estado;
    }

    public void setFk_estado(DtoEstado fk_estado) {
        this.fk_estado = fk_estado;
    }
}
