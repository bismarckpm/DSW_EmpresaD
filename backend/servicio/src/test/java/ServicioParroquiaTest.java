import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Pais;
import mercadeoucab.entidades.Parroquia;
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
        dtoParroquia.setMunicipios(new DtoMunicipio(1));
        DtoParroquia resultado = servicio.registrarParroquia( dtoParroquia );
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarParroquiaTest() throws  Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoParroquia resultado = servicio.consultarParroquia(dtoParroquia);
        Assert.assertEquals(resultado.get_id(), dtoParroquia.get_id());
    }

    @Test
    public void actualizarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        dtoParroquia.setNombre("De pana ni idea");
        dtoParroquia.setValor_socio_economico(50);
        dtoParroquia.setMunicipios(new DtoMunicipio(1));
        DtoParroquia resultado = servicio.actualizarParroquia( dtoParroquia );
    }

    @Test
    public void eliminarParroquiaTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia(1);
        DtoParroquia resultado = servicio.eliminarParroquia(dtoParroquia);
        Assert.assertEquals(resultado.get_id(), dtoParroquia.get_id());
    }

    @Test
    public void listarParroquiasTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        List<Parroquia> resultado = servicio.listarParroquias();
        Assert.assertNotNull(resultado);
    }
}