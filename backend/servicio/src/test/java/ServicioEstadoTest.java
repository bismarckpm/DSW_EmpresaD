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
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("TESTS");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Gurico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado resultado = servicio.agregarEstado(dtoEstado);
        Estado consulta = servicio.consultarEstado(resultado.get_id());
        Assert.assertEquals(resultado.get_id(), consulta.get_id());
    }

    @Test
    public void consultarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("TESTS");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Gurico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado resultado = servicio.agregarEstado(dtoEstado);
        Estado consulta = servicio.consultarEstado(resultado.get_id());
        Assert.assertEquals(resultado.get_id(), consulta.get_id());
    }

    @Test
    public void actualizarEstadoTest() throws  Exception{
        ServicioEstado servicio = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("TESTS");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Gurico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado resultado = servicio.agregarEstado(dtoEstado);
        Estado consulta = servicio.consultarEstado(resultado.get_id());
        DtoEstado consulta1 = new DtoEstado(consulta.get_id());
        consulta1.setNombre("Modificacion");
        Estado modificado = servicio.actualizarEstado(consulta.get_id(), consulta1);
        Assert.assertNotNull(modificado.getModificado_el());
    }

    @Test
    public void eliminarEstadoTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("TESTS");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Gurico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado resultado = servicio.agregarEstado(dtoEstado);
        Estado eliminado = servicio.eliminarEstado(resultado.get_id());
        Assert.assertNotEquals(1, eliminado.getActivo());
    }

    @Test
    public void listarEstadosTest() throws Exception{
        ServicioEstado servicio = new ServicioEstado();
        List<Estado> resultado = servicio.listarEstador();
        Assert.assertNotNull(resultado);
    }
}
