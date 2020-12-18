package mercadeoucab.servicio;


import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/administrador" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAdministrador extends AplicacionBase{

    @GET
    @Path("/{id}/preguntas")
    public Response preguntasAdministrador(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            List<Pregunta> preguntas = dao.obtenerPreguntasAdministrador(new Usuario(id));
            JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
            if(!(preguntas.isEmpty())) {
                for (Pregunta pregunta : preguntas) {
                    String tipo = pregunta.getTipo();
                    JsonObject objeto = null;
                    JsonArrayBuilder opcionesList = null;
                    switch (tipo) {
                        case "abierta":
                            objeto = Json.createObjectBuilder()
                                    .add("pregunta", Json.createObjectBuilder()
                                            .add("_id", pregunta.get_id())
                                            .add("nombre", pregunta.getNombrePregunta())
                                            .add("tipo", pregunta.getTipo()))
                                    .build();
                            preguntaslist.add(objeto);
                            break;

                        case "multiple":
                            opcionesList = Json.createArrayBuilder();
                            for (Opcion opcion : pregunta.getOpciones()) {
                                JsonObject option = Json.createObjectBuilder()
                                        .add("_id", opcion.get_id())
                                        .add("nombre", opcion.getNombre_opcion())
                                        .build();
                                opcionesList.add(option);
                            }
                            objeto = Json.createObjectBuilder()
                                    .add("pregunta", Json.createObjectBuilder()
                                            .add("_id", pregunta.get_id())
                                            .add("nombre", pregunta.getNombrePregunta())
                                            .add("tipo", pregunta.getTipo())
                                            .add("opciones", opcionesList))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                        case "simple":
                            opcionesList = Json.createArrayBuilder();
                            for (Opcion opcion : pregunta.getOpciones()) {
                                JsonObject option = Json.createObjectBuilder()
                                        .add("_id", opcion.get_id())
                                        .add("nombre", opcion.getNombre_opcion())
                                        .build();
                                opcionesList.add(option);
                            }
                            objeto = Json.createObjectBuilder()
                                    .add("pregunta", Json.createObjectBuilder()
                                            .add("_id", pregunta.get_id())
                                            .add("nombre", pregunta.getNombrePregunta())
                                            .add("tipo", pregunta.getTipo())
                                            .add("opciones", opcionesList))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                        case "boolean":
                            objeto = Json.createObjectBuilder()
                                    .add("pregunta", Json.createObjectBuilder()
                                            .add("_id", pregunta.get_id())
                                            .add("nombre", pregunta.getNombrePregunta())
                                            .add("tipo", pregunta.getTipo()))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                        case "rango":
                            objeto = Json.createObjectBuilder()
                                    .add("pregunta", Json.createObjectBuilder()
                                            .add("_id",pregunta.get_id())
                                            .add("nombre", pregunta.getNombrePregunta())
                                            .add("tipo", pregunta.getTipo())
                                            .add("rango", pregunta.getRango()))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                    }//final switch
                }//Final for

                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", preguntaslist)
                        .build();
                resultado = Response.status(Response.Status.OK)
                        .entity(data)
                        .build();
            }//final if
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("Preguntas", "No posee preguntas asociadas")
                        .build();
                preguntaslist.add(agregar);

            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", preguntaslist)
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
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
