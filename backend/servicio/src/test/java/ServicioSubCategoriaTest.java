import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.servicio.ServicioSubCategoria;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioSubCategoriaTest {

    @Test
    public void registrarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria();
        dtoSubCategoria.setNombre( "Bebida");
        dtoSubCategoria.setCategoria( new DtoCategoria(1));
        Response resultado = servicio.registrarSubCategoria( dtoSubCategoria);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void actualizarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria(1);
        dtoSubCategoria.setCategoria( new DtoCategoria(1));
        dtoSubCategoria.setNombre("Soy una subc actualizada");
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
        dtoSubCategoria.setNombre( "Soy una subcategoria borrada");
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
