package mercadeoucab.dtos;

import javax.persistence.Column;

public class DtoMuestraPoblacion extends DtoBase{

    private String nombre;

    public DtoMuestraPoblacion(){}
    public DtoMuestraPoblacion(long id)throws Exception{super(id);}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
