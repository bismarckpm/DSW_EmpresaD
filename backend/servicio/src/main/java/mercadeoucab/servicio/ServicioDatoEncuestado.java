package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.comandos.DatoEncuestado.*;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoHijo;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.DatoEncuestadoMapper;
import mercadeoucab.responses.ResponseDatoEncuestado;
import mercadeoucab.responses.ResponseGeneral;

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
@Path( "/datos_encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioDatoEncuestado extends AplicacionBase{

    /**
     * Metodo para listar todos los Datos Encuestados registrados
     * @return regresa la lista de las Datos Encuestados o respuesta que no se encontro
     */
    @GET
    @Path("/")
    public Response listarDatosEncuestado(){
        Response resultado = null;
        try {
            ComandoListarDatoEncuestados comandoListarDatoEncuestados = new ComandoListarDatoEncuestados();
            comandoListarDatoEncuestados.execute();
            resultado = comandoListarDatoEncuestados.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;

    }

    /**
     * Metodo para registrar un Dato Encuestado a un usuario con rol encuestado
     * @param dtoDatoEncuestado Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *      *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado){
        Response resultado = null;
        try {
            verifyParams( dtoDatoEncuestado);
            ComandoRegistrarDatoEncuestado comandoRegistrarDatoEncuestado = new ComandoRegistrarDatoEncuestado();
            comandoRegistrarDatoEncuestado.setDtoDatoEncuestado( dtoDatoEncuestado);
            comandoRegistrarDatoEncuestado.execute();
            resultado = comandoRegistrarDatoEncuestado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para consultar un Dato Encuestado dado su identificador
     * @param id Identificador del Dato Encuestado a consultar
     * @return regresa el Dato Encuestado consultado, respuesta que no se encontro
     *      o mensaje de error
     */
    @GET
    @Path("/{id}")
    public Response consultarDatoEncuestado(@PathParam("id") long id){
        Response resultado = null;
        try{
            verifyParams( id);
            ComandoConsultarDatoEncuestado comandoConsultarDatoEncuestado = new ComandoConsultarDatoEncuestado();
            comandoConsultarDatoEncuestado.setId( id);
            comandoConsultarDatoEncuestado.execute();
            resultado = comandoConsultarDatoEncuestado.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Dato Encuestado dado su identificador
     * @param id Identificador del Dato Encuestado a actualizar
     * @param dtoDatoEncuestado Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarDatoEncuestado(@PathParam("id") long id, DtoDatoEncuestado dtoDatoEncuestado){
        Response resultado = null;
        try {
            verifyParams( id);
            verifyParams( dtoDatoEncuestado);
            ComandoActualizarDatoEncuestado comandoActualizarDatoEncuestado = new ComandoActualizarDatoEncuestado();
            comandoActualizarDatoEncuestado.setDtoDatoEncuestado( dtoDatoEncuestado);
            comandoActualizarDatoEncuestado.setId( id);
            comandoActualizarDatoEncuestado.execute();
            resultado = comandoActualizarDatoEncuestado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Dato Encuestado dado su identificador
     * @param id Identificador del Dato Encuestado a eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/eliminar/{id}")
    public Response eliminarDatoEncuestado(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams( id);
            ComandoEliminarDatoEncuestado comandoEliminarDatoEncuestado = new ComandoEliminarDatoEncuestado();
            comandoEliminarDatoEncuestado.setId( id);
            comandoEliminarDatoEncuestado.execute();
            resultado = comandoEliminarDatoEncuestado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }
}
