import mercadeoucab.mail.Mail;
import org.junit.Assert;
import org.junit.Test;


public class MailTest {

    @Test
    public void enviarCorreoTest() throws Exception{
        String correoReceptor = "dswempresad@gmail.com";
        String asunto = "prueba";
        String mensaje = "Esto es una prueba";
        Mail correo = new Mail();
        Boolean resultado = correo.enviarCorreo(
                correoReceptor,
                mensaje,
                asunto
        );
        Assert.assertTrue(resultado);
    }
}
