import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioSolicitud;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServicioSolicitudTest {


    @Test
    public void registrarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        dtoSolicitud.setComentarios("Comentario");
        dtoSolicitud.setMarca("marca");
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        dtoSolicitud.setMuestraPoblacion(new DtoMuestraPoblacion(1));
        List<DtoPresentacion> presentacions = new ArrayList<>();
        presentacions.add(new DtoPresentacion(1));

        dtoSolicitud.setPresentaciones(presentacions);
        Response resultado = servicio.registrarSolicitud("", dtoSolicitud);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void actualizarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud(1);
        dtoSolicitud.setEstado("aceptada");
        Response resultado = servicio.actualizarSolicitud("", dtoSolicitud.get_id(), dtoSolicitud);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void eliminarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud(1);
        Response resultado = servicio.eliminarSolicitud( "",dtoSolicitud.get_id());
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void obtenerSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud(1);
        Response resultado = servicio.obtenerSolicitud( "",dtoSolicitud.get_id());
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void listarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        Response resultado = servicio.listarSolicitud("");
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void listarSolicitudEstadoTest() throws Exception{
        ServicioSolicitud servicio = new ServicioSolicitud();
        Response resultado = servicio.listarSolicitudEstado("","solicitada");
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void preguntasRecomendadasTest() throws Exception{
        ServicioSolicitud servicio = new ServicioSolicitud();
        Response resultado = servicio.preguntasRecomendadas("",1);
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void muestrasRecomendadasTest() throws Exception{
        ServicioSolicitud servicio = new ServicioSolicitud();
        Response resultado = servicio.preguntasRecomendadas("",1);
        Assert.assertEquals(200, resultado.getStatus());
    }
}