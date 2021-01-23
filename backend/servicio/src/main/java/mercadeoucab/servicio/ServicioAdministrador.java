package mercadeoucab.servicio;


import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePregunta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/administrador" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAdministrador extends AplicacionBase{

    /**
     * Metodo para listar todas las preguntas que un usuario administrador ha
     * creado
     * @param id Identificador del usuario administrador
     * @return regresa la lista de las preguntas de un usuario
     *  administrador o respuesta que no se encontro
     */
    @GET
    @Path("/{id}/preguntas")
    public Response preguntasAdministrador(@PathParam("id") long id){
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
                resultado = ResponseGeneral.Succes( preguntaslist);
            }//final if
            else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
