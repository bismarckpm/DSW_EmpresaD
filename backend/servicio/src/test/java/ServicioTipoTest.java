import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.servicio.ServicioTipo;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioTipoTest {

    @Test
    public void registrarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre( "barra");
        dtoTipo.setSubCategoria(new DtoSubCategoria());
        Response resultado = servicio.registrarTipo( dtoTipo);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void actualizarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo(1);
        dtoTipo.setNombre("aerosol");
        Response resultado = servicio.actualizarTipo( dtoTipo.get_id(),dtoTipo);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void eliminarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo(1 );
        Response resultado = servicio.eliminarTipo( dtoTipo.get_id());
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void obtenerTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo(1);
        Response resultado = servicio.obtenerTipo( dtoTipo.get_id());
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void listarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        Response resultado = servicio.listarTipos();
        Assert.assertEquals(resultado.getStatus(), 200);
    }
}
