import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.directorioactivo.DirectorioActivo;
import org.junit.Assert;
import org.junit.Test;

public class ServicioDATest {

    @Test
    public void createUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo("CM10@gmail.com");
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
        user.setCorreo( "pasaCono@gmail.com" );
        user.setPassword( "cambie" );
        user.setEstado("activo");
        DirectorioActivo ldap = new DirectorioActivo("administrador");
        ldap.userAuthentication( user);
        Assert.assertTrue( ldap.userAuthentication( user ));
    }

}
