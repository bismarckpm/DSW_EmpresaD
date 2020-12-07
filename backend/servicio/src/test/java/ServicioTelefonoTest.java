import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.servicio.ServicioTelefono;
import org.junit.Assert;
import org.junit.Test;

public class ServicioTelefonoTest {

    @Test
    public void registrarTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setTelefono("01236355962");
        //Se debe tener un DatoEncuestado en la DB
        dtoTelefono.setDatoEncuestado( new DtoDatoEncuestado(1));
        DtoTelefono resultado = servicio.registrarTelefono( dtoTelefono);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void actualizarTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setTelefono("01236355962");
        //Se debe tener un DatoEncuestado en la DB
        dtoTelefono.setDatoEncuestado( new DtoDatoEncuestado(1));
        DtoTelefono paraActualizar = servicio.registrarTelefono( dtoTelefono);
        paraActualizar.setTelefono("04141111111");
        DtoTelefono resultado = servicio.modificarTelefono( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setTelefono("01236355962");
        //Se debe tener un DatoEncuestado en la DB
        dtoTelefono.setDatoEncuestado( new DtoDatoEncuestado(1));
        DtoTelefono paraBorrar = servicio.registrarTelefono( dtoTelefono);
        DtoTelefono resultado = servicio.eliminarTelefono( paraBorrar.get_id());
        Assert.assertEquals(resultado.get_id(), paraBorrar.get_id());
    }

    @Test
    public void obtenerTelefonoTest() throws Exception{
        ServicioTelefono servicio = new ServicioTelefono();
        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setTelefono("01236355962");
        //Se debe tener un DatoEncuestado en la DB
        dtoTelefono.setDatoEncuestado( new DtoDatoEncuestado(1));
        DtoTelefono paraConsultar = servicio.registrarTelefono( dtoTelefono);
        DtoTelefono resultado = servicio.obtenerTelefono( paraConsultar.get_id());
        Assert.assertEquals(resultado.get_id(), paraConsultar.get_id());
    }
}
