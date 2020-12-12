import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Pais;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.servicio.ServicioEstado;
import mercadeoucab.servicio.ServicioMunicipio;
import mercadeoucab.servicio.ServicioPais;
import mercadeoucab.servicio.ServicioParroquia;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioParroquiaTest {

    @Test
    public void registroParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(0);
        dtoParroquia.setFk_municipio(new DtoMunicipio(1));
        DtoParroquia resultado = servicio.registrarParroquia( dtoParroquia );
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarParroquiaTest() throws  Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Parroquia consultada = servicio.consultarParroquia(1);
        Assert.assertEquals(1, consultada.get_id());
    }

    @Test
    public void actualizarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(1000);
        Parroquia actualizado = servicio.actualizarParroquia(1, dtoParroquia);
        Assert.assertEquals(dtoParroquia.getNombre(), actualizado.getNombre());
    }

    @Test
    public void eliminarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        Parroquia eliminada = servicio.eliminarParroquia(1);
        Assert.assertEquals(0, eliminada.getActivo());
    }

    @Test
    public void listarParroquiasTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        List<Parroquia> resultado = servicio.listarParroquias();
        Assert.assertNotNull(resultado);
    }
}
