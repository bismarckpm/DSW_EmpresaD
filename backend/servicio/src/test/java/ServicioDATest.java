import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.directorioactivo.DirectorioActivo;
import org.junit.Assert;
import org.junit.Test;

public class ServicioDATest {

    @Test
    public void createUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo("aaaa@gmail.com");
        user.setEstado("activo");
        user.setPassword("123");
        DirectorioActivo ldap = new DirectorioActivo("encuestado");
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
        user.setCorreo( "test@gmail.com" );
        DirectorioActivo ldap = new DirectorioActivo("administrador");
        ldap.updateEntry( user,null,"cambie3",null);
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
        user.setCorreo( "atag102@gmail.com" );
        user.setPassword( "123" );
        user.setEstado("activo");
        DirectorioActivo ldap = new DirectorioActivo("encuestado");
        ldap.userAuthentication( user);
        Assert.assertTrue( ldap.userAuthentication( user ));
    }

}
