import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.servicio.ServicioSubCategoria;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioSubCategoriaTest {

    @Test
    public void registrarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria();
        dtoSubCategoria.setNombre( "bebida");
        dtoSubCategoria.setCategoria( new DtoCategoria(1));
        Response resultado = servicio.registrarSubCategoria( dtoSubCategoria);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void actualizarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSubCategoria.setCategoria( new DtoCategoria(1));
        dtoSubCategoria.setNombre("alcoholica");
        Response resultado = servicio.actualizarSubCategoria(
                dtoSubCategoria.get_id(),
                dtoSubCategoria
        );
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void eliminarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSubCategoria.setNombre( "adultos");
        dtoSubCategoria.setCategoria( new DtoCategoria(1));
        Response resultado = servicio.eliminarSubCategoria(
                dtoSubCategoria.get_id()
        );
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void obtenerSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSubCategoria.setCategoria( new DtoCategoria(1));
        Response resultado = servicio.obtenerSubCategoria(
                dtoSubCategoria.get_id()
        );
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void listarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        Response resultado = servicio.listarSubCategoria();
        Assert.assertEquals(resultado.getStatus(), 200);
    }
}
