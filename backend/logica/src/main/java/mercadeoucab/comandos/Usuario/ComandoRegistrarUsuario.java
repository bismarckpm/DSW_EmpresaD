package mercadeoucab.comandos.Usuario;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoRegistrarUsuario implements ComandoBase {
    private Response result;
    private DtoUsuario dtoUsuario;

    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoUsuario dao = (DaoUsuario) fabrica.generarDao();
            Usuario usuario = UsuarioMapper.mapDtoToEntity( this.dtoUsuario);
            usuario.setActivo( 1);
            usuario.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Usuario resul = dao.insert(usuario);
            // Agregar al directorio activo
            DirectorioActivo ldap = new DirectorioActivo(dtoUsuario.getRol());
            if (dtoUsuario.getPassword() != null) {
                DtoDirectorioAUser paraInsertar = new DtoDirectorioAUser(
                        dtoUsuario.getCorreo(),
                        dtoUsuario.getEstado(),
                        dtoUsuario.getPassword()
                );
                ldap.addEntryToLdap(paraInsertar);
            }
            this.result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("Ocurrio un error al agregar el usuario");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoUsuario(DtoUsuario dtoUsuario) {
        this.dtoUsuario = dtoUsuario;
    }
}
