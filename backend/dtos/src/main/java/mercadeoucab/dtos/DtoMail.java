package mercadeoucab.dtos;

/**
 * Name: DtoMail
 */
public class DtoMail {

    private String mensaje;
    private String asunto;

    /**
     * Name: DtoMail
     * Description: COnstructor vacio
     */
    public DtoMail() {
    }

    /**
     * Name: getMensaje
     * @return mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Name: setMensaje
     * @param mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Name: getAsunto
     * @return
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Name: setAsunto
     * @param asunto
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * Name: emailResetearContrasena
     * @param frontUrl
     */
    public void emailResetearContrasena(String frontUrl){
        this.asunto = "Recuperacion de Contraseña MercadeoUcab Web App";
        this.mensaje = "Para modificar su contrasena por favor ingrese a la siguiente URL \n" +
                frontUrl + "\n Gracias, que tenga un buen dia";
    }
}
