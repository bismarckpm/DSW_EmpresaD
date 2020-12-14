package mercadeoucab.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
