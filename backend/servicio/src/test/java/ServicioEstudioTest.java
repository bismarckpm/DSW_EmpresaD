import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;
import mercadeoucab.servicio.*;
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
        //Muestra Solicitud
        dtoEstudio.setFk_muestra_poblacion(new DtoMuestraPoblacion(1));
        //Solicitud
        dtoEstudio.setSolicitud( new DtoSolicitud(1));
        List<DtoPregunta> preguntas = new ArrayList<>();
        preguntas.add(new DtoPregunta(1));
        preguntas.add(new DtoPregunta(5));
        preguntas.add(new DtoPregunta(4));
        preguntas.add(new DtoPregunta(3));
        preguntas.add(new DtoPregunta(2));
        dtoEstudio.setPreguntas(preguntas);
        Response resultado = servicio.agregarEstudio(dtoEstudio);
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void consultarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(9);
        Response consultado = servicio.consultarEstudio(dtoEstudio.get_id());
        Assert.assertEquals( 200, consultado.getStatus());
    }

    @Test
    public void listarEstudiosTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        Response estudios = servicio.listarEstudios();
        Assert.assertEquals(200, estudios.getStatus());
    }

    @Test
    public void eliminarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        Response resultado = servicio.eliminarEstudio(dtoEstudio.get_id());
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
        Response resultado = servicio.actualizarEstudio(1, dtoEstudio);
        Assert.assertEquals(200, resultado.getStatus());
    }
    
    @Test
    public void usuariosRespondieronEncuestaTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        Response resultado = servicio.usuariosRespondieronEncuesta(dtoEstudio.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void usuariosAplicanEncuestaTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio(1);
        Response resultado = servicio.usuariosAplicanEncuesta(dtoEstudio.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }


}
