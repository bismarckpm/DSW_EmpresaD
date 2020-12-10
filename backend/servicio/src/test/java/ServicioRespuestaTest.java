import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.servicio.ServicioRespuesta;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioRespuestaTest {

    @Test
    public void registrarRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta DTOR= new DtoRespuesta();
        DTOR.setRespuesta("Me gustaria tener mas opciones y no solo 3");
        DTOR.setActivo(1);
        DtoOpcion O=new DtoOpcion(1);
        DTOR.set_dtoopcion(O);
        DtoEncuestaEstudio EE=new DtoEncuestaEstudio(1);
        DTOR.setDtoEncuestaEstudio(EE);
        DtoUsuario U=new DtoUsuario(1);
        DTOR.setDtousuario(U);
        DtoRespuesta resultado = servicio.registrarRespuesta( DTOR);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta DTOR= new DtoRespuesta();
        DTOR.setRespuesta("Estoy muy sastifecho con el producto");
        DTOR.setActivo(1);
        DtoOpcion O=new DtoOpcion(1);
        DTOR.set_dtoopcion(O);
        DtoEncuestaEstudio EE=new DtoEncuestaEstudio(1);
        DTOR.setDtoEncuestaEstudio(EE);
        DtoUsuario U=new DtoUsuario(1);
        DTOR.setDtousuario(U);
        DtoRespuesta paraConsultar = servicio.registrarRespuesta( DTOR);
        DtoRespuesta resultado = servicio.obtenerRespuesta(paraConsultar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerListaRespuestaTest(){
        ServicioRespuesta servicio = new ServicioRespuesta();
        List<Respuesta> LDTOO= servicio.listarRespuesta();
        Assert.assertNotNull(LDTOO);
    }

    @Test
    public void actualizarRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta DTOR= new DtoRespuesta();
        DTOR.setRespuesta("Estoy muy sastifecho con el producto");
        DTOR.setActivo(1);
        DtoOpcion O=new DtoOpcion(1);
        DTOR.set_dtoopcion(O);
        DtoEncuestaEstudio EE=new DtoEncuestaEstudio(1);
        DTOR.setDtoEncuestaEstudio(EE);
        DtoUsuario U=new DtoUsuario(1);
        DTOR.setDtousuario(U);
        DtoRespuesta paraActualizar = servicio.registrarRespuesta( DTOR);
        paraActualizar.setActivo(2);
        DtoRespuesta resultado = servicio.actualizarRespuesta( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta DTOR= new DtoRespuesta();
        DTOR.setRespuesta("Estoy muy sastifecho con el producto");
        DTOR.setActivo(1);
        DtoOpcion O=new DtoOpcion(1);
        DTOR.set_dtoopcion(O);
        DtoEncuestaEstudio EE=new DtoEncuestaEstudio(1);
        DTOR.setDtoEncuestaEstudio(EE);
        DtoUsuario U=new DtoUsuario(1);
        DTOR.setDtousuario(U);
        DtoRespuesta paraEliminar = servicio.registrarRespuesta( DTOR);
        DtoRespuesta resultado = servicio.eliminarRespuesta(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

}
