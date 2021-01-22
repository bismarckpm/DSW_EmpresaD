package mercadeoucab.servicio;


import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponsePregunta;

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
                                objeto = responsePregunta.generateWithRango( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;
                        }//final switch
                    }
                }//Final for

                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", preguntaslist)
                        .build();
            }//final if
            else{
            data = Json.createObjectBuilder()
                    .add("status", 204)
                    .add("message", "No posee preguntas asociadas")
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
        }
        return resultado;
    }
}
