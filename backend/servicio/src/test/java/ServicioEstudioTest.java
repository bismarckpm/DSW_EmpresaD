import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioEstudio;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServicioEstudioTest {

    @Test
    public void registrarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio();
        //Datos Estudio
        dtoEstudio.setEstado("En ejecucion");
        dtoEstudio.setTipo("En linea");
        dtoEstudio.setEncuestasEsperadas(100);
        //Datos estudio
        //Usuario
        dtoEstudio.setFk_usuario(new DtoUsuario(1));
        //Solicitud
        dtoEstudio.setSolicitud( new DtoSolicitud(1));
        List<DtoPregunta> preguntas = new ArrayList<>();
        preguntas.add(new DtoPregunta(1));
        preguntas.add(new DtoPregunta(5));
        preguntas.add(new DtoPregunta(4));
        preguntas.add(new DtoPregunta(3));
        preguntas.add(new DtoPregunta(2));
        dtoEstudio.setPreguntas(preguntas);
        Response resultado = servicio.agregarEstudio("",dtoEstudio);
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void consultarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        Response consultado = servicio.consultarEstudio("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjEyMzEwNTc2LCJzdWIiOiJtYWlsQG1haWwuY29tIiwiaXNzIjoibWVyY2FkZW9VY2FiIiwiZXhwIjoxNjEyMzIxMzc2fQ.NxPXeU9BMvtvGN9-gQDw4lLuyj6-00K-DXCUlDQHs_s"
                ,dtoEstudio.get_id());
        Assert.assertEquals( 200, consultado.getStatus());
    }

    @Test
    public void listarEstudiosTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        Response estudios = servicio.listarEstudios("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjEyMzEwNTc2LCJzdWIiOiJtYWlsQG1haWwuY29tIiwiaXNzIjoibWVyY2FkZW9VY2FiIiwiZXhwIjoxNjEyMzIxMzc2fQ.NxPXeU9BMvtvGN9-gQDw4lLuyj6-00K-DXCUlDQHs_s");
        Assert.assertEquals(200, estudios.getStatus());
    }

    @Test
    public void eliminarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        Response resultado = servicio.eliminarEstudio("",dtoEstudio.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void actualizarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        //Datos Estudio
        dtoEstudio.setEstado("En ejecucion");
        dtoEstudio.setTipo("En linea");
        dtoEstudio.setEncuestasEsperadas(30000);
        //Datos estudio
        Response resultado = servicio.actualizarEstudio("",1, dtoEstudio);
        Assert.assertEquals(200, resultado.getStatus());
    }
    
    @Test
    public void usuariosRespondieronEncuestaTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        Response resultado = servicio.usuariosRespondieronEncuesta("",dtoEstudio.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void usuariosAplicanEncuestaTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        Response resultado = servicio.usuariosAplicanEncuesta("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjEyMzEwNTc2LCJzdWIiOiJtYWlsQG1haWwuY29tIiwiaXNzIjoibWVyY2FkZW9VY2FiIiwiZXhwIjoxNjEyMzIxMzc2fQ.NxPXeU9BMvtvGN9-gQDw4lLuyj6-00K-DXCUlDQHs_s"
                                                             ,dtoEstudio.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }


}
