package mercadeoucab.dtos;

public class DtoMail {

    private String mensaje;
    private String asunto;

    public DtoMail() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void emailResetearContrasena(String frontUrl){
        this.asunto = "Recuperacion de Contraseña MercadeoUcab Web App";
        this.mensaje = "Para modificar su contrasena por favor ingrese a la siguiente URL \n" +
                frontUrl + "\n Gracias, que tenga un buen dia";
    }
}
