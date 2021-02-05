import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.servicio.ServicioCategoria;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioCategoriaTest {

    @Test
    public void agregarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("Higiene");
        Response resultado = servicio.agregarCategoria("",categoria);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public void consultarCategoriaTest() throws  Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        Response resultado = servicio.consultarCategoria("",1);
        Assert.assertEquals(resultado.getStatus(), 200);
    }


    @Test
    public void actualizarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("higiene");
        Response resultado = servicio.actualizarCategoria("",1, categoria);
        Assert.assertEquals(resultado.getStatus(), 200);
    }

    @Test
    public  void eliminarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        Response eliminado = servicio.eliminarCategoria("",1);
        Assert.assertEquals(eliminado.getStatus(), 200);
    }

    @Test
    public void listarCategoriasTest() throws  Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        Response categorias = servicio.listarCategorias("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjEyMjIwNTA3LCJzdWIiOiJtYWlsQG1haWwuY29tIiwiaXNzIjoibWVyY2FkZW9VY2FiIiwiZXhwIjoxNjEyMjMxMzA3fQ.fjfg4aIEobQ_p3jOgfB9KXvF-LaNetJQ7HQjmizKxvE");
        Assert.assertEquals(categorias.getStatus(), 200);
    }
}
