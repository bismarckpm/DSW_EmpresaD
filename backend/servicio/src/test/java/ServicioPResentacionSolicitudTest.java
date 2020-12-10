import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoPresentacionSolicitud;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.PresentacionSolicitud;
import mercadeoucab.servicio.ServicioPresentacionSolicitud;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioPResentacionSolicitudTest {

    @Test
    public void registrarPresentacionSolicitudTest() throws Exception {
        ServicioPresentacionSolicitud servicio = new ServicioPresentacionSolicitud();
        DtoPresentacionSolicitud DTPS= new DtoPresentacionSolicitud();
        DtoPresentacion DP=new DtoPresentacion(1);
        DTPS.setDto_presentacion(DP);
        DtoSolicitud S=new DtoSolicitud(1);
        DTPS.setDto_solicitud(S);
        DTPS.setActivo(1);
        DtoPresentacionSolicitud resultado = servicio.registrarPresentacionSolicitud( DTPS);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerPresentacionSolicitudTest() throws Exception {
        ServicioPresentacionSolicitud servicio = new ServicioPresentacionSolicitud();
        DtoPresentacionSolicitud DTPS= new DtoPresentacionSolicitud();
        DtoPresentacion DP=new DtoPresentacion(1);
        DTPS.setDto_presentacion(DP);
        DtoSolicitud S=new DtoSolicitud(1);
        DTPS.setDto_solicitud(S);
        DTPS.setActivo(1);
        DtoPresentacionSolicitud paraConsultar = servicio.registrarPresentacionSolicitud( DTPS);
        DtoPresentacionSolicitud resultado = servicio.obtenerPresentacionSolicitu(paraConsultar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerListaPresentacionSolicitudTest(){
        ServicioPresentacionSolicitud servicio = new ServicioPresentacionSolicitud();
        List<PresentacionSolicitud> LDTOO= servicio.listarPresentacionSolicitud();
        Assert.assertNotNull(LDTOO);
    }

    @Test
    public void actualizarPresentacionSolicitudTest() throws Exception {
        ServicioPresentacionSolicitud servicio = new ServicioPresentacionSolicitud();
        DtoPresentacionSolicitud DTPS= new DtoPresentacionSolicitud();
        DtoPresentacion DP=new DtoPresentacion(1);
        DTPS.setDto_presentacion(DP);
        DtoSolicitud S=new DtoSolicitud(1);
        DTPS.setDto_solicitud(S);
        DTPS.setActivo(1);
        DtoPresentacionSolicitud paraActualizar = servicio.registrarPresentacionSolicitud( DTPS);
        paraActualizar.setActivo(2);
        DtoPresentacionSolicitud resultado = servicio.actualizarPresentacionSolicitud( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarPresentacionSolicitudTest() throws Exception {
        ServicioPresentacionSolicitud servicio = new ServicioPresentacionSolicitud();
        DtoPresentacionSolicitud DTPS= new DtoPresentacionSolicitud();
        DtoPresentacion DP=new DtoPresentacion(1);
        DTPS.setDto_presentacion(DP);
        DtoSolicitud S=new DtoSolicitud(1);
        DTPS.setDto_solicitud(S);
        DTPS.setActivo(1);
        DtoPresentacionSolicitud paraEliminar = servicio.registrarPresentacionSolicitud( DTPS);
        DtoPresentacionSolicitud resultado = servicio.eliminarPresentacionSolicitud(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }



}
