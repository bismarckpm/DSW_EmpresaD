import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.servicio.ServicioUsuario;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class ServicioUsuarioTest {

    @Test
    public void registrarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setNombre( "Yeferson");
        dtoUsuario.setApellido( "Soteldo");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "soteldios10@gmail.com");
        dtoUsuario.setPassword("54321");
        Response resultado = servicio.registrarUsuario( dtoUsuario);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void actualizarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.set_id( 1);
        dtoUsuario.setNombre( "Yangel");
        dtoUsuario.setApellido( "Herrera");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "sanYangel@gmail.com");
        Response resultado = servicio.actualizarUsuario(
                dtoUsuario.get_id(),
                dtoUsuario
        );
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        Response resultado = servicio.eliminarUsuario( (long)1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void obtenerUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        Response resultado = servicio.obtenerUsuario( (long)1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        Response resultado = servicio.listarUsuarios();
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
