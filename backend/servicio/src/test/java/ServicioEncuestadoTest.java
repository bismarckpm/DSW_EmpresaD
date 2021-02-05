import mercadeoucab.servicio.ServicioEncuestado;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioEncuestadoTest {

    @Test
    public void estudiosAplicablesTest() throws Exception{
        ServicioEncuestado servicio = new ServicioEncuestado();
        Response resultado = servicio.estudiosAplicables("" ,21);
        Assert.assertEquals(200, resultado.getStatus());
    }
}
