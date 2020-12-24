import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioCliente;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioClienteTest {

    @Test
    public void solicitudesClienteTest() throws Exception{
        ServicioCliente servicio = new ServicioCliente();
        DtoUsuario dtoUsuario = new DtoUsuario(1);
        Response resultado = servicio.solicitudesCliente(dtoUsuario.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }
}
