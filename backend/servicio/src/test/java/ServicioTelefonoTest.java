import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.servicio.ServicioTelefono;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioTelefonoTest {

    @Test
    public void registrarTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setTelefono("01236355962");
        //Se debe tener un DatoEncuestado en la DB
        dtoTelefono.setDatoEncuestado( new DtoDatoEncuestado(1));
        Response resultado = servicio.registrarTelefono( dtoTelefono);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setTelefono("01236355962");
        //Se debe tener un DatoEncuestado en la DB
        dtoTelefono.setDatoEncuestado( new DtoDatoEncuestado(1));
        Response resultado = servicio.modificarTelefono( (long)1, dtoTelefono);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setTelefono("01236355962");
        //Se debe tener un DatoEncuestado en la DB
        dtoTelefono.setDatoEncuestado( new DtoDatoEncuestado(1));
        Response resultado = servicio.eliminarTelefono( (long)1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void obtenerTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        Response resultado = servicio.obtenerTelefono( (long)1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
