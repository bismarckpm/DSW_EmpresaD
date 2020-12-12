import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.servicio.ServicioLDAP;
import mercadeoucab.servicio.ServicioUsuario;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ServicioLDAPTest {

    @Test
    public void loginTest() throws Exception{
        ServicioLDAP servicioLDAP = new ServicioLDAP();
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario paraProbar = new DtoUsuario();
        paraProbar.setNombre( "Wuilker");
        paraProbar.setApellido( "Farinez");
        paraProbar.setEstado( "activo");
        paraProbar.setRol( "encuestado");
        paraProbar.setCorreo( "777777@gmail.com");
        servicioUsuario.registrarUsuario( paraProbar);
        DirectorioActivo ldap = new DirectorioActivo( paraProbar.getRol());
        DtoDirectorioAUser usuario = new DtoDirectorioAUser(
                paraProbar.getCorreo(),
                paraProbar.getEstado(),
                "12345"
        );
        ldap.addEntryToLdap( usuario);
        DtoDirectorioAUser usuario2 = new DtoDirectorioAUser();
        usuario2.setCorreo(paraProbar.getCorreo());
        usuario2.setPassword("12345");
        Response respuesta = servicioLDAP.login( usuario2);
        Assert.assertEquals(Response.Status.OK.getStatusCode(),respuesta.getStatus());
    }
}
