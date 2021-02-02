import mercadeoucab.dtos.DtoPais;
import mercadeoucab.servicio.ServicioPais;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioPaisTest {

    @Test
    public void registroPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("Venezuela");
        Response resultado = servicio.agregarPais("",registro);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("Venezuela");
        Response resultado = servicio.actualizarPais("",1, registro);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        Response resultado = servicio.eliminarPais("",1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void consultarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        Response resultado = servicio.obtenerPais("",1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarPaisesTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        Response resultado = servicio.listar_paises("");
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}

