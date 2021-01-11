package mercadeoucab.dtos;

import javax.persistence.*;
import java.util.List;

public class DtoOpcion extends DtoBase {


    private String nombre_opcion;
    private DtoPregunta Dtopregunta;

    public DtoOpcion(){}
    public DtoOpcion(long id)throws Exception{super(id);}

    public String getNombre_opcion() {return nombre_opcion;}
    public void setNombre_opcion(String nombre_opcion) {this.nombre_opcion = nombre_opcion;}

    public DtoPregunta get_Dtopregunta() {return Dtopregunta;}
    public void set_Dtopregunta(DtoPregunta DTOP) {this.Dtopregunta = DTOP;}
}
