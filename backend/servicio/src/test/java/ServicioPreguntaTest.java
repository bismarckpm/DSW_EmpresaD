import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.servicio.ServicioPregunta;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioPreguntaTest {

    @Test
    public void registrarPreguntaTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta();
        dtoPregunta.setNombre_pregunta("Olis");
        dtoPregunta.setRango("oliwis");
        dtoPregunta.setTipo("abierta");
        dtoPregunta.setUsuarioDto(new DtoUsuario(1));
        Response resultado = servicio.registrarPregunta(dtoPregunta);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void consultarPreguntaTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta(1);
        Response resultado = servicio.consultarPregunta(dtoPregunta.get_id());
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void eliminarPreguntaTest() throws  Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta(1);
        Response resultado = servicio.eliminarPregunta(dtoPregunta.get_id());
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void listarPreguntasTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        Response resultado = servicio.listarPreguntas();
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void actualizarPreguntaTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta(1);
        dtoPregunta.setNombre_pregunta("cambio");
        dtoPregunta.setRango("rango");
        dtoPregunta.setTipo("abierta");
        Response resultado = servicio.actualizarPregunta(dtoPregunta.get_id(),dtoPregunta);
        Assert.assertEquals(resultado.getStatus(), 200);
    }
}
