import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.servicio.ServicioPresentacion;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioPresentacionTest {

    @Test
    public void registrarPresentacionTest(){
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion DTOP= new DtoPresentacion();
        DTOP.setTipo("volumen");
        DTOP.setCantidad("1000 L");
        DTOP.setActivo(1);
        DtoPresentacion resultado = servicio.registrarPresentacion( DTOP);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void actualizarPresentacionTest() throws Exception {
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion DTOP = new DtoPresentacion();
        DTOP.setActivo(1);
        DTOP.setCantidad("1000 L");
        DTOP.setTipo("volumen");
        DtoPresentacion paraActualizar = servicio.registrarPresentacion( DTOP);
        paraActualizar.setCantidad("500 Litros");
        DtoPresentacion resultado = servicio.actualizarPresentacion( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarPresentacionTest() throws Exception {
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion DTOP = new DtoPresentacion();
        DTOP.setActivo(1);
        DTOP.setCantidad("2000 L");
        DTOP.setTipo("volumen");
        DtoPresentacion paraEliminar = servicio.registrarPresentacion( DTOP);
        DtoPresentacion resultado = servicio.eliminarPresentacion(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerPresentacionTest(){
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion DTOP = new DtoPresentacion();
        DTOP.setActivo(1);
        DTOP.setCantidad("2000 L");
        DTOP.setTipo("volumen");
        DtoPresentacion paraConsultar = servicio.registrarPresentacion( DTOP);
        DtoPresentacion resultado = servicio.obtenerPresentacion(paraConsultar.get_id());
        System.out.println(resultado.getTipo());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerListaPresentacionTest(){
        ServicioPresentacion servicio = new ServicioPresentacion();
        List<Presentacion> LDTOP= servicio.listarPresentacion();
        Assert.assertNotNull(LDTOP);
    }

}
