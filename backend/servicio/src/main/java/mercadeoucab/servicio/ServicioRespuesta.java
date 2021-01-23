package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.RespuestaMapper;
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

@Path( "/respuestas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioRespuesta extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerRespuesta(@PathParam("id") long id){
        JsonObject data;
        JsonObject respuesta;
        Response resultado = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta resul = dao.find( id, Respuesta.class);
            ResponseRespuesta responseRespuesta = new ResponseRespuesta();
            DtoRespuesta dtoRespuesta = RespuestaMapper.mapEntityToDto( resul);
            if( resul.getActivo() == 1) {
                respuesta = responseRespuesta.generate( dtoRespuesta);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", respuesta)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Respuesta no se encuentra activa")
                        .build();
            }
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public Response listarRespuesta(){
        JsonObject data;
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
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "No se poseen respuestas registradas")
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", respuestasList)
                        .build();
            }


            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();
        }
        return resultado;
    }

    @POST
    @Path("/")
    public Response registrarRespuesta(DtoRespuesta dtoRespuesta){
        JsonObject data;
        Response resultado = null;
        try{

            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = new Respuesta();

            EncuestaEstudio encuestaEstudio =new EncuestaEstudio(dtoRespuesta.getDtoEncuestaEstudio().get_id());
            respuesta.setEncuesta_estudio(encuestaEstudio);

            Opcion opcion = new Opcion(dtoRespuesta.get_dtoopcion().get_id());
            respuesta.setFk_opcion(opcion);

            Usuario usuario = new Usuario(dtoRespuesta.getDtousuario().get_id());
            respuesta.setFk_usuario(usuario);

            respuesta.setRespuesta(dtoRespuesta.getRespuesta());
            respuesta.setActivo(1);
            respuesta.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));

            Respuesta resul = dao.insert( respuesta );

            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","respuesta creada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK).entity(data).build();

        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(data)
                                .build();
        }
        return resultado;
    }


    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public Response actualizarRespuesta(@PathParam("id") long id, DtoRespuesta dtoRespuesta){
        JsonObject data;
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
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Respuesta actualizada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public Response eliminarRespuesta(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = dao.find( id, Respuesta.class);
            respuesta.setActivo( 0);
            respuesta.setModificado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            Respuesta resul = dao.update( respuesta );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Respuesta eliminada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e)
        {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

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
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message","Respuestas registradas con exito, gracias por su tiempo")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

}
