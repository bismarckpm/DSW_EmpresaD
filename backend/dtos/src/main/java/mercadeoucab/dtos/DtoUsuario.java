package mercadeoucab.dtos;

import javax.persistence.Column;

public class DtoUsuario extends DtoBase{

    private String nombre;
    private String apellido;
    private String rol;
    private String estado;

    public DtoUsuario(){}

    public DtoUsuario(long id) throws Exception{super(id);}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return this.apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getRol() { return rol;}
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}






}
