import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.servicio.ServicioPregunta;
import org.junit.Assert;
import org.junit.Test;

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
        dtoPregunta.setEstudio(new DtoEstudio(1));
        Pregunta resultado = servicio.registrarPregunta(dtoPregunta);
        Assert.assertNotEquals(0, resultado.get_id());
    }

    @Test
    public void consultarPreguntaTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta();
        dtoPregunta.setNombre_pregunta("Olis");
        dtoPregunta.setRango("oliwis");
        dtoPregunta.setTipo("abierta");
        dtoPregunta.setUsuarioDto(new DtoUsuario(1));
        dtoPregunta.setEstudio(new DtoEstudio(1));
        Pregunta consultar = servicio.registrarPregunta(dtoPregunta);
        Pregunta consultado = servicio.consultarPregunta(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
    }

    @Test
    public void eliminarPreguntaTest() throws  Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta();
        dtoPregunta.setNombre_pregunta("Olis");
        dtoPregunta.setRango("oliwis");
        dtoPregunta.setTipo("abierta");
        dtoPregunta.setUsuarioDto(new DtoUsuario(1));
        dtoPregunta.setEstudio(new DtoEstudio(1));
        Pregunta eliminar = servicio.registrarPregunta(dtoPregunta);
        Pregunta eliminado = servicio.eliminarPregunta(eliminar.get_id());
        Assert.assertEquals(0, eliminado.getActivo());
    }

    @Test
    public void listarPreguntasTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        List<Pregunta> preguntas = servicio.listarPreguntas();
        Assert.assertNotNull(preguntas);
    }

    @Test
    public void actualizarPreguntaTest() throws Exception{
        ServicioPregunta servicio = new ServicioPregunta();
        DtoPregunta dtoPregunta = new DtoPregunta();
        dtoPregunta.setNombre_pregunta("Olis");
        dtoPregunta.setRango("oliwis");
        dtoPregunta.setTipo("abierta");
        dtoPregunta.setUsuarioDto(new DtoUsuario(1));
        dtoPregunta.setEstudio(new DtoEstudio(1));
        Pregunta actualizar = servicio.registrarPregunta(dtoPregunta);
        dtoPregunta.setNombre_pregunta("cambio");
        dtoPregunta.setRango("rango");
        Pregunta actualizado = servicio.actualizarPregunta(actualizar.get_id(),dtoPregunta);
        Assert.assertNotNull(actualizado.getModificado_el());
    }
}
