package mercadeoucab.servicio;

import mercadeoucab.comandos.Estado.*;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosEstado;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/estados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstado extends AplicacionBase {

    private final FabricaComandosAbstractos fabricaComandosEstado = FabricaComandosEstado.getFactory(Comandos.ESTADO);

    /**
     * Metodo para listar todos los estados registrados
     * @return regresa la lista de los estados, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarEstador(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try{
            validateToken(token);
            ComandoListarEstados comandoListarEstados = (ComandoListarEstados) fabricaComandosEstado.comandoListar();
            comandoListarEstados.execute();
            resultado = comandoListarEstados.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear un Estado
     * @param dtoEstado Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarEstado(@HeaderParam("Authorization") String token, DtoEstado dtoEstado){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( dtoEstado);
            ComandoAgregarEstado comandoAgregarEstado = (ComandoAgregarEstado) fabricaComandosEstado.comandoCrear();
            comandoAgregarEstado.setDtoEstado( dtoEstado);
            comandoAgregarEstado.execute();
            resultado = comandoAgregarEstado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Estado
     * @param dtoEstado Objeto que se desea actualizar
     * @param id Identificador del Estado a actualizar
     * @return regresa mensaje de exito o mensaje de error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarEstado(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoEstado dtoEstado){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            verifyParams( dtoEstado);
            ComandoActualizarEstado comandoActualizarEstado = (ComandoActualizarEstado) fabricaComandosEstado.comandoModificar();
            comandoActualizarEstado.setDtoEstado( dtoEstado);
            comandoActualizarEstado.setId( id);
            comandoActualizarEstado.execute();
            resultado = comandoActualizarEstado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Estado dado un identificador
     * @param id Identificador del Estado que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarEstado(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            ComandoEliminarEstado comandoEliminarEstado = (ComandoEliminarEstado) fabricaComandosEstado.comandoEliminar();
            comandoEliminarEstado.setId( id);
            comandoEliminarEstado.execute();
            resultado = comandoEliminarEstado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar un Estado dado un identificador
     * @param id Identificador del Estado que se desea consultar
     * @return regresa el Estado consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarEstado(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            ComandoConsultarEstado comandoConsultarEstado = (ComandoConsultarEstado) fabricaComandosEstado.comandoConsultar();
            comandoConsultarEstado.setId( id);
            comandoConsultarEstado.execute();
            resultado = comandoConsultarEstado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }


}
