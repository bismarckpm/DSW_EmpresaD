package mercadeoucab.dtos;

import java.util.List;

public class DtoMarca extends DtoBase{

    private String nombre;

    public DtoMarca(long id)throws Exception{ super(id); }
    public DtoMarca(){}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
}
