import mercadeoucab.dtos.*;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Hijo;
import mercadeoucab.servicio.ServicioDatoEncuestado;
import mercadeoucab.servicio.ServicioHijo;
import mercadeoucab.servicio.ServicioUsuario;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class ServicioHijoTest {

    @Test
    public void registrarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        DtoHijo dtoHijo = new DtoHijo("masculino", Date.valueOf("1997-02-28"), new DtoDatoEncuestado(1));
        Hijo resultado = servicio.registrarHijo(dtoHijo);
        Assert.assertNotEquals(0, resultado.get_id());
    }

    @Test
    public void consultarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        Hijo consultado = servicio.consultarHijo(1);
        Assert.assertEquals(1, consultado.get_id());
    }

    @Test
    public void listarHijosTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        List<Hijo> hijos = servicio.listarHijos();
        Assert.assertNotNull(hijos);
    }

    @Test
    public void eliminarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        Hijo eliminado = servicio.eliminarHijo(1);
        Assert.assertEquals(1, eliminado.getActivo());
    }

    @Test
    public void actualizarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        DtoHijo dtoHijo = new DtoHijo("masculino", Date.valueOf("2015-02-28"), new DtoDatoEncuestado(1));
        Hijo actualizado = servicio.actuaizarHijo(1, dtoHijo);
        Assert.assertNotNull(actualizado.getModificado_el());
    }
}
