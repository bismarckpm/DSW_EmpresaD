import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.servicio.ServicioTipo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioTipoTest {

    @Test
    public void registrarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre( "Soy un tipo");
        DtoTipo resultado = servicio.registrarTipo( dtoTipo);
        Assert.assertNotEquals( 0, resultado.get_id());
    }

    @Test
    public void actualizarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre( "Soy un tipo");
        DtoTipo paraActualizar = servicio.registrarTipo( dtoTipo);
        paraActualizar.setNombre(" Soy un Tipo actualizado");
        DtoTipo resultado = servicio.actualizarTipo( paraActualizar);
        Assert.assertNotEquals( 0, resultado.get_id());
    }

    @Test
    public void eliminarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre( "Soy un tipo eliminar");
        DtoTipo paraBorrar = servicio.registrarTipo( dtoTipo);
        DtoTipo resultado = servicio.eliminarTipo( paraBorrar.get_id());
        Assert.assertEquals( paraBorrar.get_id(), resultado.get_id());
    }

    @Test
    public void obtenerTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre( "Soy un tipo");
        DtoTipo paraConsultar = servicio.registrarTipo( dtoTipo);
        DtoTipo resultado = servicio.obtenerTipo( paraConsultar.get_id());
        Assert.assertEquals( paraConsultar.get_id(), resultado.get_id());
    }

    @Test
    public void listarTipoTest() throws Exception{
        ServicioTipo servicio = new ServicioTipo();
        List<Tipo> resultado = servicio.listarTipos();
        Assert.assertNotNull( resultado);
    }
}
