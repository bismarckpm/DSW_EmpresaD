package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.comandos.Parroquia.*;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.mappers.ParroquiaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseParroquia;

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
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/parroquias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioParroquia extends AplicacionBase {

    /**
     * Metodo para listar todas las Parroquias registradas
     * @return regresa la lista de las categorias, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarParroquias(){
        Response resultado = null;
        try{
            ComandoListarParroquias comandoListarParroquias = new ComandoListarParroquias();
            comandoListarParroquias.execute();
            resultado = comandoListarParroquias.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Parroquia
     * @param dtoParroquia Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarParroquia(DtoParroquia dtoParroquia){
        Response resultado = null;
        try {
            verifyParams( dtoParroquia);
            ComandoRegistrarParroquia comandoRegistrarParroquia = new ComandoRegistrarParroquia();
            comandoRegistrarParroquia.setDtoParroquia( dtoParroquia);
            comandoRegistrarParroquia.execute();
            resultado = comandoRegistrarParroquia.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
        }

    /**
     * Metodo para consultar una Parroquia dado un identificador
     * @param id Identificador de la Parroquia que se desea consultar
     * @return regresa la categoria Parroquia, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarParroquia(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams( id);
            ComandoConsultarParroquia comandoConsultarParroquia = new ComandoConsultarParroquia();
            comandoConsultarParroquia.setId( id);
            comandoConsultarParroquia.execute();
            resultado = comandoConsultarParroquia.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Parroquia dado un identificador
     * @param id Identificador de la Parroquia que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarParroquia(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams( id);
            ComandoEliminarParroquia comandoEliminarParroquia = new ComandoEliminarParroquia();
            comandoEliminarParroquia.setId( id);
            comandoEliminarParroquia.execute();
            resultado = comandoEliminarParroquia.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Parroquia dado un identificador
     * @param id Identificador de la Parroquia que se desea actualizar
     * @param dtoParroquia Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarParroquia(@PathParam("id") long id, DtoParroquia dtoParroquia){
        Response resultado = null;
        try{
            verifyParams( id);
            verifyParams( dtoParroquia);
            ComandoActualizarParroquia comandoActualizarParroquia = new ComandoActualizarParroquia();
            comandoActualizarParroquia.setDtoParroquia( dtoParroquia);
            comandoActualizarParroquia.setId( id);
            comandoActualizarParroquia.execute();
            resultado = comandoActualizarParroquia.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}

