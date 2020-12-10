import mercadeoucab.dtos.*;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Marca;
import mercadeoucab.entidades.MuestraPoblacion;
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
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        dtoEstudio.setFk_usuario(new DtoUsuario(usuario.get_id()));
        //Usuario
        //Muestra Solicitud
        ServicioMuestraPoblacion servicioMuestraPoblacion = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                10,
                "universitario",
                20,
                30,
                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        MuestraPoblacion muestraPoblacion = servicioMuestraPoblacion.registrarMuestraPoblacion(dtoMuestraPoblacion);
        dtoEstudio.setFk_muestra_poblacion(new DtoMuestraPoblacion(muestraPoblacion.get_id()));
        //Muestra Solicitud
        //Solicitud
        ServicioSolicitud servicioSolicitud = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        //Marca
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        //Marca
        dtoSolicitud.setUsuario( new DtoUsuario( usuario.get_id()));
        DtoSolicitud solicitud = servicioSolicitud.registrarSolicitud( dtoSolicitud);
        dtoEstudio.setSolicitud( solicitud );
        //Solicitud
        Estudio resultado = servicio.agregarEstudio(dtoEstudio);
        Assert.assertNotEquals(0, resultado.get_id());
    }

    @Test
    public void consultarEstudioTest() throws Exception{
        ServicioEstudio servicio = new ServicioEstudio();
        DtoEstudio dtoEstudio = new DtoEstudio();
        //Datos Estudio
        dtoEstudio.setEstado("En ejecucion");
        dtoEstudio.setTipo("En linea");
        dtoEstudio.setEscuestasEsperadas(100);
        //Datos estudio
        //Usuario
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        dtoEstudio.setFk_usuario(new DtoUsuario(usuario.get_id()));
        //Usuario
        //Muestra Solicitud
        ServicioMuestraPoblacion servicioMuestraPoblacion = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                10,
                "universitario",
                20,
                30,
                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        MuestraPoblacion muestraPoblacion = servicioMuestraPoblacion.registrarMuestraPoblacion(dtoMuestraPoblacion);
        dtoEstudio.setFk_muestra_poblacion(new DtoMuestraPoblacion(muestraPoblacion.get_id()));
        //Muestra Solicitud
        //Solicitud
        ServicioSolicitud servicioSolicitud = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        //Marca
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        //Marca
        dtoSolicitud.setUsuario( new DtoUsuario( usuario.get_id()));
        DtoSolicitud solicitud = servicioSolicitud.registrarSolicitud( dtoSolicitud);
        dtoEstudio.setSolicitud( solicitud );
        //Solicitud
        Estudio consultar = servicio.agregarEstudio(dtoEstudio);
        Estudio consultado = servicio.consultarEstudio(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
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
        DtoEstudio dtoEstudio = new DtoEstudio();
        //Datos Estudio
        dtoEstudio.setEstado("En ejecucion");
        dtoEstudio.setTipo("En linea");
        dtoEstudio.setEscuestasEsperadas(100);
        //Datos estudio
        //Usuario
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        dtoEstudio.setFk_usuario(new DtoUsuario(usuario.get_id()));
        //Usuario
        //Muestra Solicitud
        ServicioMuestraPoblacion servicioMuestraPoblacion = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                10,
                "universitario",
                20,
                30,
                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        MuestraPoblacion muestraPoblacion = servicioMuestraPoblacion.registrarMuestraPoblacion(dtoMuestraPoblacion);
        dtoEstudio.setFk_muestra_poblacion(new DtoMuestraPoblacion(muestraPoblacion.get_id()));
        //Muestra Solicitud
        //Solicitud
        ServicioSolicitud servicioSolicitud = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        //Marca
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        //Marca
        dtoSolicitud.setUsuario( new DtoUsuario( usuario.get_id()));
        DtoSolicitud solicitud = servicioSolicitud.registrarSolicitud( dtoSolicitud);
        dtoEstudio.setSolicitud( solicitud );
        //Solicitud
        Estudio eliminar = servicio.agregarEstudio(dtoEstudio);
        Estudio eliminado = servicio.eliminarEstudio(eliminar.get_id());
        Assert.assertEquals(0, eliminado.getActivo());
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
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        dtoEstudio.setFk_usuario(new DtoUsuario(usuario.get_id()));
        //Usuario
        //Muestra Solicitud
        ServicioMuestraPoblacion servicioMuestraPoblacion = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                10,
                "universitario",
                20,
                30,
                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        MuestraPoblacion muestraPoblacion = servicioMuestraPoblacion.registrarMuestraPoblacion(dtoMuestraPoblacion);
        dtoEstudio.setFk_muestra_poblacion(new DtoMuestraPoblacion(muestraPoblacion.get_id()));
        //Muestra Solicitud
        //Solicitud
        ServicioSolicitud servicioSolicitud = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        //Marca
        ServicioMarca servicioMarca = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca registrarMarca = servicioMarca.registrarMarca(marca);
        dtoSolicitud.setMarca( new DtoMarca(registrarMarca.get_id()));
        //Marca
        dtoSolicitud.setUsuario( new DtoUsuario( usuario.get_id()));
        DtoSolicitud solicitud = servicioSolicitud.registrarSolicitud( dtoSolicitud);
        dtoEstudio.setSolicitud( solicitud );
        //Solicitud
        Estudio actualizar = servicio.agregarEstudio(dtoEstudio);
        dtoEstudio.setEstado("Culminado");
        dtoEstudio.setEscuestasEsperadas(5000);
        Estudio actualizado = servicio.actualizarEstudio(actualizar.get_id(), dtoEstudio);
        Assert.assertNotNull(actualizado.getModificado_el());
    }
}
