import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.servicio.DirectorioActivo;
import org.junit.Test;

public class ServicioDATest {

    @Test
    public void createUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo("muha30@gmail.com");
        user.setEstado("activo");
        user.setPassword("123");
        DirectorioActivo ldap = new DirectorioActivo("administrador");
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
        user.setCorreo( "b@k.com" );
        DirectorioActivo ldap = new DirectorioActivo("administrador");
        ldap.updateEntry( user,"C@k.com","hola2","bloqueado");
    }

    @Test
    public void getUserLDAP()
    {
        DtoDirectorioAUser user = new DtoDirectorioAUser();
        user.setCorreo( "C@k.com" );
        DirectorioActivo ldap = new DirectorioActivo("administrador");
        ldap.getEntry(user);
    }
}
