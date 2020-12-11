import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.directorioactivo.DirectorioActivo;
import org.junit.Assert;
import org.junit.Test;

public class ServicioDATest {

    @Test
    public void createUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo("muha31@gmail.com");
        user.setEstado("activo");
        user.setPassword("123");
        DirectorioActivo ldap = new DirectorioActivo("cliente");
        ldap.addEntryToLdap( user );
    }

    @Test
    public void deleteUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo( "muha30@gmail.com" );
        DirectorioActivo ldap = new DirectorioActivo("administrador");
        ldap.deleteEntry( user );
    }

    @Test
    public void updateUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo( "muha30@gmail.com" );
        DirectorioActivo ldap = new DirectorioActivo("cliente");
        ldap.updateEntry( user,"C@k.com","123","bloqueado");
    }

    @Test
    public void getUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo( "C@k.com" );
        DirectorioActivo ldap = new DirectorioActivo("administrador");
        ldap.getEntry(user);
    }

    @Test
    public void userAuthentication()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo( "prueba@gmail.com" );
        user.setPassword( "12345" );
        user.setEstado("activo");
        DirectorioActivo ldap = new DirectorioActivo("encuestado");
        ldap.addEntryToLdap( user);
        Assert.assertTrue( ldap.userAuthentication( user ));
    }

}
