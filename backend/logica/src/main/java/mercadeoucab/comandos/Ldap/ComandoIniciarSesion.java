package mercadeoucab.comandos.Ldap;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.fabricas.FabricasConcretas.FabricaDirectorioAUser;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-02-1
 */
public class ComandoIniciarSesion implements ComandoBase {
    private Response result;
    private DtoDirectorioAUser dtoUsuario;

    @Override
    public void execute() {
        try {
            JsonObject usuarioRegresado;
            FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoUsuario dao = (DaoUsuario) fabricaUsuario.generarDao();
            Usuario usuario = dao.obtenerUsuarioPorCorreo(
                    dtoUsuario.getCorreo()
            );
            FabricaDirectorioAUser fabricaDirectorioActivo = (FabricaDirectorioAUser) FabricaAbstracta.getFactory( Fabricas.DIRECTORIOACTIVO);
            DirectorioActivo ldap = fabricaDirectorioActivo.generarDirectorioActivo( usuario.getRol());
            dtoUsuario.setEstado(
                    usuario.getEstado()
            );

            if ( ldap.userAuthentication( dtoUsuario)){
                //DEBEMOS AGREGAR EL TOKEN EN ESTA RESPUESTA
                ResponseUsuario responseUsuario = (ResponseUsuario) fabricaUsuario.generarResponse();
                DtoUsuario usuarioParaRegresar = UsuarioMapper.mapEntityToDto( usuario);
                usuarioRegresado = responseUsuario.generate( usuarioParaRegresar);
                this.result = ResponseGeneral.Succes( usuarioRegresado);
            }
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("Ocurrio un error al agregar el usuario");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoUsuario(DtoDirectorioAUser dtoUsuario) {
        this.dtoUsuario = dtoUsuario;
    }
}
