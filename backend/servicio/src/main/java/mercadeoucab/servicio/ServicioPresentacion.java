package mercadeoucab.servicio;

import mercadeoucab.comandos.Presentacion.*;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Antonio Nohra
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/presentaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPresentacion extends AplicacionBase{

    /**
     * Metodo para consultar una Presentacion dado un identificador
     * @param id Identificador de la Presentacion que se desea consultar
     * @return regresa la Presentacion consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerPresentacion(@PathParam("id") Long id){
        Response resultado = null;
        try{
            verifyParams(id);
            ComandoObtenerPresentacion comandoObtenerPresentacion = new ComandoObtenerPresentacion();
            comandoObtenerPresentacion.setId(id);
            comandoObtenerPresentacion.execute();
            resultado = comandoObtenerPresentacion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las Presentaciones registradas
     * @return regresa la lista de las Presentaciones, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarPresentacion(){
        Response resultado = null;
        try {
            ComandoListarPresentacion comandoListarPresentacion = new ComandoListarPresentacion();
            comandoListarPresentacion.execute();
            resultado = comandoListarPresentacion.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Presentacion
     * @param dtoPresentacion Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarPresentacion(DtoPresentacion dtoPresentacion){
        Response resultado = null;
        try{
            verifyParams(dtoPresentacion);
            ComandoRegistrarPresentacion comandoRegistrarPresentacion = new ComandoRegistrarPresentacion();
            comandoRegistrarPresentacion.setDtoPresentacion(dtoPresentacion);
            comandoRegistrarPresentacion.execute();
            resultado = comandoRegistrarPresentacion.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Presentacion dado un identificador
     * @param id Identificador de la Presentacion que se desea actualizar
     * @param dtoPresentacion Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarPresentacion(@PathParam("id") long id,DtoPresentacion dtoPresentacion){
        Response resultado = null;
        try{
            verifyParams( id );
            verifyParams( dtoPresentacion );
            ComandoActualizarPresentacion comandoActualizarPresentacion = new ComandoActualizarPresentacion();
            comandoActualizarPresentacion.setId( id );
            comandoActualizarPresentacion.setDtoPresentacion( dtoPresentacion );
            comandoActualizarPresentacion.execute();
            resultado = comandoActualizarPresentacion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Presentacion dado un identificador
     * @param id Identificador de la Presentacion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarPresentacion(@PathParam("id") long id){
        Response resultado = null;
        try{
            verifyParams(id);
            ComandoEliminarPresentacion comandoEliminarPresentacion = new ComandoEliminarPresentacion();
            comandoEliminarPresentacion.setId(id);
            comandoEliminarPresentacion.execute();
            resultado = comandoEliminarPresentacion.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
