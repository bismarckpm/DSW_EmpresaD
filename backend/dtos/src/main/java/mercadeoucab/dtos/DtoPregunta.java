package mercadeoucab.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: DtoPregunta
 */
public class DtoPregunta extends DtoBase{


    private String nombre_pregunta;
    private String tipo;
    private String rango;
    private DtoUsuario usuarioDto;
    private List<DtoOpcion> opciones;

    /**
     * Name: DtoPregunta
     * Description: Constructor vacio
     */
    public DtoPregunta(){}

    /**
     * Name: DtoPregunta
     * Description: COnstructor con id
     * @param id
     * @throws Exception
     */
    public DtoPregunta(long id) throws Exception {
        super(id);
    }

    /**
     * Name: getNombre_pregunta
     * @return nombre_pregunta
     */
    public String getNombre_pregunta() {return nombre_pregunta;}

    /**
     * Name: setNombre_pregunta
     * @param nombre_pregunta
     */
    public void setNombre_pregunta(String nombre_pregunta) {this.nombre_pregunta = nombre_pregunta;}

    /**
     * Name: getTipo
     * @return tipo
     */
    public String getTipo() {return tipo;}

    /**
     * Name: setTipo
     * @param tipo
     */
    public void setTipo(String tipo) {this.tipo = tipo;}

    /**
     * Name: getRango
     * @return rango
     */
    public String getRango() {return rango;}

    /**
     * Name: setRango
     * @param rango
     */
    public void setRango(String rango) {this.rango = rango;}

    /**
     * Name: getOpciones
     * @return opciones
     */
    public List<DtoOpcion> getOpciones() {
        return opciones;
    }

    /**
     * Name: setOpciones
     * @param opciones
     */
    public void setOpciones(List<DtoOpcion> opciones) {
        this.opciones = opciones;
    }

    /**
     * Name: DtoUsuario
     * @return
     */
    public DtoUsuario getUsuarioDto() {
        return usuarioDto;
    }

    /**
     * Name: setUsuarioDto
     * @param usuarioDto
     */
    public void setUsuarioDto(DtoUsuario usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    /**
     * Name: addOpcion
     * @param opcion
     */
    public void addOpcion( DtoOpcion opcion){
        if(this.opciones == null)
            this.opciones = new ArrayList<>();
        opcion.set_Dtopregunta( this);
        this.opciones.add( opcion);
    }
}
