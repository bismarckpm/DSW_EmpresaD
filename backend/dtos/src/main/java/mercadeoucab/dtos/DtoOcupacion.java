package mercadeoucab.dtos;

import java.util.List;

public class DtoOcupacion extends DtoBase {


    private String nombre;

    public DtoOcupacion(){}
    public DtoOcupacion(long id)throws Exception{super(id);}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}