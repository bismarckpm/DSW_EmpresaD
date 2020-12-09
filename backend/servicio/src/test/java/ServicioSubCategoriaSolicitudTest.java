import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoSubCategoriaSolicitud;
import mercadeoucab.entidades.SubCategoriaSolicitud;
import mercadeoucab.servicio.ServicioSubCategoriaSolicitud;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioSubCategoriaSolicitudTest {

    @Test
    public void registrarSubCategoriaSolicitudTest() throws Exception{
        ServicioSubCategoriaSolicitud servicio = new ServicioSubCategoriaSolicitud();
        DtoSubCategoriaSolicitud dtoSubCategoriaSolicitud = new DtoSubCategoriaSolicitud();
        dtoSubCategoriaSolicitud.setSolicitud(
                new DtoSolicitud(1)
        );
        dtoSubCategoriaSolicitud.setSubCategoria(
                new DtoSubCategoria(1)
        );
        DtoSubCategoriaSolicitud resultado = servicio.registrarSubCategoriaSolicitud(
                dtoSubCategoriaSolicitud
        );
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarSubCategoriaSolicitudTest() throws Exception{
        ServicioSubCategoriaSolicitud servicio = new ServicioSubCategoriaSolicitud();
        DtoSubCategoriaSolicitud dtoSubCategoriaSolicitud = new DtoSubCategoriaSolicitud();
        dtoSubCategoriaSolicitud.setSolicitud(
                new DtoSolicitud(1)
        );
        dtoSubCategoriaSolicitud.setSubCategoria(
                new DtoSubCategoria(1)
        );
        DtoSubCategoriaSolicitud paraBorrar = servicio.registrarSubCategoriaSolicitud(
                dtoSubCategoriaSolicitud
        );
        DtoSubCategoriaSolicitud resultado = servicio.eliminarSubCategoriaSolicitud(
                paraBorrar.get_id()
        );
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerSubCategoriaSolicitudTest() throws Exception{
        ServicioSubCategoriaSolicitud servicio = new ServicioSubCategoriaSolicitud();
        DtoSubCategoriaSolicitud dtoSubCategoriaSolicitud = new DtoSubCategoriaSolicitud();
        dtoSubCategoriaSolicitud.setSolicitud(
                new DtoSolicitud(1)
        );
        dtoSubCategoriaSolicitud.setSubCategoria(
                new DtoSubCategoria(1)
        );
        DtoSubCategoriaSolicitud paraConsultar = servicio.registrarSubCategoriaSolicitud(
                dtoSubCategoriaSolicitud
        );
        DtoSubCategoriaSolicitud resultado = servicio.obtenerSubCategoriaSolicitud(
                paraConsultar.get_id()
        );
        Assert.assertEquals(resultado.get_id(), paraConsultar.get_id());
    }

    @Test
    public void listarSubCategoriaSolicitudTest() throws Exception{
        ServicioSubCategoriaSolicitud servicio = new ServicioSubCategoriaSolicitud();
        List<SubCategoriaSolicitud> resultado = servicio.listarSubCategoriaSolicitud();
        Assert.assertNotNull( resultado);
    }
}
