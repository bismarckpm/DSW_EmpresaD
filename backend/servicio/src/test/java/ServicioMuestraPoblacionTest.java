import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.servicio.ServicioMuestraPoblacion;
import mercadeoucab.servicio.ServicioOcupacion;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

public class ServicioMuestraPoblacionTest {

    @Test
    public void agregarMuestraPoblacionTest() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion("masculino",
                                                                          "Alto",
                                                                          "universitario",
                                                                            Date.valueOf("1997-02-28"),
                                                                            Date.valueOf("2010-02-28"),
                                                                2);
        dtoMuestraPoblacion.setFk_lugar(dtoParroquia);

        DtoOcupacion ocupacion = new DtoOcupacion(1);
        dtoMuestraPoblacion.setDtoOcupacion(ocupacion);

        Response resultado = servicio.registrarMuestraPoblacion(dtoMuestraPoblacion);
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public  void listarMuestrasPoblacionTest() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        List<MuestraPoblacion> muestras = servicio.listarMuestrasPoblaciones();
        Assert.assertNotNull(muestras);
    }
/*
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
        DtoOcupacion ocupacion = new DtoOcupacion(1);
        dtoMuestraPoblacion.setDtoOcupacion(ocupacion);
        MuestraPoblacion consultar = servicio.registrarMuestraPoblacion(dtoMuestraPoblacion);
        MuestraPoblacion consultado = servicio.consultarMuestraPoblacion(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
    }
*/
    @Test
    public void eliminarMuestraPoblacionTest() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion(1);
        Response resultado = servicio.eliminarMuestraPoblacion(dtoMuestraPoblacion.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void actualizarMuestraPoblacion() throws Exception{
        ServicioMuestraPoblacion servicio = new ServicioMuestraPoblacion();
        DtoMuestraPoblacion dtoMuestraPoblacion = new DtoMuestraPoblacion(1);
        dtoMuestraPoblacion.setRangoEdadInicio(Date.valueOf("1997-02-28"));
        dtoMuestraPoblacion.setRangoEdadFin(Date.valueOf("2010-02-28"));
        dtoMuestraPoblacion.setCantidadHijos(3);
        dtoMuestraPoblacion.setNivelAcademico("universitario");
        dtoMuestraPoblacion.setGenero("femenino");
        Response actualizado = servicio.actualizarMuestraPoblacion(dtoMuestraPoblacion.get_id(), dtoMuestraPoblacion);
        Assert.assertEquals(200, actualizado.getStatus());
    }
}
