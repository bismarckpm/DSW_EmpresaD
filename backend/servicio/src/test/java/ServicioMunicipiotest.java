import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.servicio.ServicioMunicipio;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioMunicipiotest {

    @Test
    public void registrarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(1));
        DtoMunicipio resultado = servicio.registrarMunicipio(dtoMunicipio);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio(1);
        DtoMunicipio resultado = servicio.obtenerMunicipio(dtoMunicipio);
        Assert.assertEquals(resultado.get_id(), dtoMunicipio.get_id());
    }

    @Test
    public void actualizarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio(1);
        dtoMunicipio.setNombre("eso existe?");
        dtoMunicipio.setFk_estado(new DtoEstado(2));
        DtoMunicipio resultado = servicio.actualizarMunicipio(dtoMunicipio);
        Assert.assertEquals(resultado.get_id(), dtoMunicipio.get_id());
    }

    @Test
    public void eliminarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio(1);
        DtoMunicipio resultado = servicio.eliminarMunicipio(dtoMunicipio);
        Assert.assertEquals(resultado.get_id(), dtoMunicipio.get_id());
    }

    @Test
    public void listarMunicipiosTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        List<Municipio> resultado = servicio.listarMunicipios();
        Assert.assertNotNull(resultado);
    }
}
