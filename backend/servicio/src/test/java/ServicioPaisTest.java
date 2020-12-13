import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.servicio.ServicioPais;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioPaisTest {

    @Test
    public void registroPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("Venecosuela");
        Response resultado = servicio.agregarPais(registro);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("modificado");
        Response resultado = servicio.actualizarPais(1, registro);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        Response resultado = servicio.eliminarPais( (long)1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void consultarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        Response resultado = servicio.obtenerPais( (long)1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarPaisesTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        Response resultado = servicio.listar_paises();
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}

