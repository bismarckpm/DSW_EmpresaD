import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;
import mercadeoucab.servicio.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class ServicioOpcionTest {

    @Test
    public void registrarOpcionTest(){
        ServicioOpcion servicio = new ServicioOpcion();
        DtoOpcion DTOO= new DtoOpcion();
        DTOO.setNombre_opcion("Opcion 1: Se recalienta rapido");
        DTOO.setActivo(1);
        DtoPregunta DTOP=new DtoPregunta();
        DTOP.set_id(5);
        DTOO.set_Dtopregunta(DTOP);
        DtoOpcion resultado = servicio.registrarOpcion( DTOO);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerOpcionTest(){
        ServicioOpcion servicio = new ServicioOpcion();
        DtoOpcion DTOO = new DtoOpcion();
        DTOO.setNombre_opcion("opcion 2: No la comprendo muy bien");
        DTOO.setActivo(2);
        DtoPregunta DTOP=new DtoPregunta();
        DTOP.set_id(5);
        DTOO.set_Dtopregunta(DTOP);
        DtoOpcion paraConsultar = servicio.registrarOpcion( DTOO);
        DtoOpcion resultado = servicio.obtenerOpcion(paraConsultar.get_id());
        System.out.println(resultado.getNombre_opcion());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerListaOpcionesTest(){
        ServicioOpcion servicio = new ServicioOpcion();
        List<Opcion> LDTOO= servicio.listarOpcion();
        Assert.assertNotNull(LDTOO);
    }

    @Test
    public void actualizarOpcionTest() throws Exception {
        ServicioOpcion servicio = new ServicioOpcion();
        DtoOpcion DTOO = new DtoOpcion();
        DTOO.setNombre_opcion("opcion 2: No la comprendo muy bien");
        DTOO.setActivo(2);
        DtoPregunta DTOP=new DtoPregunta();
        DTOP.set_id(5);
        DTOO.set_Dtopregunta(DTOP);
        DtoOpcion paraActualizar = servicio.registrarOpcion( DTOO);
        paraActualizar.setNombre_opcion("opcion 10: No estoy a favor");
        paraActualizar.setActivo(1);
        DtoOpcion resultado = servicio.actualizarOpcion( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarOpcionTest() throws Exception {
        ServicioOpcion servicio = new ServicioOpcion();
        DtoOpcion DTOO = new DtoOpcion();
        DTOO.setNombre_opcion("opcion 2: No la comprendo muy bien");
        DTOO.setActivo(2);
        DtoPregunta DTOP=new DtoPregunta();
        DTOP.set_id(5);
        DTOO.set_Dtopregunta(DTOP);
        DtoOpcion paraEliminar = servicio.registrarOpcion( DTOO);
        DtoOpcion resultado = servicio.eliminarOpcion(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }





}
