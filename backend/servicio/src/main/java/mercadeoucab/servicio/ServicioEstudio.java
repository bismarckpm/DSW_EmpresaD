package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.estudio.*;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

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

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/estudios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio extends AplicacionBase{

    /**
     * Metodo para listar todos los Estudios registrados
     * @return regresa la lista de los Estudios, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarEstudios(){
        Response resultado = null;
        try {
            ComandoListarEstudios comandoListarEstudios = new ComandoListarEstudios();
            comandoListarEstudios.execute();
            resultado = comandoListarEstudios.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para crear un Estudio
     * @param dtoEstudio Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarEstudio(DtoEstudio dtoEstudio){
        Response resultado = null;
        try {
            verifyParams(dtoEstudio);
            ComandoAgregarEstudio comandoAgregarEstudio = new ComandoAgregarEstudio();
            comandoAgregarEstudio.setDtoEstudio(dtoEstudio);
            comandoAgregarEstudio.execute();
            resultado = comandoAgregarEstudio.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar un Estudio dado un identificador
     * @param id Identificador del Estudio que se desea consultar
     * @return regresa el Estudio consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarEstudio(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams( id );
            ComandoConsultarEstudio comandoConsultarEstudio = new ComandoConsultarEstudio();
            comandoConsultarEstudio.setId( id );
            comandoConsultarEstudio.execute();
            resultado = comandoConsultarEstudio.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Estudio dado un identificador
     * @param id Identificador del Estudio que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarEstudio(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams(id);
            ComandoEliminarEstudio comandoEliminarEstudio = new ComandoEliminarEstudio();
            comandoEliminarEstudio.setId(id);
            comandoEliminarEstudio.execute();
            resultado = comandoEliminarEstudio.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para actualizar un Estudio dado un identificador
     * @param id Identificador del Estudio que se desea actualizar
     * @param dtoEstudio Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio){
        Response resultado = null;
        try {
            verifyParams(id);
            verifyParams(dtoEstudio);
            ComandoActualizarEstudio comandoActualizarEstudio = new ComandoActualizarEstudio();
            comandoActualizarEstudio.setDtoEstudio(dtoEstudio);
            comandoActualizarEstudio.setId(id);
            comandoActualizarEstudio.execute();
            resultado = comandoActualizarEstudio.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar los usuarios que han respondido a la encuesta
     * de un estudio
     * @param id Identificador del Estudio
     * @return devuelve la lista de usuarios o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/usuarios_respondieron")
    public Response usuariosRespondieronEncuesta(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams(id);
            ComandoUsuariosRespondieronEncuesta comandoUsuariosRespondieronEncuesta = new ComandoUsuariosRespondieronEncuesta();
            comandoUsuariosRespondieronEncuesta.setId(id);
            comandoUsuariosRespondieronEncuesta.execute();
            resultado = comandoUsuariosRespondieronEncuesta.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar los usuarios que han califican a responder
     *      la encuesta de un estudio
     * @param id Identificador del Estudio
     * @return devuelve la lista de usuarios o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/usuarios_aplican")
    public Response usuariosAplicanEncuesta(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams(id);
            ComandoUsuariosAplicanEncuesta comandoUsuariosAplicanEncuesta = new ComandoUsuariosAplicanEncuesta();
            comandoUsuariosAplicanEncuesta.setId(id);
            comandoUsuariosAplicanEncuesta.execute();
            resultado = comandoUsuariosAplicanEncuesta.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
