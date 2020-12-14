import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioAnalista;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioAnalistaTest {

    @Test
    public void estudiosAnalistaTest() throws Exception{
        ServicioAnalista servicio = new ServicioAnalista();
        DtoUsuario dtoUsuario = new DtoUsuario(1);
        Response resultado = servicio.estudiosAnalista(dtoUsuario.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }
}
