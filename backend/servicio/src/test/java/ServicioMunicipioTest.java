import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.servicio.ServicioEstado;
import mercadeoucab.servicio.ServicioMunicipio;
import mercadeoucab.servicio.ServicioPais;
import javax.ws.rs.core.Response;
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
        Response resultado = servicio.registrarMunicipio(dtoMunicipio);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void consultarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        Response resultado = servicio.obtenerMunicipio(1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("Modified");
        Response resultado = servicio.actualizarMunicipio(1, dtoMunicipio);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        Response resultado = servicio.eliminarMunicipio(1);
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarMunicipiosTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        Response resultado = servicio.listarMunicipios();
        Assert.assertEquals(
                javax.ws.rs.core.Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
