import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.servicio.ServicioOcupacion;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;


public class ServicioOcupacionTest {

    @Test
    public void registrarOcupacionTest() throws Exception {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO= new DtoOcupacion();
        DTOO.setNombre("Cocinar");
        Response resultado = servicio.registrarOcupacion("", DTOO );
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void obtenerOcupacionTest() throws Exception {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO = new DtoOcupacion(1);
        Response resultado = servicio.obtenerOcupacion("",DTOO.get_id());
        Assert.assertEquals(200 , resultado.getStatus());
    }

    @Test
    public void obtenerListaOcupacionTest() throws Exception{
        ServicioOcupacion servicio = new ServicioOcupacion();
        Response LDTOO= servicio.listarOcupacion("");
        Assert.assertEquals(200, LDTOO.getStatus());
    }

    @Test
    public void actualizarOcupacionTest() throws Exception {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO= new DtoOcupacion(1);
        DTOO.setNombre("Leer libros");
        Response resultado = servicio.actualizarOcupacion( "",DTOO.get_id(),DTOO);
        Assert.assertEquals(200, resultado.getStatus());
    }

    @Test
    public void eliminarOcupacionest() throws Exception {
        ServicioOcupacion servicio = new ServicioOcupacion();
        DtoOcupacion DTOO= new DtoOcupacion(1);
        Response resultado = servicio.eliminarOcupacion("",DTOO.get_id());
        Assert.assertEquals(200, resultado.getStatus());
    }

}
