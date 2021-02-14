package mercadeoucab.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * La clase Mail se usa para manejar
 * todo lo relacionado a enviar correos electronicos
 *
 * @author  Oscar Marquez
 * @version 1.0
 * @since   2020-12-18
 */
public class Mail {

    private final Properties properties = new Properties();
    private String email;
    private String clave;
    private Session session;

    private void init() {

        this.properties.put("mail.smtp.host", "smtp.gmail.com");
        this.properties.put("mail.smtp.starttls.enable", "true");
        this.properties.put("mail.smtp.port",587);
        this.properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        this.properties.put("mail.smtp.auth", "true");
        this.email = "dswempresad@gmail.com";
        this.clave = "ponganos20";
        this.session = Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( email, clave);
            }
        });
    }

    /**
     * Este metodo es usado para preparar todos los datos
     * necesarios para enviar un mensaje
     * @param correoReceptor Receptor del mensaje
     * @param mensajeParaEnviar  Contenido del mensaje
     * @param asunto Asunto del mensaje
     * @return Message Retorna el mensaje preparado para enviar
     */
    public Message prepararMensaje(
            String correoReceptor,
            String mensajeParaEnviar,
            String asunto
    ){
        Message mensaje = new MimeMessage(this.session);
        try{
            mensaje.setFrom( new InternetAddress( this.email));
            mensaje.setRecipient( Message.RecipientType.TO, new InternetAddress( correoReceptor));
            mensaje.setSubject(asunto);
            mensaje.setText(mensajeParaEnviar);
        }catch (Exception e){
            String problema = e.getMessage();
        }
        return mensaje;
    }

    /**
     * Este metodo es usado para enviar un correo electronico
     *
     * @param correoReceptor Correo receptor del mensaje
     * @param mensaje  Contenido del mensaje
     * @param asunto Asunto del mensaje
     * @return Boolean Retorna verdadero si se envio el mensaje o
     *          falso si ocurrio algun error al enviarse
     */
    public Boolean enviarCorreo(
            String correoReceptor,
            String mensaje,
            String asunto
    ) throws Exception {
        init();
        Boolean enviado;
        Message correoParaEnviar = prepararMensaje(
                correoReceptor,
                mensaje,
                asunto
        );
        try{
            Transport.send( correoParaEnviar);
            enviado = true;
        }catch (Exception e){
            String problema = e.getMessage();
            enviado = false;
        }
        return enviado;
    }
}
