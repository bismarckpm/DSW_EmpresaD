import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioUsuario;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioUsuarioTest {

    @Test
    public void registrarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setNombre( "Black");
        dtoUsuario.setApellido( "Jack");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "BlackJaack@gmail.com");
        dtoUsuario.setPassword("123");
        Response resultado = servicio.registrarUsuario( "",dtoUsuario);
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
        dtoUsuario.setNombre( "Andres");
        dtoUsuario.setApellido( "Aranguiz");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "atag102@gmail.com");
        Response resultado = servicio.actualizarUsuario("",
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
        Response resultado = servicio.eliminarUsuario( "",(long)1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void obtenerUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        Response resultado = servicio.obtenerUsuario( "",(long)1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        Response resultado = servicio.listarUsuarios("");
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void peticionClaveOlvidadaTest() throws Exception{
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setCorreo("mail@mail.com");
        Response resultado = servicio.peticionClaveOlvidada( dtoUsuario);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void cambioClaveOlvidadaTest() throws Exception{
        ServicioUsuario servicio = new ServicioUsuario();
        DtoDirectorioAUser dtoUsuario = new DtoDirectorioAUser();
        dtoUsuario.setCorreo("aaaa@gmail.com");
        dtoUsuario.setPassword("123");
        Response resultado = servicio.cambioClaveOlvidada( dtoUsuario);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }
}
