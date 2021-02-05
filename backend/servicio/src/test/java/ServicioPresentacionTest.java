import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.servicio.ServicioPresentacion;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioPresentacionTest {

    @Test
    public void registrarPresentacionTest() throws Exception {
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion dtoPresentacion= new DtoPresentacion();
        dtoPresentacion.setTipo("volumen");
        dtoPresentacion.setCantidad("1000 L");
        dtoPresentacion.setFk_tipo(new DtoTipo(1));
        Response resultado = servicio.registrarPresentacion( "",dtoPresentacion );
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void actualizarPresentacionTest() throws Exception {
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion DTOP = new DtoPresentacion(1);
        DTOP.setCantidad("1000 L");
        DTOP.setTipo("volumen");
        Response resultado = servicio.actualizarPresentacion( "",DTOP.get_id(), DTOP);
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void eliminarPresentacionTest() throws Exception {
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion DTOP = new DtoPresentacion(1);
        Response resultado = servicio.eliminarPresentacion("",DTOP.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void obtenerPresentacionTest() throws Exception{
        ServicioPresentacion servicio = new ServicioPresentacion();
        DtoPresentacion DTOP = new DtoPresentacion(1);
        Response resultado = servicio.obtenerPresentacion("",DTOP.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void obtenerListaPresentacionTest() throws Exception{
        ServicioPresentacion servicio = new ServicioPresentacion();
        Response LDTOP= servicio.listarPresentacion("");
        Assert.assertEquals(200, LDTOP.getStatus());
    }

}
