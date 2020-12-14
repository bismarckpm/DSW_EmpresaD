import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.servicio.ServicioRespuesta;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioRespuestaTest {

    @Test
    public void registrarRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta dtoRespuesta = new DtoRespuesta();
        dtoRespuesta.setRespuesta("Me gustaria tener mas opciones y no solo 3");
        DtoOpcion opcion = new DtoOpcion(1);
        dtoRespuesta.set_dtoopcion( opcion );
        DtoEncuestaEstudio dtoEncuestaEstudio = new DtoEncuestaEstudio(1);
        dtoRespuesta.setDtoEncuestaEstudio(dtoEncuestaEstudio);
        DtoUsuario usuario = new DtoUsuario(1);
        dtoRespuesta.setDtousuario(usuario);

        Response resultado = servicio.registrarRespuesta( dtoRespuesta );
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void obtenerRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta dtoRespuesta = new DtoRespuesta(1);

        Response resultado = servicio.obtenerRespuesta(dtoRespuesta.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void obtenerListaRespuestaTest(){
        ServicioRespuesta servicio = new ServicioRespuesta();
        Response LDTOO= servicio.listarRespuesta();
        Assert.assertEquals(200, LDTOO.getStatus());
    }

    @Test
    public void actualizarRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta dtoRespuesta = new DtoRespuesta(1);
        dtoRespuesta.setRespuesta("ups me equivoque");
        Response resultado = servicio.actualizarRespuesta( dtoRespuesta.get_id(), dtoRespuesta);
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void eliminarRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta dtoRespuesta = new DtoRespuesta(1);
        Response resultado = servicio.eliminarRespuesta(dtoRespuesta.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

}
