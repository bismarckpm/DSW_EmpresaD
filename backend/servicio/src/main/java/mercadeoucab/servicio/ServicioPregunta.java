package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponseGeneral;
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
    public Response listarPreguntas(){
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
                                objeto = responsePregunta.generateWithRango( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;
                        }//final switch
                    }
                }//Final for

                resultado = ResponseGeneral.Succes( preguntaslist);
            }//final if
            else {
                resultado = ResponseGeneral.NoData();
            }
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
    public Response registrarPregunta(DtoPregunta dtoPregunta){
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
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
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
    public Response consultarPregunta(@PathParam("id") long id){
        JsonObject data;
        JsonObject pregunta;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta resul = dao.find(id, Pregunta.class);
            if ( resul.getActivo() != 0) {
                ResponsePregunta responsePregunta = new ResponsePregunta();
                DtoPregunta dtoPregunta = PreguntaMapper.mapEntityToDto( resul);
                String tipo = resul.getTipo();
                switch (tipo) {
                    case "abierta":
                    case "boolean":
                        pregunta = responsePregunta.generate( dtoPregunta);
                        break;

                    case "multiple":
                    case "simple":
                        pregunta = responsePregunta.generateWithOptions( dtoPregunta);
                        break;
                    case "rango":
                        pregunta = responsePregunta.generateWithRango( dtoPregunta);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + tipo);
                }//final switch
                resultado = ResponseGeneral.Succes( pregunta);
            }
            else{
                resultado = ResponseGeneral.NoData();
            }
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
    public Response eliminarPregunta(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = dao.find(id, Pregunta.class);
            pregunta.setActivo(0);
            pregunta.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pregunta resul = dao.update(pregunta);
            resultado = ResponseGeneral.SuccesMessage();
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
    public Response actualizarPregunta(@PathParam("id") long id, DtoPregunta dtoPregunta){
        Response resultado = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = dao.find(id, Pregunta.class);
            pregunta.setNombrePregunta(dtoPregunta.getNombre_pregunta());
            pregunta.setTipo(dtoPregunta.getTipo());
            pregunta.setRango(dtoPregunta.getRango());
            pregunta.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pregunta resul = dao.update( pregunta );
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
