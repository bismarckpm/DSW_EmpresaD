import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.servicio.ServicioPregunta;
import org.junit.Assert;
import org.junit.Test;

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
}
