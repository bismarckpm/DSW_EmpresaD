package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/LDAP" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioLDAP extends AplicacionBase {

    /**
     * Metodo para iniciar sesion en el sistema
     * @param dtoUsuario usuario que desea iniciar sesion
     * @return regresa el objeto del usuario o mensaje de error
     */
    @POST
    @Path("/login")
    public Response login(DtoDirectorioAUser dtoUsuario){
        JsonObject usuarioRegresado;
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.obtenerUsuarioPorCorreo(
                    dtoUsuario.getCorreo()
            );
            DirectorioActivo ldap = new DirectorioActivo(
                    usuario.getRol()
            );
            dtoUsuario.setEstado(
                    usuario.getEstado()
            );

            if ( ldap.userAuthentication( dtoUsuario)){
                //DEBEMOS AGREGAR EL TOKEN EN ESTA RESPUESTA
                ResponseUsuario responseUsuario = new ResponseUsuario();
                DtoUsuario usuarioParaRegresar = UsuarioMapper.mapEntityToDto( usuario);
                usuarioRegresado = responseUsuario.generate( usuarioParaRegresar);
                resultado = ResponseGeneral.Succes( usuarioRegresado);
            }

        }catch (Exception e){
            e.printStackTrace();
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
