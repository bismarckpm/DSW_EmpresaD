import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.servicio.ServicioPais;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioPaisTest {

    @Test
    public void registroPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("Venecosuela");
        DtoPais resultado = servicio.agregarPais(registro);
        DtoPais consulta = servicio.obtenerPais(resultado.get_id());
        Assert.assertEquals(resultado.get_id(), consulta.get_id());
    }

    @Test
    public void actualizarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("Venezuela");
        DtoPais resultado = servicio.agregarPais(registro);
        DtoPais modificar = servicio.obtenerPais(resultado.get_id());
        modificar.setNombre("modificado");
        DtoPais modificado = servicio.actualizarPais(modificar.get_id(), modificar);
        Assert.assertEquals(modificado.get_id() , modificar.get_id());
    }

    @Test
    public void eliminarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("eliminar");
        DtoPais eliminar = servicio.agregarPais(registro);
        DtoPais eliminado = servicio.eliminarPais(eliminar.get_id());
        Assert.assertNotEquals(eliminado.getActivo(), eliminar.getActivo());
    }

    @Test
    public void consultarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais registro = new DtoPais();
        registro.setNombre("consulta");
        DtoPais consulta = servicio.agregarPais(registro);
        DtoPais consultado = servicio.obtenerPais(consulta.get_id());
        Assert.assertEquals(consulta.get_id(), consultado.get_id());
    }

    @Test
    public void listarPaisesTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        List<Pais> resultado = servicio.listar_paises();
        Assert.assertNotNull(resultado);
    }
}

