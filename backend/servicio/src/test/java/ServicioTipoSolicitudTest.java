import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.dtos.DtoTipoSolicitud;
import mercadeoucab.entidades.TipoSolicitud;
import mercadeoucab.servicio.ServicioTipoSolicitud;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioTipoSolicitudTest {

    @Test
    public void registrarTipoSolicitudTest() throws Exception{
        ServicioTipoSolicitud servicio = new ServicioTipoSolicitud();
        DtoTipoSolicitud dtoTipoSolicitud = new DtoTipoSolicitud();
        dtoTipoSolicitud.setSolicitud( new DtoSolicitud(1));
        dtoTipoSolicitud.setTipo( new DtoTipo(1));
        DtoTipoSolicitud resultado = servicio.registrarTipoSolicitud( dtoTipoSolicitud);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarTipoSolicitudTest() throws Exception{
        ServicioTipoSolicitud servicio = new ServicioTipoSolicitud();
        DtoTipoSolicitud dtoTipoSolicitud = new DtoTipoSolicitud();
        dtoTipoSolicitud.setSolicitud( new DtoSolicitud(1));
        dtoTipoSolicitud.setTipo( new DtoTipo(1));
        DtoTipoSolicitud paraBorrar = servicio.registrarTipoSolicitud( dtoTipoSolicitud);
        DtoTipoSolicitud resultado = servicio.eliminarTipoSolicitud( paraBorrar.get_id());
        Assert.assertEquals( resultado.get_id(), paraBorrar.get_id());
    }

    @Test
    public void obtenerTipoSolicitudTest() throws Exception{
        ServicioTipoSolicitud servicio = new ServicioTipoSolicitud();
        DtoTipoSolicitud dtoTipoSolicitud = new DtoTipoSolicitud();
        dtoTipoSolicitud.setSolicitud( new DtoSolicitud(1));
        dtoTipoSolicitud.setTipo( new DtoTipo(1));
        DtoTipoSolicitud paraConsultar = servicio.registrarTipoSolicitud( dtoTipoSolicitud);
        DtoTipoSolicitud resultado = servicio.obtenerTipoSolicitud(paraConsultar.get_id());
        Assert.assertEquals( resultado.get_id(), paraConsultar.get_id());
    }

    @Test
    public void listarTipoSolicitudTest() throws Exception{
        ServicioTipoSolicitud servicio = new ServicioTipoSolicitud();
        List<TipoSolicitud> resultado = servicio.listarTipoSolicitud();
        Assert.assertNotNull( resultado);
    }
}
