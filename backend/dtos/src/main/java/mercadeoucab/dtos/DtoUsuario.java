package mercadeoucab.dtos;

import javax.persistence.Column;
import java.util.List;

public class DtoUsuario extends DtoBase{

    private String nombre;
    private String apellido;
    private String rol;
    private String estado;
    private List<DtoPregunta> preguntas;
    private List<DtoDatoEncuestado> datoEncuestado;
    private List<DtoSolicitud> solicitudes;

    public DtoUsuario(){}

    public DtoUsuario(long id) throws Exception {super(id);}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return this.apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getRol() { return rol;}
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public List<DtoPregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<DtoPregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public List<DtoDatoEncuestado> getDatoEncuestado() {
        return datoEncuestado;
    }

    public void setDatoEncuestado(List<DtoDatoEncuestado> datoEncuestado) {
        this.datoEncuestado = datoEncuestado;
    }

    public List<DtoSolicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<DtoSolicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
