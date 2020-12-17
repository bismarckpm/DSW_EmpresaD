import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.servicio.ServicioRespuesta;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
        dtoRespuesta.setRespuesta("deberia ser mas grande");
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

    @Test
    public void registarEncuestaRespondidaTest() throws Exception{
        ServicioRespuesta servicio = new ServicioRespuesta();
        List<DtoRespuesta> respuestas = new ArrayList<>();

        DtoRespuesta respuesta1 = new DtoRespuesta();
        respuesta1.setRespuesta("me gusta");
        respuesta1.setDtousuario(new DtoUsuario(21));
        respuesta1.setDtoEncuestaEstudio(new DtoEncuestaEstudio(22));
        respuestas.add(respuesta1);

        DtoRespuesta respuesta2 = new DtoRespuesta();
        respuesta2.setRespuesta("me gusta");
        respuesta2.setDtousuario(new DtoUsuario(21));
        respuesta2.setDtoEncuestaEstudio(new DtoEncuestaEstudio(23));
        respuestas.add(respuesta2);

        DtoRespuesta respuesta3 = new DtoRespuesta();
        respuesta3.setRespuesta("me gusta");
        respuesta3.setDtousuario(new DtoUsuario(21));
        respuesta3.setDtoEncuestaEstudio(new DtoEncuestaEstudio(24));
        respuestas.add(respuesta3);

        DtoRespuesta respuesta4 = new DtoRespuesta();
        respuesta4.setRespuesta("me gusta");
        respuesta4.setDtousuario(new DtoUsuario(21));
        respuesta4.setDtoEncuestaEstudio(new DtoEncuestaEstudio(25));
        respuestas.add(respuesta4);

        DtoRespuesta respuesta5 = new DtoRespuesta();
        respuesta5.setRespuesta("me gusta");
        respuesta5.setDtousuario(new DtoUsuario(21));
        respuesta5.setDtoEncuestaEstudio(new DtoEncuestaEstudio(26));
        respuestas.add(respuesta5);

        DtoEncuestaEstudio dtoEncuestaEstudio = new DtoEncuestaEstudio();
        dtoEncuestaEstudio.setRespuestas(respuestas);
        Response resultado = servicio.registarEncuestaRespondida(dtoEncuestaEstudio);
        Assert.assertEquals(200, resultado.getStatus());

    }

}
