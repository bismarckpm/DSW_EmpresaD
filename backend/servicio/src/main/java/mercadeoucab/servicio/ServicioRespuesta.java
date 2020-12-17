package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;

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
            respuesta = Json.createObjectBuilder()
                            .add("_id", resul.get_id())
                            .add("respuesta", resul.getRespuesta())
                            .add("usuario", Json.createObjectBuilder()
                                                    .add("_id",resul.getFk_usuario().get_id())
                                                    .add("nombre",resul.getFk_usuario().getNombre())
                                                    .add("apellido",resul.getFk_usuario().getApellido())
                                                    .add("correo",resul.getFk_usuario().getCorreo()))
                            .add("Pregunta", Json.createObjectBuilder()
                                                    .add("_id",resul.getEncuesta_estudio().getFk_pregunta().get_id())
                                                    .add("pregunta",resul.getEncuesta_estudio().getFk_pregunta().getNombrePregunta()))
                            .add("estudio",Json.createObjectBuilder()
                                                  .add("_id",resul.getEncuesta_estudio().getFk_estudio().get_id()))
                            .add("opcion", Json.createObjectBuilder()
                                                  .add("_id", resul.getFk_opcion().get_id())
                                                  .add("nombre",resul.getFk_opcion().getNombre_opcion()))
                            .build();
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", respuesta)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
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
            for(Respuesta resul: respuestas){
                if(resul.getActivo() == 1){
                    JsonObject objeto = Json.createObjectBuilder()
                                            .add("_id", resul.get_id())
                                            .add("respuesta", resul.getRespuesta())
                                            .add("usuario", Json.createObjectBuilder()
                                                    .add("_id",resul.getFk_usuario().get_id())
                                                    .add("nombre",resul.getFk_usuario().getNombre())
                                                    .add("apellido",resul.getFk_usuario().getApellido())
                                                    .add("correo",resul.getFk_usuario().getCorreo()))
                                            .add("Pregunta", Json.createObjectBuilder()
                                                    .add("_id",resul.getEncuesta_estudio().getFk_pregunta().get_id())
                                                    .add("pregunta",resul.getEncuesta_estudio().getFk_pregunta().getNombrePregunta()))
                                            .add("estudio",Json.createObjectBuilder()
                                                    .add("_id",resul.getEncuesta_estudio().getFk_estudio().get_id()))
                                            .add("opcion", Json.createObjectBuilder()
                                                    .add("_id", resul.getFk_opcion().get_id())
                                                    .add("nombre",resul.getFk_opcion().getNombre_opcion()))
                                            .build();
                    respuestasList.add(objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", respuestasList)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("mensaje",problema)
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
                    .add("mensaje",problema)
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
                    .add("problema", problema)
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
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
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
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

}
