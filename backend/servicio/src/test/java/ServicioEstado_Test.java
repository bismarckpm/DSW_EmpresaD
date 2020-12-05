import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Estado;
import mercadeoucab.servicio.ServicioEstado;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ServicioEstado_Test {

    @Test
    public void registrarEstadoTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Gurico");
        dtoEstado.setFk_pais(new DtoPais(1));
        DtoEstado resultado = servicio.agregarEstado(dtoEstado);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado(1);
        DtoEstado resultado = servicio.consultarEstado(dtoEstado);
        Assert.assertEquals(resultado.get_id(),dtoEstado.get_id());
    }

    @Test
    public void actualizarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado(2);
        dtoEstado.setNombre("Cojedes");
        DtoEstado resultado = servicio.actualizarEstado(dtoEstado);
        Assert.assertEquals(resultado.get_id(), dtoEstado.get_id());
    }

    @Test
    public void eliminarEstadoTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado(1);
        DtoEstado resultado = servicio.eliminarEstado(dtoEstado);
        Assert.assertEquals(dtoEstado.get_id(), resultado.get_id());
    }

    @Test
    public void listarEstadosTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        List<Estado> resultado = servicio.listarEstador();
        Assert.assertNotNull(resultado);
    }
}
