package mercadeoucab.dtos;

import javax.persistence.*;
import java.util.List;

public class DtoPregunta extends DtoBase{


    private String nombre_pregunta;
    private String tipo;
    private String rango;
    private DtoUsuario usuarioDto;
    private List<DtoOpcion> opciones;

    public DtoPregunta(){}

    public DtoPregunta(long id) throws Exception {
        super(id);
    }

    public String getNombre_pregunta() {return nombre_pregunta;}
    public void setNombre_pregunta(String nombre_pregunta) {this.nombre_pregunta = nombre_pregunta;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getRango() {return rango;}
    public void setRango(String rango) {this.rango = rango;}

    public List<DtoOpcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<DtoOpcion> opciones) {
        this.opciones = opciones;
    }

    public DtoUsuario getUsuarioDto() {
        return usuarioDto;
    }
    public void setUsuarioDto(DtoUsuario usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public void addOpcion( DtoOpcion opcion){
        opcion.set_Dtopregunta( this);
        this.opciones.add( opcion);
    }
}
