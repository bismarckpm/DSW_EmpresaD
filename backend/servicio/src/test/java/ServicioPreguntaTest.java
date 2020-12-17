import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.servicio.ServicioPregunta;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServicioPreguntaTest {

    @Test
    public void registrarPreguntaTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta();
        dtoPregunta.setNombre_pregunta("cual es su opinion");
        dtoPregunta.setRango("1&10");
        dtoPregunta.setTipo("rango");
        dtoPregunta.setUsuarioDto(new DtoUsuario(1));
        List<DtoOpcion> opciones = new ArrayList();
        DtoOpcion paraAgregar = new DtoOpcion();
        paraAgregar.setNombre_opcion("mucho");
        DtoOpcion paraAgregar2 = new DtoOpcion();
        paraAgregar2.setNombre_opcion("poco");
        opciones.add( paraAgregar2);
        dtoPregunta.setOpciones( opciones);
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
