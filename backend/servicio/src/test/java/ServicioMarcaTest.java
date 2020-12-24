import mercadeoucab.dtos.DtoMarca;
import mercadeoucab.entidades.Marca;
import mercadeoucab.servicio.ServicioMarca;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioMarcaTest {

    @Test
    public void insertarMarcaTest() throws Exception{
        ServicioMarca servicio = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Response resultado = servicio.registrarMarca(marca);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }


    @Test
    public void consultarMarcaTest() throws Exception{
        ServicioMarca servicio = new ServicioMarca();
        Response resultado = servicio.consultarMarca(1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void  actualizarMarcaTest() throws  Exception{
        ServicioMarca servicio = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Axe");
        Response resultado = servicio.actualizarMarca(1, marca);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void  eliminarMarcaTest() throws Exception{
        ServicioMarca servicio = new ServicioMarca();
        Response resultado = servicio.eliminarMarca(1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarMarcasTest() throws  Exception{
        ServicioMarca servicio = new ServicioMarca();
        Response resultado = servicio.listarMarcas();
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
