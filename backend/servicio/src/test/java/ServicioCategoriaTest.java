import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.servicio.ServicioCategoria;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioCategoriaTest {

    @Test
    public void agregarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("Higiene");
        Categoria resultado = servicio.agregarCategoria(categoria);
        Assert.assertNotEquals(0, resultado.get_id());
    }

    @Test
    public void consultarCategoriaTest() throws  Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("Higiene");
        Categoria consultar = servicio.agregarCategoria(categoria);
        Categoria consultado = servicio.consultarCategoria(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
    }

    @Test
    public void actualizarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("Higiene");
        Categoria actualizar = servicio.agregarCategoria(categoria);
        categoria.setNombre("Nuevo");
        Categoria actualizado = servicio.actualizarCategoria(actualizar.get_id(), categoria);
        Assert.assertNotNull(actualizado.getModificado_el());
    }

    @Test
    public  void eliminarCategoriaTest() throws Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        DtoCategoria categoria = new DtoCategoria();
        categoria.setNombre("Higiene");
        Categoria eliminar = servicio.agregarCategoria(categoria);
        Categoria eliminado = servicio.eliminarCategoria(eliminar.get_id());
        Assert.assertEquals(0, eliminado.getActivo());
    }

    @Test
    public void listarCategoriasTest() throws  Exception{
        ServicioCategoria servicio = new ServicioCategoria();
        List<Categoria> categorias = servicio.listarCategorias();
        Assert.assertNotNull(categorias);
    }
}
