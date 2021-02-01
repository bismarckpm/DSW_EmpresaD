package mercadeoucab.comandos.Usuario;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoCambioClaveOlvidada implements ComandoBase {

    private Response result;
    private DtoDirectorioAUser dtoDirectorioAUser;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            //Agregar seguridad aca con el token en la segunda entrega
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoUsuario dao = (DaoUsuario) fabrica.generarDao();
            Usuario usuario = dao.obtenerUsuarioPorCorreo( dtoDirectorioAUser.getCorreo());
            DirectorioActivo ldap = new DirectorioActivo( usuario.getRol());
            dtoDirectorioAUser.setEstado( usuario.getEstado());
            ldap.updateEntry(
                    dtoDirectorioAUser,
                    null,
                    dtoDirectorioAUser.getPassword(),
                    null
            );
            this.result = ResponseGeneral.SuccesMessage();
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error");
        }
    }

    @Override
    public Response getResult() {
        return null;
    }

    public void setDtoDirectorioAUser(DtoDirectorioAUser dtoDirectorioAUser) {
        this.dtoDirectorioAUser = dtoDirectorioAUser;
    }
}
