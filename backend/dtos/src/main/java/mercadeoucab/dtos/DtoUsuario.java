package mercadeoucab.dtos;

import javax.persistence.Column;
import java.util.List;

public class DtoUsuario extends DtoBase{

    private String nombre;
    private String apellido;
    private String rol;
    private String estado;
    private String correo;

    public DtoUsuario(){}

    public DtoUsuario(long id) throws Exception {super(id);}

    public DtoUsuario(String nombre, String apellido, String rol, String estado, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.estado = estado;
        this.correo = correo;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return this.apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getRol() { return rol;}
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
