package mercadeoucab.servicio;

import mercadeoucab.comandos.Opcion.*;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Antonio Nohra
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/opcion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOpcion extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerOpcion(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoObtenerOpcion comandoObtenerOpcion = new ComandoObtenerOpcion();
            comandoObtenerOpcion.setId(id);
            comandoObtenerOpcion.execute();
            resultado = comandoObtenerOpcion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    @GET
    @Path("/")
    public Response listarOpcion(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarOpcion comandoListarOpcion = new ComandoListarOpcion();
            comandoListarOpcion.execute();
            resultado = comandoListarOpcion.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para crear una opcion
     * @param dtoOpcion Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarOpcion(@HeaderParam("Authorization") String token, DtoOpcion dtoOpcion){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(dtoOpcion);
            ComandoRegistrarOpcion comandoRegistrarOpcion = new ComandoRegistrarOpcion();
            comandoRegistrarOpcion.setDtoOpcion(dtoOpcion);
            comandoRegistrarOpcion.execute();
            resultado = comandoRegistrarOpcion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }


    /**
     * Metodo para actualizar una Opcion dado un identificador
     * @param id Identificador de la Opcion que se desea actualizar
     * @param dtoOpcion Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarOpcion(@HeaderParam("Authorization") String token, @PathParam("id") Long id, DtoOpcion dtoOpcion){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoOpcion);
            ComandoActualizarOpcion comandoActualizarOpcion = new ComandoActualizarOpcion();
            comandoActualizarOpcion.setDtoOpcion(dtoOpcion);
            comandoActualizarOpcion.setId(id);
            comandoActualizarOpcion.execute();
            resultado = comandoActualizarOpcion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Opcion dado un identificador
     * @param id Identificador de la Opcion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarOpcion(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoEliminarOpcion comandoEliminarOpcion = new ComandoEliminarOpcion();
            comandoEliminarOpcion.setId(id);
            comandoEliminarOpcion.execute();
            resultado = comandoEliminarOpcion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
