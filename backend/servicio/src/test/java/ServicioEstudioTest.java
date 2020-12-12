import mercadeoucab.dtos.*;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Marca;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.servicio.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioEstudioTest {

    @Test
    public void registrarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio();
        //Datos Estudio
        dtoEstudio.setEstado("En ejecucion");
        dtoEstudio.setTipo("En linea");
        dtoEstudio.setEscuestasEsperadas(100);
        //Datos estudio
        //Usuario
        dtoEstudio.setFk_usuario(new DtoUsuario(1));
        //Muestra Solicitud
        dtoEstudio.setFk_muestra_poblacion(new DtoMuestraPoblacion(1));
        //Solicitud
        dtoEstudio.setSolicitud( new DtoSolicitud(1));
        Estudio resultado = servicio.agregarEstudio(dtoEstudio);
        Assert.assertNotEquals(0, resultado.get_id());
    }

    @Test
    public void consultarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        Estudio consultado = servicio.consultarEstudio((long)1);
        Assert.assertEquals( 1, consultado.get_id());
    }

    @Test
    public void listarEstudiosTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        List<Estudio> estudios = servicio.listarEstudios();
        Assert.assertNotNull(estudios);
    }

    @Test
    public void eliminarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        Estudio eliminado = servicio.eliminarEstudio((long)1);
        Assert.assertEquals(1, eliminado.getActivo());
    }

    @Test
    public void actualizarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio();
        //Datos Estudio
        dtoEstudio.setEstado("En ejecucion");
        dtoEstudio.setTipo("En linea");
        dtoEstudio.setEscuestasEsperadas(100);
        //Datos estudio
        //Usuario
        dtoEstudio.setFk_usuario(new DtoUsuario(1));
        //Muestra Solicitud
        dtoEstudio.setFk_muestra_poblacion(new DtoMuestraPoblacion(1));
        //Solicitud
        dtoEstudio.setSolicitud( new DtoSolicitud(1));
        Estudio actualizado = servicio.actualizarEstudio(1, dtoEstudio);
        Assert.assertNotNull(actualizado.getModificado_el());
    }
}
