package mercadeoucab.servicio;

import mercadeoucab.comandos.Usuario.*;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosUsuario;
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

    private final FabricaComandosUsuario fabricaComandosUsuario = (FabricaComandosUsuario) FabricaComandosAbstractos.getFactory(Comandos.USUARIO);

    /**
     * Metodo para consultar un Usuario dado un identificador
     * @param id Identificador del Usuario que se desea consultar
     * @return regresa el Usuario consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerUsuario(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            ComandoObtenerUsuario comandoObtenerUsuario = (ComandoObtenerUsuario) fabricaComandosUsuario.comandoConsultar();
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
    public Response listarUsuarios(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarUsuarios comandoListarUsuarios = (ComandoListarUsuarios) fabricaComandosUsuario.comandoListar();
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
    public Response registrarUsuario(@HeaderParam("Authorization") String token, DtoUsuario dtoUsuario){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( dtoUsuario);
            ComandoRegistrarUsuario comandoRegistrarUsuario = (ComandoRegistrarUsuario) fabricaComandosUsuario.comandoCrear();
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
    public Response actualizarUsuario(@HeaderParam("Authorization") String token, @PathParam("id") Long id, DtoUsuario dtoUsuario){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            verifyParams( dtoUsuario);
            ComandoActualizarUsuario comandoActualizarUsuario = (ComandoActualizarUsuario) fabricaComandosUsuario.comandoModificar();
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
    public Response eliminarUsuario(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            ComandoEliminarUsuario comandoEliminarUsuario = (ComandoEliminarUsuario) fabricaComandosUsuario.comandoEliminar();
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
            ComandoPeticionClaveOlvidada comandoPeticionClaveOlvidada = (ComandoPeticionClaveOlvidada) fabricaComandosUsuario.comandoPeticionClaveOlvidada();
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
            ComandoCambioClaveOlvidada comandoCambioClaveOlvidada = (ComandoCambioClaveOlvidada) fabricaComandosUsuario.cambioClaveOlvidada();
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
