import mercadeoucab.dtos.DtoMarca;
import mercadeoucab.entidades.Marca;
import mercadeoucab.servicio.ServicioMarca;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioMarcaTest {

    @Test
    public void insertarMarcaTest() throws Exception{
        ServicioMarca servicio = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca resultado = servicio.registrarMarca(marca);
        Assert.assertNotEquals(0,resultado.get_id());
    }

    @Test
    public void consultarMarcaTest() throws Exception{
        ServicioMarca servicio = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca consultar = servicio.registrarMarca(marca);
        Marca consultado = servicio.consultarMarca(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
    }

    @Test
    public void  actualizarMarcaTest() throws  Exception{
        ServicioMarca servicio = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca actualizar = servicio.registrarMarca(marca);
        marca.setNombre("No rexona");
        Marca actualizado = servicio.actualizarMarca(actualizar.get_id(), marca);
        Assert.assertNotNull(actualizado.getModificado_el());
    }

    @Test
    public void  eliminarMarcaTest() throws Exception{
        ServicioMarca servicio = new ServicioMarca();
        DtoMarca marca = new DtoMarca();
        marca.setNombre("Rexona");
        Marca eliminar = servicio.registrarMarca(marca);
        Marca eliminada = servicio.eliminarMarca(eliminar.get_id());
        Assert.assertEquals(0, eliminada.getActivo());
    }

    @Test
    public void listarMarcasTest() throws  Exception{
        ServicioMarca servicio = new ServicioMarca();
        List<Marca> marcas = servicio.listarMarcas();
        Assert.assertNotNull(marcas);
    }
}
