import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.servicio.ServicioEstado;
import mercadeoucab.servicio.ServicioMunicipio;
import mercadeoucab.servicio.ServicioPais;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioMunicipioTest {

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
        Municipio consultado = servicio.obtenerMunicipio(1);
        Assert.assertEquals(1, consultado.get_id());
    }

    @Test
    public void actualizarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("Modified");
        Municipio actualizado = servicio.actualizarMunicipio(1, dtoMunicipio);
        Assert.assertEquals(dtoMunicipio.getNombre(), actualizado.getNombre());
    }

    @Test
    public void eliminarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        Municipio eliminar = servicio.eliminarMunicipio(1);
        Assert.assertEquals(0, eliminar.getActivo());
    }

    @Test
    public void listarMunicipiosTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        List<Municipio> resultado = servicio.listarMunicipios();
        Assert.assertNotNull(resultado);
    }
}
