import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioLDAP;
import mercadeoucab.servicio.ServicioUsuario;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioLDAPTest {

    /*
    @Test
    public void loginTest() throws Exception{
        ServicioLDAP servicioLDAP = new ServicioLDAP();
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        //DtoUsuario paraProbar = new DtoUsuario();
        //paraProbar.setNombre( "Wuilker");
        //paraProbar.setApellido( "Farinez");
        //paraProbar.setEstado( "activo");
        //paraProbar.setRol( "encuestado");
        //paraProbar.setCorreo( "777777@gmail.com");
        //servicioUsuario.registrarUsuario( paraProbar);
        //DirectorioActivo ldap = new DirectorioActivo( "administrador");
        //DtoDirectorioAUser usuario = new DtoDirectorioAUser(
        //        "atag102@gmail.com",
        //        "activo",
        //        "123"
        //);
        //ldap.addEntryToLdap( usuario);
        DtoDirectorioAUser usuario2 = new DtoDirectorioAUser();
        usuario2.setCorreo("atag102@gmail.com");
        usuario2.setPassword("123");
        Response respuesta = servicioLDAP.login( usuario2);
        Assert.assertEquals(Response.Status.OK.getStatusCode(),respuesta.getStatus());
    }
     */
}
