import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.servicio.ServicioMunicipio;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioMunicipioTest {

    @Test
    public void registrarMunicipioTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("La pastora");
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
        dtoMunicipio.setNombre("Libertador");
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
