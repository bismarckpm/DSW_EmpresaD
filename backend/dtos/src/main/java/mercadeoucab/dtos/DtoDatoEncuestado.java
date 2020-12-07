package mercadeoucab.dtos;

public class DtoDatoEncuestado extends DtoBase {

    private String nombre;

    public DtoDatoEncuestado(){}
    public DtoDatoEncuestado(long id)throws Exception{super(id);}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }


}
