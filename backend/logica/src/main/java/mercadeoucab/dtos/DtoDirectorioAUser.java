package mercadeoucab.dtos;

public class DtoDirectorioAUser {

    private String correo;
    private String estado;
    private String password;

    public DtoDirectorioAUser(){}

    public DtoDirectorioAUser(String correo, String estado, String password) {
        this.correo = correo;
        this.estado = estado;
        this.password = password;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
