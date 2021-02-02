import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioAdministrador;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioAdministradorTest {

    @Test
    public void preguntasAdmnistradorTest() throws Exception{
        ServicioAdministrador servicio = new ServicioAdministrador();
        DtoUsuario dtoUsuario = new DtoUsuario(54);
        Response resultado = servicio.preguntasAdministrador("",dtoUsuario.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }
}
