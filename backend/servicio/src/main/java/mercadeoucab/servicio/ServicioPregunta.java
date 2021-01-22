package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponsePregunta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/preguntas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPregunta extends AplicacionBase{

    @GET
    @Path("/")
    public Response listarPreguntas(){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            List<Pregunta> preguntas = dao.findAll(Pregunta.class);
            JsonArrayBuilder preguntaslist = Json.createArrayBuilder();

            if(!(preguntas.isEmpty())) {
                for (Pregunta pregunta : preguntas) {

                    if(pregunta.getActivo() == 1) {
                        String tipo = pregunta.getTipo();
                        JsonObject objeto = null;
                        ResponsePregunta responsePregunta = new ResponsePregunta();
                        DtoPregunta dtoPregunta = PreguntaMapper.mapEntityToDto( pregunta);
                        switch (tipo) {
                            case "abierta":
                            case "boolean":
                                objeto = responsePregunta.generate( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;

                            case "multiple":
                            case "simple":
                                objeto = responsePregunta.generateWithOptions( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;
                            case "rango":
                                objeto =responsePregunta.generateWithRango( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;
                        }//final switch
                    }
                }//Final for

                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", preguntaslist)
                        .build();
                resultado = Response.status(Response.Status.OK)
                        .entity(data)
                        .build();
            }//final if

            else {
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "No posee preguntas registradas")
                        .build();
                resultado = Response.status(Response.Status.OK)
                        .entity(data)
                        .build();
            }
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();

        }
        return  resultado;
    }

    @POST
    @Path("/")
    public Response registrarPregunta(DtoPregunta dtoPregunta){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = new Pregunta();
            pregunta.setNombrePregunta(dtoPregunta.getNombre_pregunta());
            pregunta.setTipo(dtoPregunta.getTipo());
            pregunta.setRango(dtoPregunta.getRango());
            Usuario usuario = new Usuario(dtoPregunta.getUsuarioDto().get_id());
            pregunta.setUsuario(usuario);
            pregunta.setActivo(1);
            pregunta.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            if ( dtoPregunta.getOpciones() != null) {
                for (DtoOpcion opcion : dtoPregunta.getOpciones()) {
                    Opcion paraInsertar = new Opcion();
                    paraInsertar.setActivo(1);
                    paraInsertar.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
                    paraInsertar.setNombre_opcion(opcion.getNombre_opcion());
                    pregunta.addOpcion(paraInsertar);
                }
            }

            Pregunta resul = dao.insert(pregunta);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("_id", resul.get_id())
                    .add("mensaje", "pregunta creada con exito")
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
            resultado = Response.status(Response.Status.BAD_REQUEST).entity(data).build();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Response consultarPregunta(@PathParam("id") long id){
        JsonObject data;
        JsonObject pregunta;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta resul = dao.find(id, Pregunta.class);
            if ( resul.getActivo() != 0) {
                JsonArrayBuilder listaOpcion = Json.createArrayBuilder();
                for ( Opcion opcion: resul.getOpciones()){
                    if( opcion.getActivo() != 0 ) {
                        JsonObject objetoOpcion = Json.createObjectBuilder()
                                .add("_id", opcion.get_id())
                                .add("nombre_opcion", opcion.getNombre_opcion())
                                .build();
                        listaOpcion.add(objetoOpcion);
                    }
                }
                pregunta = Json.createObjectBuilder()
                        .add("_id", resul.get_id())
                        .add("pregunta", resul.getNombrePregunta())
                        .add("tipo", resul.getTipo())
                        .add("rango", resul.getRango())
                        .add("opciones", listaOpcion)
                        .add("usuario", Json.createObjectBuilder()
                                .add("_id", resul.getUsuario().get_id())
                                .add("nombre", resul.getUsuario().getNombre())
                                .add("apellido", resul.getUsuario().getApellido())
                                .add("correo", resul.getUsuario().getCorreo())
                                .add("rol", resul.getUsuario().getRol()))
                        .build();
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", pregunta)
                        .build();
            }
            else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Pregunta no se encuentra activa")
                        .build();
            }
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e){
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
    public Response eliminarPregunta(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = dao.find(id, Pregunta.class);
            pregunta.setActivo(0);
            pregunta.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pregunta resul = dao.update(pregunta);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Pregunta eliminada con exito")
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

    @PUT
    @Path("/{id}")
    public Response actualizarPregunta(@PathParam("id") long id, DtoPregunta dtoPregunta){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = dao.find(id, Pregunta.class);
            pregunta.setNombrePregunta(dtoPregunta.getNombre_pregunta());
            pregunta.setTipo(dtoPregunta.getTipo());
            pregunta.setRango(dtoPregunta.getRango());
            pregunta.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pregunta resul = dao.update( pregunta );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "pregunta actualizada con exito")
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
