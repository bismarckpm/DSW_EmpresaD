package mercadeoucab.dtos;

/**
 * Name: DtoDirectorioAUser
 */
public class DtoDirectorioAUser {

    private String correo;
    private String estado;
    private String password;

    /**
     * Name: DtoDirectorioAUser
     * Description: constructor vacio
     */
    public DtoDirectorioAUser(){}

    /**
     * Name: DtoDirectorioAUser
     * Description: constructor completo
     * @param correo
     * @param estado
     * @param password
     */
    public DtoDirectorioAUser(String correo, String estado, String password) {
        this.correo = correo;
        this.estado = estado;
        this.password = password;
    }

    /**
     * Name:getCorreo
     * @return correo
     */
    public String getCorreo() { return correo; }

    /**
     * Name: setCorreo
     * @param correo
     */
    public void setCorreo(String correo) { this.correo = correo; }

    /**
     * Name: getEstado
     * @return estado
     */
    public String getEstado() { return estado; }

    /**
     * Name: setEstado
     * @param estado
     */
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Name: getPassword
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * Name: setPassword
     * @param password
     */
    public void setPassword(String password) { this.password = password; }

}
