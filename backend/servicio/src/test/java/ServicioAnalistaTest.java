import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioAnalista;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioAnalistaTest {

    @Test
    public void estudiosAnalistaTest() throws Exception{
        ServicioAnalista servicio = new ServicioAnalista();
        DtoUsuario dtoUsuario = new DtoUsuario(37);
        Response resultado = servicio.estudiosAnalista("",dtoUsuario.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void listarAnalistasTest() throws Exception{
        ServicioAnalista servicio = new ServicioAnalista();
        Response resultado = servicio.listarAnalistas("");
        Assert.assertEquals(200, resultado.getStatus());
    }
}
