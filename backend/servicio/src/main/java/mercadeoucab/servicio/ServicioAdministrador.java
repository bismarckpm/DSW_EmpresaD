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
        JsonArrayBuilder preguntasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            List<Pregunta> preguntas = dao.obtenerPreguntasAdministrador(new Usuario(id));
            if(!(preguntas.isEmpty())){
                for(Pregunta pregunta: preguntas){
                    JsonArrayBuilder listaOpcion = Json.createArrayBuilder();
                    for ( Opcion opcion: pregunta.getOpciones()){
                        if( opcion.getActivo() != 0 ) {
                            JsonObject objetoOpcion = Json.createObjectBuilder()
                                    .add("_id", opcion.get_id())
                                    .add("nombre", opcion.getNombre_opcion())
                                    .build();
                            listaOpcion.add(objetoOpcion);
                        }
                    }
                    JsonObject objeto = Json.createObjectBuilder()
                            .add("_id", pregunta.get_id())
                            .add("pregunta",pregunta.getNombrePregunta())
                            .add("tipo", pregunta.getTipo())
                            .add("rango", pregunta.getRango())
                            .add("opciones", listaOpcion)
                            .build();
                    preguntasList.add( objeto );
                }
            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("Preguntas", "No posee preguntas asociadas")
                        .build();
                preguntasList.add(agregar);
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", preguntasList)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){

        }
        return resultado;
    }
}
