package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoMail;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mail.Mail;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/usuarios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioUsuario extends AplicacionBase{

    /**
     * Metodo para consultar un Usuario dado un identificador
     * @param id Identificador del Usuario que se desea consultar
     * @return regresa el Usuario consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerUsuario(@PathParam("id") Long id){
        JsonObject usuario;
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario resul = dao.find( id, Usuario.class);
            ResponseUsuario responseUsuario = new ResponseUsuario();
            DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto( resul);
            if (resul.getActivo()!= 0) {
                usuario = responseUsuario.generate( dtoUsuario);
                resultado = ResponseGeneral.Succes( usuario);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todos los Usuarios registrados
     * @return regresa la lista de los Usuarios, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarUsuarios(){
        JsonArrayBuilder usuarios = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoUsuario dao = new DaoUsuario();
            List<Usuario> usuariosObtenidos = dao.findAll(Usuario.class);
            ResponseUsuario responseUsuario = new ResponseUsuario();
            if ( !usuariosObtenidos.isEmpty()) {
                for (Usuario usuario : usuariosObtenidos) {
                    if (usuario.getActivo() != 0) {
                        DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto(usuario);
                        JsonObject objeto = responseUsuario.generate(dtoUsuario);
                        usuarios.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( usuarios);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear un Usuario
     * @param dtoUsuario Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarUsuario(DtoUsuario dtoUsuario){
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = new Usuario();
            usuario.setNombre( dtoUsuario.getNombre());
            usuario.setApellido( dtoUsuario.getApellido());
            usuario.setEstado( dtoUsuario.getEstado());
            usuario.setRol( dtoUsuario.getRol());
            usuario.setCorreo( dtoUsuario.getCorreo());
            usuario.setActivo( 1);
            usuario.setCreado_el( new Date(Calendar.getInstance().getTime().getTime()));
            Usuario resul = dao.insert( usuario);
            // Agregar al directorio activo
            DirectorioActivo ldap = new DirectorioActivo( dtoUsuario.getRol());
            if (dtoUsuario.getPassword() != null){
                DtoDirectorioAUser paraInsertar = new DtoDirectorioAUser(
                        dtoUsuario.getCorreo(),
                        dtoUsuario.getEstado(),
                        dtoUsuario.getPassword()
                );
                ldap.addEntryToLdap( paraInsertar);
                resultado = ResponseGeneral.SuccesCreate( resul.get_id());
            }else{
                resultado = ResponseGeneral.Failure("Error debe enviar una contrasena");
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Usuario dado un identificador
     * @param id Identificador del Usuario que se desea actualizar
     * @param dtoUsuario Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarUsuario( @PathParam("id") Long id, DtoUsuario dtoUsuario){
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find( id, Usuario.class);
            usuario.setNombre( dtoUsuario.getNombre());
            usuario.setApellido( dtoUsuario.getApellido());
            usuario.setEstado( dtoUsuario.getEstado());
            usuario.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Usuario resul = dao.update( usuario);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Usuario dado un identificador
     * @param id Identificador del Usuario que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarUsuario( @PathParam("id") Long id){
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find( id, Usuario.class);
            usuario.setActivo(0);
            usuario.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Usuario resul = dao.update( usuario);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para realizar una peticio al sistema de cambiar la clave
     * @param dtoUsuario Objeto del Usuario que desea realizar una peticion
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @POST
    @Path("/peticionClaveOlvidada")
    public Response peticionClaveOlvidada (DtoUsuario dtoUsuario){
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.obtenerUsuarioPorCorreo(
                    dtoUsuario.getCorreo()
            );
            if ( usuario == null){
                resultado = ResponseGeneral.Failure("Usuario no se encuentra registrado");
            }else {
                Mail enviarCorreo = new Mail();
                DtoMail dtoMail = new DtoMail();
                dtoMail.emailResetearContrasena(
                        "http://localhost:4200/change-password?correo="+ usuario.getCorreo()
                );
                enviarCorreo.enviarCorreo(
                        usuario.getCorreo(),
                        dtoMail.getMensaje(),
                        dtoMail.getAsunto()
                );
                resultado = ResponseGeneral.SuccesMessage();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para cambiar la clave de un Usuario
     * @param dtoDirectorioAUser Objeto que se desea cambiar la clave
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @POST
    @Path("/cambioClaveOlvidada")
    public Response cambioClaveOlvidada (DtoDirectorioAUser dtoDirectorioAUser){
        Response resultado = null;
        try{
            //Agregar seguridad aca con el token en la segunda entrega
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.obtenerUsuarioPorCorreo( dtoDirectorioAUser.getCorreo());
            DirectorioActivo ldap = new DirectorioActivo( usuario.getRol());
            dtoDirectorioAUser.setEstado(usuario.getEstado());
            ldap.updateEntry(
                    dtoDirectorioAUser,
                    null,
                    dtoDirectorioAUser.getPassword(),
                    null
            );
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
