package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.RespuestaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseRespuesta;

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
    public Response obtenerRespuesta(@PathParam("id") long id){
        JsonObject respuesta;
        Response resultado = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta resul = dao.find( id, Respuesta.class);
            ResponseRespuesta responseRespuesta = new ResponseRespuesta();
            DtoRespuesta dtoRespuesta = RespuestaMapper.mapEntityToDto( resul);
            if( resul.getActivo() == 1) {
                respuesta = responseRespuesta.generate( dtoRespuesta);
                resultado = ResponseGeneral.Succes( respuesta);
            }else{
                resultado = ResponseGeneral.NoData();
            }
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
    public Response listarRespuesta(){
        JsonArrayBuilder respuestasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoRespuesta dao = new DaoRespuesta();
            List<Respuesta> respuestas = dao.findAll(Respuesta.class);
            ResponseRespuesta responseRespuesta = new ResponseRespuesta();
            for(Respuesta resul: respuestas){
                if( resul.getActivo() == 1){
                    DtoRespuesta dtoRespuesta = RespuestaMapper.mapEntityToDto( resul);
                    JsonObject objeto = responseRespuesta.generate( dtoRespuesta);
                    respuestasList.add(objeto);
                }
            }
            if (respuestas.isEmpty()){
                resultado = ResponseGeneral.NoData();
            }else{
                resultado = ResponseGeneral.Succes( respuestasList);
            }
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
    public Response registrarRespuesta(DtoRespuesta dtoRespuesta){
        Response resultado = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = new Respuesta();
            EncuestaEstudio encuestaEstudio = new EncuestaEstudio(
                    dtoRespuesta.getDtoEncuestaEstudio().get_id()
            );
            respuesta.setEncuesta_estudio( encuestaEstudio);
            Opcion opcion = new Opcion(
                    dtoRespuesta.get_dtoopcion().get_id()
            );
            respuesta.setFk_opcion( opcion);
            Usuario usuario = new Usuario(
                    dtoRespuesta.getDtousuario().get_id()
            );
            respuesta.setFk_usuario( usuario);
            respuesta.setRespuesta( dtoRespuesta.getRespuesta());
            respuesta.setActivo( 1);
            respuesta.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Respuesta resul = dao.insert( respuesta );
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
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
    public Response actualizarRespuesta(@PathParam("id") long id, DtoRespuesta dtoRespuesta){
        Response resultado = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = dao.find( id, Respuesta.class);

            respuesta.setRespuesta(dtoRespuesta.getRespuesta());
            respuesta.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Respuesta resul = dao.update( respuesta );
            resultado = ResponseGeneral.SuccesMessage();
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
    public Response eliminarRespuesta(@PathParam("id") long id){
        Response resultado = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = dao.find( id, Respuesta.class);
            respuesta.setActivo( 0);
            respuesta.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Respuesta resul = dao.update( respuesta);
            resultado = ResponseGeneral.SuccesMessage();
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
    public Response registarEncuestaRespondida(DtoEncuestaEstudio dtoEncuestaEstudio){
        JsonObject data;
        Response resultado = null;
        try {
            DaoRespuesta dao = new DaoRespuesta();
            for(DtoRespuesta dtorespuesta: dtoEncuestaEstudio.getRespuestas()){

                Respuesta respuesta = new Respuesta();
                respuesta.setRespuesta(dtorespuesta.getRespuesta());

                EncuestaEstudio encuestaEstudio = new EncuestaEstudio(dtorespuesta.getDtoEncuestaEstudio().get_id());
                respuesta.setEncuesta_estudio(encuestaEstudio);

                Usuario usuario = new Usuario(dtorespuesta.getDtousuario().get_id());
                respuesta.setFk_usuario(usuario);

                if(dtorespuesta.get_dtoopcion() != null){
                    Opcion opcion = new Opcion(dtorespuesta.get_dtoopcion().get_id());
                    respuesta.setFk_opcion(opcion);
                }
                respuesta.setActivo(1);
                respuesta.setCreado_el(new Date(Calendar
                                                .getInstance()
                                                .getTime()
                                                .getTime()));
                dao.insert(respuesta);

            }
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
