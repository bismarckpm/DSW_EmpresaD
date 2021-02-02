import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.servicio.ServicioParroquia;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioParroquiaTest {

    @Test
    public void registroParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("caucaguita");
        dtoParroquia.setValor_socio_economico(66666660);
        dtoParroquia.setFk_municipio(new DtoMunicipio(1));
        Response resultado = servicio.registrarParroquia( "",dtoParroquia );
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void consultarParroquiaTest() throws  Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Response resultado = servicio.consultarParroquia("",1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("petare");
        dtoParroquia.setValor_socio_economico(1000);
        Response resultado = servicio.actualizarParroquia("",1, dtoParroquia);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Response resultado = servicio.eliminarParroquia("",1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarParroquiasTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Response resultado = servicio.listarParroquias("");
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
