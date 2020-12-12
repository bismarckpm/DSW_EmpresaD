import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.servicio.ServicioTipo;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioTipoTest {

    @Test
    public void registrarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre( "Soy un tipo");
        Response resultado = servicio.registrarTipo( dtoTipo);
        Assert.assertEquals( 200, resultado.getStatus());
    }

    @Test
    public void actualizarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo(1);
        dtoTipo.setNombre(" Soy un Tipo actualizado");
        Response resultado = servicio.actualizarTipo( dtoTipo.get_id(),dtoTipo);
        Assert.assertEquals( 200, resultado.getStatus());
    }

    @Test
    public void eliminarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo(1 );
        Response resultado = servicio.eliminarTipo( dtoTipo.get_id());
        Assert.assertEquals( 200, resultado.getStatus());
    }

    @Test
    public void obtenerTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo(1);
        Response resultado = servicio.obtenerTipo( dtoTipo.get_id());
        Assert.assertEquals( 200, resultado.getStatus());
    }

    @Test
    public void listarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        Response resultado = servicio.listarTipos();
        Assert.assertEquals( 200, resultado.getStatus());
    }
}
