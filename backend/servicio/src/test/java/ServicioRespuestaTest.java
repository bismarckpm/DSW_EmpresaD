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
        DtoRespuesta dtoRespuesta = new DtoRespuesta();
        dtoRespuesta.setRespuesta("Me gustaria tener mas opciones y no solo 3");
        DtoOpcion opcion = new DtoOpcion(1);
        dtoRespuesta.set_dtoopcion( opcion );
        DtoEncuestaEstudio dtoEncuestaEstudio = new DtoEncuestaEstudio(1);
        dtoRespuesta.setDtoEncuestaEstudio(dtoEncuestaEstudio);
        DtoUsuario usuario = new DtoUsuario(1);
        dtoRespuesta.setDtousuario(usuario);

        DtoRespuesta resultado = servicio.registrarRespuesta( dtoRespuesta );
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta dtoRespuesta = new DtoRespuesta();
        dtoRespuesta.setRespuesta("Me gustaria tener mas opciones y no solo 3");
        DtoOpcion opcion = new DtoOpcion(1);
        dtoRespuesta.set_dtoopcion( opcion );
        DtoEncuestaEstudio dtoEncuestaEstudio = new DtoEncuestaEstudio(1);
        dtoRespuesta.setDtoEncuestaEstudio(dtoEncuestaEstudio);
        DtoUsuario usuario = new DtoUsuario(1);
        dtoRespuesta.setDtousuario(usuario);

        DtoRespuesta paraConsultar = servicio.registrarRespuesta( dtoRespuesta );
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
        DtoRespuesta dtoRespuesta = new DtoRespuesta();
        dtoRespuesta.setRespuesta("Me gustaria tener mas opciones y no solo 3");
        DtoOpcion opcion = new DtoOpcion(1);
        dtoRespuesta.set_dtoopcion( opcion );
        DtoEncuestaEstudio dtoEncuestaEstudio = new DtoEncuestaEstudio(1);
        dtoRespuesta.setDtoEncuestaEstudio(dtoEncuestaEstudio);
        DtoUsuario usuario = new DtoUsuario(1);
        dtoRespuesta.setDtousuario(usuario);

        DtoRespuesta paraActualizar = servicio.registrarRespuesta( dtoRespuesta );
        dtoRespuesta.setRespuesta("ups me equivoque");
        DtoRespuesta resultado = servicio.actualizarRespuesta( paraActualizar.get_id(), dtoRespuesta);
        Assert.assertNotNull(resultado.getModificado_el());
    }

    @Test
    public void eliminarRespuestaTest() throws Exception {
        ServicioRespuesta servicio = new ServicioRespuesta();
        DtoRespuesta dtoRespuesta = new DtoRespuesta();
        dtoRespuesta.setRespuesta("Me gustaria tener mas opciones y no solo 3");
        DtoOpcion opcion = new DtoOpcion(1);
        dtoRespuesta.set_dtoopcion( opcion );
        DtoEncuestaEstudio dtoEncuestaEstudio = new DtoEncuestaEstudio(1);
        dtoRespuesta.setDtoEncuestaEstudio(dtoEncuestaEstudio);
        DtoUsuario usuario = new DtoUsuario(1);
        dtoRespuesta.setDtousuario(usuario);
        DtoRespuesta paraEliminar = servicio.registrarRespuesta( dtoRespuesta );
        DtoRespuesta resultado = servicio.eliminarRespuesta(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

}
