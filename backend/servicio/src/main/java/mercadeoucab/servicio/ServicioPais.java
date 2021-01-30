package mercadeoucab.servicio;

import mercadeoucab.comandos.Pais.*;
import mercadeoucab.dtos.DtoPais;
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
@Path( "/paises" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPais extends AplicacionBase{

    /**
     * Metodo para listar todos los Paises registrados
     * @return regresa la lista de los estudios, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listar_paises(){
        Response resultado = null;
        try{
            ComandoListarPaises comandoListarPaises = new ComandoListarPaises();
            comandoListarPaises.execute();
            resultado = comandoListarPaises.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar un Pais dado un identificador
     * @param id Identificador del Pais que se desea consultar
     * @return regresa el Pais consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerPais(@PathParam("id") long id){
        Response resultado = null;
        try{
            verifyParams( id);
            ComandoObtenerPais comandoObtenerPais = new ComandoObtenerPais();
            comandoObtenerPais.setId( id);
            comandoObtenerPais.execute();
            resultado = comandoObtenerPais.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para crear un Pais
     * @param dtoPais Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarPais(DtoPais dtoPais){
        Response resultado = null;
        try{
            verifyParams( dtoPais);
            ComandoAgregarPais comandoAgregarPais = new ComandoAgregarPais();
            comandoAgregarPais.setDtoPais( dtoPais);
            comandoAgregarPais.execute();
            resultado = comandoAgregarPais.getResult();
        }
        catch(Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Pais dado un identificador
     * @param id Identificador del Pais que se desea actualizar
     * @param dtoPais Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarPais(@PathParam("id") long id,DtoPais dtoPais){
        Response resultado = null;
        try {
            verifyParams( id);
            verifyParams( dtoPais);
            ComandoActualizarPais comandoActualizarPais = new ComandoActualizarPais();
            comandoActualizarPais.setDtoPais( dtoPais);
            comandoActualizarPais.setId( id);
            comandoActualizarPais.execute();
            resultado = comandoActualizarPais.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Pais dado un identificador
     * @param id Identificador del Pais que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public  Response eliminarPais(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams( id);
            ComandoEliminarPais comandoEliminarPais = new ComandoEliminarPais();
            comandoEliminarPais.setId( id);
            comandoEliminarPais.execute();
            resultado = comandoEliminarPais.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
