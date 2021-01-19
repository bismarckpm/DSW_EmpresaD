package mercadeoucab.dtos;


/**
 * Name: DtoUsuario
 */
public class DtoUsuario extends DtoBase{

    private String nombre;
    private String apellido;
    private String rol;
    private String estado;
    private String correo;
    private String password;

    /**
     * Name: DtoUsuario
     * Description: constructor vacio
     */
    public DtoUsuario(){}

    /**
     * Name: getPassword
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Name: setPassword
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Name: DtoUsuario
     * Description: COnstructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoUsuario(long id) throws Exception {super(id);}

    /**
     * Name: DtoUsuario
     * Description: constructor completo
     * @param nombre
     * @param apellido
     * @param rol
     * @param estado
     * @param correo
     */
    public DtoUsuario(String nombre, String apellido, String rol, String estado, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.estado = estado;
        this.correo = correo;
    }

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() {return nombre;}

    /**
     * Name: setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {this.nombre = nombre;}

    /**
     * Name: getApellido
     * @return apellido
     */
    public String getApellido() {return this.apellido;}

    /**
     * Name: setApellido
     * @param apellido
     */
    public void setApellido(String apellido) {this.apellido = apellido;}

    /**
     * Name: getRol
     * @return rol
     */
    public String getRol() { return rol;}

    /**
     * Name: setRol
     * @param rol
     */
    public void setRol(String rol) { this.rol = rol; }

    /**
     * Name: getEstado
     * @return estado
     */
    public String getEstado() {return estado;}

    /**
     * Name: setEstado
     * @param estado
     */
    public void setEstado(String estado) {this.estado = estado;}

    /**
     * Name: getCorreo
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Name: setCorreo
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
