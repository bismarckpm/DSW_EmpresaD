import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.servicio.ServicioEstado;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

public class ServicioEstadoTest {

    @Test
    public void registrarEstadoTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(1));
        Response resultado = servicio.agregarEstado(dtoEstado);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void consultarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        Response resultado = servicio.consultarEstado(1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(1));
        Response resultado = servicio.actualizarEstado(1, dtoEstado);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarEstadoTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        Response resultado = servicio.eliminarEstado(1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarEstadosTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        Response resultado = servicio.listarEstador();
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
