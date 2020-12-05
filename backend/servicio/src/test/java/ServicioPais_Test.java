import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.servicio.ServicioPais;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioPais_Test {

    @Test
    public void registroPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Argentina");
        DtoPais resultado = servicio.agregarPais(dtoPais);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void actualizarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais dtoPais = new DtoPais(2);
        dtoPais.setNombre("Colombia");
        DtoPais resultado = servicio.actualizarPais(dtoPais);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais dtoPais = new DtoPais(1);
        DtoPais resultado = servicio.eliminarPais(dtoPais);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarPaisTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        DtoPais dtoPais = new DtoPais(1);
        DtoPais resultado = servicio.eliminarPais(dtoPais);
        Assert.assertEquals(resultado.get_id(), 1);
    }

    @Test
    public void listarPaisesTest() throws Exception{
        ServicioPais servicio = new ServicioPais();
        List<Pais> resultado = servicio.listar_paises();
        Assert.assertNotNull(resultado);
    }
}

