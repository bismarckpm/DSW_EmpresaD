package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.comandos.Ocupacion.*;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.mappers.OcupacionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseOcupacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Antonio Nohra
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/ocupaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOcupacion extends AplicacionBase{

    /**
     * Metodo para consultar una Ocupacion dado un identificador
     * @param id Identificador de la Ocupacion que se desea consultar
     * @return regresa la Ocupacion consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerOcupacion(@PathParam("id") Long id){
        Response resultado = null;
        try{
            verifyParams( id);
            ComandoObtenerOcupacion comandoObtenerOcupacion = new ComandoObtenerOcupacion();
            comandoObtenerOcupacion.setId( id);
            comandoObtenerOcupacion.execute();
            resultado = comandoObtenerOcupacion.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las ocupaciones registradas
     * @return regresa la lista de las ocupaciones, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarOcupacion(){
        Response resultado = null;
        try {
            ComandoListarOcupaciones comandoListarOcupaciones = new ComandoListarOcupaciones();
            comandoListarOcupaciones.execute();
            resultado = comandoListarOcupaciones.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Ocupacion
     * @param dtoOcupacion Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarOcupacion(DtoOcupacion dtoOcupacion){
        Response resultado = null;
        try{
            verifyParams( dtoOcupacion);
            ComandoRegistrarOcupacion comandoRegistrarOcupacion = new ComandoRegistrarOcupacion();
            comandoRegistrarOcupacion.setDtoOcupacion( dtoOcupacion);
            comandoRegistrarOcupacion.execute();
            resultado = comandoRegistrarOcupacion.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Ocupacion dado un identificador
     * @param id Identificador de la Ocupacion que se desea actualizar
     * @param dtoOcupacion Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarOcupacion(@PathParam("id") long id,DtoOcupacion dtoOcupacion){
        Response resultado = null;
        try{
            verifyParams( id);
            verifyParams( dtoOcupacion);
            ComandoActualizarOcupacion comandoActualizarOcupacion = new ComandoActualizarOcupacion();
            comandoActualizarOcupacion.setDtoOcupacion( dtoOcupacion);
            comandoActualizarOcupacion.setId( id);
            comandoActualizarOcupacion.execute();
            resultado = comandoActualizarOcupacion.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Ocupacion dado un identificador
     * @param id Identificador de la Ocupacion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarOcupacion(@PathParam("id") long id){
        Response resultado = null;
        try{
            verifyParams( id);
            ComandoEliminarOcupacion comandoEliminarOcupacion = new ComandoEliminarOcupacion();
            comandoEliminarOcupacion.setId( id);
            comandoEliminarOcupacion.execute();
            resultado = comandoEliminarOcupacion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
