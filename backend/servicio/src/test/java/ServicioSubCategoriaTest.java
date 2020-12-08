import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.servicio.ServicioSubCategoria;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void actualizarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria();
        dtoSubCategoria.setNombre( "Soy una subcategoria");
        // Se debe tener una Categoria en la BD
        dtoSubCategoria.setCategoria( new DtoCategoria(2));
        DtoSubCategoria paraActualizar = servicio.registrarSubCategoria(
                dtoSubCategoria
        );
        paraActualizar.setNombre("Soy una subc actualizada");
        DtoSubCategoria resultado = servicio.actualizarSubCategoria(
                paraActualizar
        );
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria();
        dtoSubCategoria.setNombre( "Soy una subcategoria borrada");
        // Se debe tener una Categoria en la BD
        dtoSubCategoria.setCategoria( new DtoCategoria(2));
        DtoSubCategoria paraBorrar = servicio.registrarSubCategoria(
                dtoSubCategoria
        );
        DtoSubCategoria resultado = servicio.eliminarSubCategoria(
                paraBorrar.get_id()
        );
        Assert.assertEquals(resultado.get_id(), paraBorrar.get_id());
    }

    @Test
    public void obtenerSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria();
        dtoSubCategoria.setNombre( "Bebida");
        // Se debe tener una Categoria en la BD
        dtoSubCategoria.setCategoria( new DtoCategoria(2));
        DtoSubCategoria paraConsultar = servicio.registrarSubCategoria(
                dtoSubCategoria
        );
        DtoSubCategoria resultado = servicio.obtenerSubCategoria(
                paraConsultar.get_id()
        );
        Assert.assertEquals( resultado.get_id(), paraConsultar.get_id());
    }

    @Test
    public void listarSubCategoriaTest() throws Exception {
        ServicioSubCategoria servicio = new ServicioSubCategoria();
        List<SubCategoria> resultado = servicio.listarSubCategoria();
        Assert.assertNotNull( resultado);
    }
}
