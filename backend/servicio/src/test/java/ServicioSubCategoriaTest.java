import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.servicio.ServicioSubCategoria;
import org.junit.Assert;
import org.junit.Test;

public class ServicioSubCategoriaTest {

    @Test
    public void registrarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria();
        dtoSubCategoria.setNombre( "Bebida");
        // Se debe tener una Categoria en la BD
        dtoSubCategoria.setCategoria( new DtoCategoria(2));
        DtoSubCategoria resultado = servicio.registrarSubCategoria( dtoSubCategoria);
        Assert.assertNotEquals( resultado.get_id(), 0);
    }


}
