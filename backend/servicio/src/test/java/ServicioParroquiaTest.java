import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Pais;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.servicio.ServicioEstado;
import mercadeoucab.servicio.ServicioMunicipio;
import mercadeoucab.servicio.ServicioPais;
import javax.ws.rs.core.Response;
import mercadeoucab.servicio.ServicioParroquia;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioParroquiaTest {

    @Test
    public void registroParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(0);
        dtoParroquia.setFk_municipio(new DtoMunicipio(1));
        Response resultado = servicio.registrarParroquia( dtoParroquia );
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void consultarParroquiaTest() throws  Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Response resultado = servicio.consultarParroquia(1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(1000);
        Response resultado = servicio.actualizarParroquia(1, dtoParroquia);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Response resultado = servicio.eliminarParroquia(1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarParroquiasTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Response resultado = servicio.listarParroquias();
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
