package mercadeoucab.dtos;

public class DtoOpcion extends DtoBase {


    private String nombre_opcion;
    private DtoPregunta Dtopregunta;

    public DtoOpcion(){}

    public String getNombre_opcion() {return nombre_opcion;}
    public void setNombre_opcion(String nombre_opcion) {this.nombre_opcion = nombre_opcion;}

    public DtoPregunta get_Dtopregunta() {return Dtopregunta;}
    public void set_Dtopregunta(DtoPregunta DTOP) {this.Dtopregunta = DTOP;}
}
