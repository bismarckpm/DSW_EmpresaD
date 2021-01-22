package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseEstudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Path( "/estudios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio {

    @GET
    @Path("/")
    public Response listarEstudios(){
        JsonObject data;
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.findAll(Estudio.class);

            for(Estudio estudio: estudios){

                if(estudio.getActivo() == 1){
                    ResponseEstudio responseEstudio = new ResponseEstudio();
                    DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto( estudio);
                    JsonObject agregar = responseEstudio.generate( dtoEstudio);
                    estudiosList.add(agregar);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", estudiosList)
                    .build();
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
    public Response agregarEstudio(DtoEstudio dtoEstudio){
        JsonObject data;
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = new Estudio();
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEncuestasEsperadas(dtoEstudio.getEncuestasEsperadas());
            estudio.setActivo(1);
            estudio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Solicitud solicitud = new Solicitud(dtoEstudio.getSolicitud().get_id());
            estudio.setSolicitud( solicitud );
            Usuario usuario = new Usuario(dtoEstudio.getFk_usuario().get_id());
            estudio.setFk_usuario( usuario );
            MuestraPoblacion muestraPoblacion = new MuestraPoblacion(dtoEstudio.getFk_muestra_poblacion().get_id());
            estudio.setFk_muestra_poblacion( muestraPoblacion );

            for(DtoPregunta pregunta: dtoEstudio.getPreguntas()){
                Pregunta pregunta1 = new Pregunta(pregunta.get_id());
                estudio.addpregunta(pregunta1);
            }

            Estudio resul = dao.insert(estudio);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Estudio creada con exito")
                    .build();
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

    @GET
    @Path("/{id}")
    public Response consultarEstudio(@PathParam("id") long id){
        JsonObject data;
        JsonObject estudioJson;
        Response resultado = null;
        try {

            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            if ( Objects.nonNull( estudio) && estudio.getActivo() == 1){
                ResponseEstudio responseEstudio = new ResponseEstudio();
                DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto( estudio);
                estudioJson = responseEstudio.generate( dtoEstudio);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", estudioJson)
                        .build();
            }else {
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "No se ha encontrado ningun estudio")
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
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
            System.out.print(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarEstudio(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setActivo(0);
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.update( estudio );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Estudio eliminado con exito")
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
        return  resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio){
        JsonObject data;
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEncuestasEsperadas(dtoEstudio.getEncuestasEsperadas());
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.update(estudio);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "estudio actualizado con exito")
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

    @GET
    @Path("/{id}/usuarios_respondieron")
    public Response usuariosRespondieronEncuesta(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoUsuario daoUsuario = new DaoUsuario();
            List<Long> ids = daoRespuesta.usuariosRespondidoEncuesta(daoEstudio.find(id, Estudio.class));
            if(!(ids.isEmpty())){
                for( int i = 0; i < ids.size(); i++){

                    Usuario add = daoUsuario.find(ids.get(i),Usuario.class);

                    JsonObject agregar = Json.createObjectBuilder()
                                             .add("_id", add.get_id())
                                             .add("nombre", add.getNombre())
                                             .add("apellido", add.getApellido())
                                             .add("rol", add.getRol())
                                             .add("estado", add.getEstado())
                                             .add("correo", add.getCorreo())
                                             .build();
                    usuariosList.add(agregar);
                }
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", usuariosList)
                        .build();
            }
            else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Actualmente ningun usuario ha respondido a esta encuesta")
                        .build();
            }

            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
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
    @Path("/{id}/usuarios_aplican")
    public Response usuariosAplicanEncuesta(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            List<Usuario> usuarios = daoEstudio.personasAplicanEstudio(daoEstudio.find(id, Estudio.class));
            if (!(usuarios.isEmpty())){
                for(Usuario usuario: usuarios){
                    if(usuario.getActivo() == 1) {
                        JsonObject agregar = Json.createObjectBuilder()
                                .add("_id", usuario.get_id())
                                .add("nombre", usuario.getNombre())
                                .add("apellido", usuario.getApellido())
                                .add("rol", usuario.getRol())
                                .add("estado", usuario.getEstado())
                                .add("correo", usuario.getCorreo())
                                .build();
                        usuariosList.add(agregar);
                    }
                }
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", usuariosList)
                        .build();
            }
            else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Actualmente ningun usuario califica a esta encuesta")
                        .build();
            }
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
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
