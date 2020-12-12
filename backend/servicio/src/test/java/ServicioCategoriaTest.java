import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.servicio.ServicioCategoria;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioCategoriaTest {

    @Test
    public void agregarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("Higiene");
        Response resultado = servicio.agregarCategoria(categoria);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void consultarCategoriaTest() throws  Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        Response resultado = servicio.consultarCategoria(1);
        Assert.assertEquals(resultado.getStatus(), 200);
    }


    @Test
    public void actualizarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("Nuevo");
        Response resultado = servicio.actualizarCategoria(1, categoria);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public  void eliminarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        Response eliminado = servicio.eliminarCategoria(1);
        Assert.assertEquals(eliminado.getStatus(), 200);
    }

    @Test
    public void listarCategoriasTest() throws  Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        Response categorias = servicio.listarCategorias();
        Assert.assertEquals(categorias.getStatus(), 200);
    }
}
