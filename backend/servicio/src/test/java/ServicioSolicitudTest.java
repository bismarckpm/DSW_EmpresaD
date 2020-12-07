import mercadeoucab.dtos.DtoMarca;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Marca;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.servicio.ServicioSolicitud;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioSolicitudTest {

    @Test
    public void registrarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        // Se debe tener una marca agregada a la BD
        dtoSolicitud.setMarca( new DtoMarca(1));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoSolicitud resultado = servicio.registrarSolicitud( dtoSolicitud);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void actualizarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        // Se debe tener una marca agregada a la BD
        dtoSolicitud.setMarca( new DtoMarca(1));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoSolicitud paraActualizar = servicio.registrarSolicitud( dtoSolicitud);
        paraActualizar.setEstado("aceptada");
        DtoSolicitud resultado = servicio.actualizarSolicitud( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        // Se debe tener una marca agregada a la BD
        dtoSolicitud.setMarca( new DtoMarca(1));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoSolicitud paraBorrar = servicio.registrarSolicitud( dtoSolicitud);
        DtoSolicitud resultado = servicio.eliminarSolicitud( paraBorrar.get_id());
        Assert.assertEquals( resultado.get_id(), paraBorrar.get_id());
    }

    @Test
    public void obtenerSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        // Se debe tener una marca agregada a la BD
        dtoSolicitud.setMarca( new DtoMarca(1));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoSolicitud paraConsultar = servicio.registrarSolicitud( dtoSolicitud);
        DtoSolicitud resultado = servicio.obtenerSolicitud( paraConsultar.get_id());
        Assert.assertEquals( resultado.get_id(), paraConsultar.get_id());
    }

    @Test
    public void listarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        List<Solicitud> resultado = servicio.listarSolicitud();
        Assert.assertNotNull( resultado);
    }
}
