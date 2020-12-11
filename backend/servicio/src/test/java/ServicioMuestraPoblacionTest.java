import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.servicio.ServicioMuestraPoblacion;
import mercadeoucab.servicio.ServicioOcupacion;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioMuestraPoblacionTest {

    @Test
    public void agregarMuestraPoblacionTest() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                                                                          10,
                                                                          "universitario",
                                                                20,
                                                                30,
                                                                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        DtoOcupacion dtoOcupacion = new DtoOcupacion();
        dtoOcupacion.setNombre("Maestro");
        ServicioOcupacion servicioOcupacion = new ServicioOcupacion();
        DtoOcupacion ocupacion = servicioOcupacion.registrarOcupacion(dtoOcupacion);
        dtoMuestraPoblacion.setDtoOcupacion(ocupacion);

        MuestraPoblacion resultado = servicio.registrarMuestraPoblacion(dtoMuestraPoblacion);
        Assert.assertNotEquals(0, resultado.get_id());
    }

    @Test
    public  void listarMuestrasPoblacionTest() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        List<MuestraPoblacion> muestras = servicio.listarMuestrasPoblaciones();
        Assert.assertNotNull(muestras);
    }

    @Test
    public void consultarMuestraPoblacionTest() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                10,
                "universitario",
                20,
                30,
                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        DtoOcupacion dtoOcupacion = new DtoOcupacion();
        dtoOcupacion.setNombre("Maestro");
        ServicioOcupacion servicioOcupacion = new ServicioOcupacion();
        DtoOcupacion ocupacion = servicioOcupacion.registrarOcupacion(dtoOcupacion);
        dtoMuestraPoblacion.setDtoOcupacion(ocupacion);
        MuestraPoblacion consultar = servicio.registrarMuestraPoblacion(dtoMuestraPoblacion);
        MuestraPoblacion consultado = servicio.consultarMuestraPoblacion(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
    }

    @Test
    public void eliminarMuestraPoblacionTest() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                10,
                "universitario",
                20,
                30,
                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        DtoOcupacion dtoOcupacion = new DtoOcupacion();
        dtoOcupacion.setNombre("Maestro");
        ServicioOcupacion servicioOcupacion = new ServicioOcupacion();
        DtoOcupacion ocupacion = servicioOcupacion.registrarOcupacion(dtoOcupacion);
        dtoMuestraPoblacion.setDtoOcupacion(ocupacion);
        MuestraPoblacion eliminar = servicio.registrarMuestraPoblacion(dtoMuestraPoblacion);
        MuestraPoblacion eliminado = servicio.eliminarMuestraPoblacion(eliminar.get_id());
        Assert.assertEquals(0, eliminado.getActivo());
    }

    @Test
    public void actualizarMuestraPoblacion() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                10,
                "universitario",
                20,
                30,
                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);
        DtoOcupacion dtoOcupacion = new DtoOcupacion();
        dtoOcupacion.setNombre("Maestro");
        ServicioOcupacion servicioOcupacion = new ServicioOcupacion();
        DtoOcupacion ocupacion = servicioOcupacion.registrarOcupacion(dtoOcupacion);
        dtoMuestraPoblacion.setDtoOcupacion(ocupacion);
        MuestraPoblacion actualizar = servicio.registrarMuestraPoblacion(dtoMuestraPoblacion);
        dtoMuestraPoblacion.setRangoEdadInicio(30);
        dtoMuestraPoblacion.setRangoEdadFin(40);
        dtoMuestraPoblacion.setCantidadHijos(3);
        dtoMuestraPoblacion.setGenero("femenino");
        MuestraPoblacion actualizado = servicio.actualizarMuestraPoblacion(actualizar.get_id(), dtoMuestraPoblacion);
        Assert.assertNotNull(actualizado.getModificado_el());
    }
}
