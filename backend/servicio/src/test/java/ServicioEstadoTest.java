import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Estado;
import mercadeoucab.servicio.ServicioEstado;
import mercadeoucab.servicio.ServicioPais;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ServicioEstadoTest {

    @Test
    public void registrarEstadoTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Gurico");
        dtoEstado.setFk_pais(new DtoPais(1));
        Estado resultado = servicio.agregarEstado(dtoEstado);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        Estado consulta = servicio.consultarEstado(1);
        Assert.assertEquals(1, consulta.get_id());
    }

    @Test
    public void actualizarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Gurico");
        dtoEstado.setFk_pais(new DtoPais(1));
        Estado modificado = servicio.actualizarEstado(1, dtoEstado);
        Assert.assertNotNull(modificado.getModificado_el());
    }

    @Test
    public void eliminarEstadoTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        Estado eliminado = servicio.eliminarEstado(1);
        Assert.assertNotEquals(1, eliminado.getActivo());
    }

    @Test
    public void listarEstadosTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        List<Estado> resultado = servicio.listarEstador();
        Assert.assertNotNull(resultado);
    }
}
