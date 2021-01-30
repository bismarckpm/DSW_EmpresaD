package mercadeoucab.servicio;

import mercadeoucab.comandos.usuario.*;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
        Response resultado = null;
        try{
            verifyParams( id);
            ComandoObtenerUsuario comandoObtenerUsuario = new ComandoObtenerUsuario();
            comandoObtenerUsuario.setId( id);
            comandoObtenerUsuario.execute();
            resultado = comandoObtenerUsuario.getResult();
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
        Response resultado = null;
        try {
            ComandoListarUsuarios comandoListarUsuarios = new ComandoListarUsuarios();
            comandoListarUsuarios.execute();
            resultado = comandoListarUsuarios.getResult();
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
            verifyParams( dtoUsuario);
            ComandoRegistrarUsuario comandoRegistrarUsuario = new ComandoRegistrarUsuario();
            comandoRegistrarUsuario.setDtoUsuario( dtoUsuario);
            comandoRegistrarUsuario.execute();
            resultado = comandoRegistrarUsuario.getResult();
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
            verifyParams( id);
            verifyParams( dtoUsuario);
            ComandoActualizarUsuario comandoActualizarUsuario = new ComandoActualizarUsuario();
            comandoActualizarUsuario.setDtoUsuario( dtoUsuario);
            comandoActualizarUsuario.setId( id);
            comandoActualizarUsuario.execute();
            resultado = comandoActualizarUsuario.getResult();
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
            verifyParams( id);
            ComandoEliminarUsuario comandoEliminarUsuario = new ComandoEliminarUsuario();
            comandoEliminarUsuario.setId( id);
            comandoEliminarUsuario.execute();
            resultado = comandoEliminarUsuario.getResult();
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
            verifyParams( dtoUsuario);
            ComandoPeticionClaveOlvidada comandoPeticionClaveOlvidada = new ComandoPeticionClaveOlvidada();
            comandoPeticionClaveOlvidada.setDtoUsuario( dtoUsuario);
            comandoPeticionClaveOlvidada.execute();
            resultado = comandoPeticionClaveOlvidada.getResult();
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
            verifyParams( dtoDirectorioAUser);
            ComandoCambioClaveOlvidada comandoCambioClaveOlvidada = new ComandoCambioClaveOlvidada();
            comandoCambioClaveOlvidada.setDtoDirectorioAUser( dtoDirectorioAUser);
            comandoCambioClaveOlvidada.execute();
            resultado = comandoCambioClaveOlvidada.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
