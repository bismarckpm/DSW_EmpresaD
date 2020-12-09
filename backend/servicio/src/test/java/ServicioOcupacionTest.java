import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.excepciones.PruebaExcepcion;
import mercadeoucab.servicio.ServicioOcupacion;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class ServicioOcupacionTest {

    @Test
    public void registrarOcupacionTest() throws PruebaExcepcion {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO= new DtoOcupacion();
        DTOO.setActivo(1);
        DTOO.setNombre("Cocinar");
        DtoOcupacion resultado = servicio.registrarOcupacion( DTOO);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerOcupacionTest() throws PruebaExcepcion {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO= new DtoOcupacion();
        DTOO.setActivo(1);
        DTOO.setNombre("Cocinar");
        DtoOcupacion paraConsultar = servicio.registrarOcupacion( DTOO);
        DtoOcupacion resultado = servicio.obtenerOcupacio(paraConsultar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerListaOcupacionTest(){
        ServicioOcupacion servicio = new ServicioOcupacion();
        List<Ocupacion> LDTOO= servicio.listarOcupacion();
        Assert.assertNotNull(LDTOO);
    }

    @Test
    public void actualizarOcupacionTest() throws Exception {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO= new DtoOcupacion();
        DTOO.setActivo(1);
        DTOO.setNombre("Cocinar");
        DtoOcupacion paraActualizar = servicio.registrarOcupacion( DTOO);
        paraActualizar.setNombre("Leer libros");
        DtoOcupacion resultado = servicio.actualizarOcupacion( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarOcupacionest() throws Exception {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO= new DtoOcupacion();
        DTOO.setActivo(1);
        DTOO.setNombre("Cocinar");
        DtoOcupacion paraEliminar = servicio.registrarOcupacion( DTOO);
        DtoOcupacion resultado = servicio.eliminarOcupacion(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

}
