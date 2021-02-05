package mercadeoucab.servicio;

import mercadeoucab.comandos.Tipo.*;
import mercadeoucab.dtos.DtoTipo;
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
@Path( "/tipos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTipo extends AplicacionBase {

    /**
     * Metodo para listar todos los Tipos registrados
     * @return regresa la lista de los Tipos, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarTipos(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarTipos comandoListarTipos = new ComandoListarTipos();
            comandoListarTipos.execute();
            resultado = comandoListarTipos.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar un Tipo dado un identificador
     * @param id Identificador del Tipo que se desea consultar
     * @return regresa el Tipo consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerTipo(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoObtenerTipo comandoObtenerTipo = new ComandoObtenerTipo();
            comandoObtenerTipo.setId(id);
            comandoObtenerTipo.execute();
            resultado = comandoObtenerTipo.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para crear un Tipo
     * @param dtoTipo Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarTipo(@HeaderParam("Authorization") String token, DtoTipo dtoTipo){
        Response resultado = null;
        try
        {
            validateToken(token);
            verifyParams(dtoTipo);
            ComandoRegistrarTipo comandoRegistrarTipo = new ComandoRegistrarTipo();
            comandoRegistrarTipo.setDtoTipo(dtoTipo);
            comandoRegistrarTipo.execute();
            resultado = comandoRegistrarTipo.getResult();
        }
        catch (Exception e)
        {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Tipo dado un identificador
     * @param id Identificador del Tipo que se desea actualizar
     * @param dtoTipo Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarTipo(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoTipo dtoTipo){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoTipo);
            ComandoActualizarTipo comandoActualizarTipo = new ComandoActualizarTipo();
            comandoActualizarTipo.setId(id);
            comandoActualizarTipo.setDtoTipo(dtoTipo);
            comandoActualizarTipo.execute();
            resultado = comandoActualizarTipo.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Tipo dado un identificador
     * @param id Identificador del Tipo que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarTipo(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoEliminarTipo comandoEliminarTipo = new ComandoEliminarTipo();
            comandoEliminarTipo.setId(id);
            comandoEliminarTipo.execute();
            resultado = comandoEliminarTipo.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
