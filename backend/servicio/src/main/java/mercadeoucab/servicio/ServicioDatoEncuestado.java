package mercadeoucab.servicio;

import mercadeoucab.comandos.DatoEncuestado.*;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
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
@Path( "/datos_encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioDatoEncuestado extends AplicacionBase{

    private final FabricaComandosAbstractos fabricaComandos = FabricaComandosAbstractos.getFactory(Comandos.DATOENCUESTADO);

    /**
     * Metodo para listar todos los Datos Encuestados registrados
     * @return regresa la lista de las Datos Encuestados o respuesta que no se encontro
     */
    @GET
    @Path("/")
    public Response listarDatosEncuestado(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarDatoEncuestados comandoListarDatoEncuestados = (ComandoListarDatoEncuestados) fabricaComandos.comandoListar();
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
    public Response registrarDatoEncuestado(@HeaderParam("Authorization") String token, DtoDatoEncuestado dtoDatoEncuestado){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( dtoDatoEncuestado);
            ComandoRegistrarDatoEncuestado comandoRegistrarDatoEncuestado = (ComandoRegistrarDatoEncuestado) fabricaComandos.comandoCrear();
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
    public Response consultarDatoEncuestado(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try{
            verifyParams( id);
            validateToken(token);
            ComandoConsultarDatoEncuestado comandoConsultarDatoEncuestado = (ComandoConsultarDatoEncuestado) fabricaComandos.comandoConsultar();
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
    public Response actualizarDatoEncuestado(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoDatoEncuestado dtoDatoEncuestado){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            verifyParams( dtoDatoEncuestado);
            ComandoActualizarDatoEncuestado comandoActualizarDatoEncuestado = (ComandoActualizarDatoEncuestado) fabricaComandos.comandoModificar();
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
    public Response eliminarDatoEncuestado(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoEliminarDatoEncuestado comandoEliminarDatoEncuestado = (ComandoEliminarDatoEncuestado) fabricaComandos.comandoEliminar();
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
