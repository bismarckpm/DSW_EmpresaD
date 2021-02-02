package mercadeoucab.servicio;

import mercadeoucab.comandos.Pregunta.*;
import mercadeoucab.dtos.DtoPregunta;
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
@Path( "/preguntas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPregunta extends AplicacionBase{

    /**
     * Metodo para listar todas las Preguntas registradas
     * @return regresa la lista de las Preguntas, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarPreguntas(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarPreguntas comandoListarPreguntas = new ComandoListarPreguntas();
            comandoListarPreguntas.execute();
            resultado = comandoListarPreguntas.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Pregunta
     * @param dtoPregunta Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarPregunta(@HeaderParam("Authorization") String token, DtoPregunta dtoPregunta){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(dtoPregunta);
            ComandoRegistrarPregunta comandoRegistrarPregunta = new ComandoRegistrarPregunta();
            comandoRegistrarPregunta.setDtoPregunta(dtoPregunta);
            comandoRegistrarPregunta.execute();
            resultado = comandoRegistrarPregunta.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar una Pregunta dado un identificador
     * @param id Identificador de la Pregunta que se desea consultar
     * @return regresa la Pregunta consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarPregunta(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoConsultarPregunta comandoConsultarPregunta = new ComandoConsultarPregunta();
            comandoConsultarPregunta.setId(id);
            comandoConsultarPregunta.execute();
            resultado = comandoConsultarPregunta.getResult();
        }catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Pregunta dado un identificador
     * @param id Identificador de la Pregunta que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarPregunta(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoEliminarPregunta comandoEliminarPregunta = new ComandoEliminarPregunta();
            comandoEliminarPregunta.setId(id);
            comandoEliminarPregunta.execute();
            resultado = comandoEliminarPregunta.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Pregunta dado un identificador
     * @param id Identificador de la Pregunta que se desea actualizar
     * @param dtoPregunta Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarPregunta(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoPregunta dtoPregunta){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoPregunta);
            ComandoActualizarPregunta comandoActualizarPregunta = new ComandoActualizarPregunta();
            comandoActualizarPregunta.setDtoPregunta(dtoPregunta);
            comandoActualizarPregunta.setId(id);
            comandoActualizarPregunta.execute();
            resultado = comandoActualizarPregunta.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
