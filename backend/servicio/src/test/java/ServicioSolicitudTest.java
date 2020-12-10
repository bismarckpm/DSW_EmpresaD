import mercadeoucab.dtos.*;
import mercadeoucab.entidades.Marca;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.servicio.ServicioMarca;
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
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoTipo dtoTipo = new DtoTipo(1);
        dtoSolicitud.setTipo(dtoTipo);
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSolicitud.setSubCategoria(dtoSubCategoria);
        DtoSolicitud resultado = servicio.registrarSolicitud( dtoSolicitud);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void actualizarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Adidas");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        DtoTipo dtoTipo = new DtoTipo(1);
        dtoSolicitud.setTipo(dtoTipo);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSolicitud.setSubCategoria(dtoSubCategoria);
        DtoSolicitud paraActualizar = servicio.registrarSolicitud( dtoSolicitud);
        paraActualizar.setEstado("aceptada");
        DtoSolicitud resultado = servicio.actualizarSolicitud( paraActualizar.get_id(), paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoTipo dtoTipo = new DtoTipo(1);
        dtoSolicitud.setTipo(dtoTipo);
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSolicitud.setSubCategoria(dtoSubCategoria);
        DtoSolicitud paraBorrar = servicio.registrarSolicitud( dtoSolicitud);
        DtoSolicitud resultado = servicio.eliminarSolicitud( paraBorrar.get_id());
        Assert.assertEquals( resultado.get_id(), paraBorrar.get_id());
    }

    @Test
    public void obtenerSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoTipo dtoTipo = new DtoTipo(1);
        dtoSolicitud.setTipo(dtoTipo);
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSolicitud.setSubCategoria(dtoSubCategoria);
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
