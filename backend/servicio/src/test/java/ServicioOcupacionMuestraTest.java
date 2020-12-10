import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.dtos.DtoOcupacionMuestra;
import mercadeoucab.entidades.OcupacionMuestra;
import mercadeoucab.excepciones.PruebaExcepcion;
import mercadeoucab.servicio.ServicioOcupacionMuestra;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioOcupacionMuestraTest {

    @Test
    public void registrarOcupacionMuestraTest() throws PruebaExcepcion {
        ServicioOcupacionMuestra servicio = new ServicioOcupacionMuestra();
        DtoOcupacionMuestra DTOM= new DtoOcupacionMuestra();
        DTOM.setActivo(1);
        DtoMuestraPoblacion DMP=new DtoMuestraPoblacion();
        DMP.set_id(1);
        DTOM.setFk_muestra_poblacion(DMP);
        DtoOcupacion DTOO=new DtoOcupacion();
        DTOO.set_id(1);
        DTOM.setFk_ocupacion(DTOO);
        DtoOcupacionMuestra resultado = servicio.registrarOcupacionMuestra( DTOM);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerOcupacionMuestraTest() throws PruebaExcepcion {
        ServicioOcupacionMuestra servicio = new ServicioOcupacionMuestra();
        DtoOcupacionMuestra DTOM= new DtoOcupacionMuestra();
        DTOM.setActivo(1);
        DtoMuestraPoblacion DMP=new DtoMuestraPoblacion();
        DMP.set_id(1);
        DTOM.setFk_muestra_poblacion(DMP);
        DtoOcupacion DTOO=new DtoOcupacion();
        DTOO.set_id(1);
        DTOM.setFk_ocupacion(DTOO);
        DtoOcupacionMuestra paraConsultar = servicio.registrarOcupacionMuestra( DTOM);
        DtoOcupacionMuestra resultado = servicio.obtenerOcupacionMuestra(paraConsultar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerListaOcupacionMuestraTest(){
        ServicioOcupacionMuestra servicio = new ServicioOcupacionMuestra();
        List<OcupacionMuestra> LDTOO= servicio.listarOcupacionMuestra();
        Assert.assertNotNull(LDTOO);
    }

    @Test
    public void actualizarOcupacionMuestraTest() throws Exception {
        ServicioOcupacionMuestra servicio = new ServicioOcupacionMuestra();
        DtoOcupacionMuestra DTOM= new DtoOcupacionMuestra();
        DTOM.setActivo(1);
        DtoMuestraPoblacion DMP=new DtoMuestraPoblacion();
        DMP.set_id(1);
        DTOM.setFk_muestra_poblacion(DMP);
        DtoOcupacion DTOO=new DtoOcupacion();
        DTOO.set_id(1);
        DTOM.setFk_ocupacion(DTOO);
        DtoOcupacionMuestra paraActualizar = servicio.registrarOcupacionMuestra(DTOM);
        paraActualizar.setActivo(2);
        DtoOcupacionMuestra resultado = servicio.actualizarOcupacionMuestra( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarOcupacionMuestraTest() throws Exception {
        ServicioOcupacionMuestra servicio = new ServicioOcupacionMuestra();
        DtoOcupacionMuestra DTOM= new DtoOcupacionMuestra();
        DTOM.setActivo(1);
        DtoMuestraPoblacion DMP=new DtoMuestraPoblacion();
        DMP.set_id(1);
        DTOM.setFk_muestra_poblacion(DMP);
        DtoOcupacion DTOO=new DtoOcupacion();
        DTOO.set_id(1);
        DTOM.setFk_ocupacion(DTOO);
        DtoOcupacionMuestra paraEliminar = servicio.registrarOcupacionMuestra( DTOM);
        DtoOcupacionMuestra resultado = servicio.eliminarOcupacionMuestra(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

}
