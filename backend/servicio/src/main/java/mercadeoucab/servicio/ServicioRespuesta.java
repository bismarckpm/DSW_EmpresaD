package mercadeoucab.servicio;

import mercadeoucab.comandos.respuesta.*;
import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
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
@Path( "/respuestas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioRespuesta extends AplicacionBase{

    /**
     * Metodo para consultar una Respuesta dado un identificador
     * @param id Identificador de la Respuesta que se desea consultar
     * @return regresa la Respuesta consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerRespuesta(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoObtenerRespuesta comandoObtenerRespuesta = new ComandoObtenerRespuesta();
            comandoObtenerRespuesta.setId(id);
            comandoObtenerRespuesta.execute();
            resultado = comandoObtenerRespuesta.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las Respuestas registradas
     * @return regresa la lista de las Respuestas, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarRespuesta(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarRespuesta comandoListarRespuesta = new ComandoListarRespuesta();
            comandoListarRespuesta.execute();
            resultado = comandoListarRespuesta.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para crear una Respuesta
     * @param dtoRespuesta Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarRespuesta(@HeaderParam("Authorization") String token, DtoRespuesta dtoRespuesta){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(dtoRespuesta);
            ComandoRegistrarRespuesta comandoRegistrarRespuesta = new ComandoRegistrarRespuesta();
            comandoRegistrarRespuesta.setDtoRespuesta(dtoRespuesta);
            comandoRegistrarRespuesta.execute();
            resultado = comandoRegistrarRespuesta.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Respuesta dado un identificador
     * @param id Identificador de la Respuesta que se desea actualizar
     * @param dtoRespuesta Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarRespuesta(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoRespuesta dtoRespuesta){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoRespuesta);
            ComandoActualizarRespuesta comandoActualizarRespuesta = new ComandoActualizarRespuesta();
            comandoActualizarRespuesta.setId(id);
            comandoActualizarRespuesta.setDtoRespuesta(dtoRespuesta);
            comandoActualizarRespuesta.execute();
            resultado = comandoActualizarRespuesta.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Respuesta dado un identificador
     * @param id Identificador de la Respuesta que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarRespuesta(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoEliminarRespuesta comandoEliminarRespuesta = new ComandoEliminarRespuesta();
            comandoEliminarRespuesta.setId(id);
            comandoEliminarRespuesta.execute();
            resultado = comandoEliminarRespuesta.getResult();
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
     * Metodo para registrar una Encuesta respondida
     * @param dtoEncuestaEstudio Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/encuesta")
    public Response registarEncuestaRespondida(@HeaderParam("Authorization") String token, DtoEncuestaEstudio dtoEncuestaEstudio){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(dtoEncuestaEstudio);
            ComandoRegistarEncuestaRespondida comandoRegistarEncuestaRespondida = new ComandoRegistarEncuestaRespondida();
            comandoRegistarEncuestaRespondida.setDtoEncuestaEstudio(dtoEncuestaEstudio);
            comandoRegistarEncuestaRespondida.execute();
            resultado = comandoRegistarEncuestaRespondida.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
