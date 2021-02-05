package mercadeoucab.servicio;

import mercadeoucab.comandos.Usuario.ComandoSolicitudesCliente;
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
@Path( "/cliente" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCliente extends AplicacionBase{

    /**
     * Metodo para consultar las solicitudes creadar por algun cliente
     * @param id Identificador del cliente
     * @return regresa lista de solicitudes en caso de tener alguna creada,
     *      mensaje que no posee o mensaje de error en caso de ocurrir algun
     *      fallo
     */
    @GET
    @Path("/{id}/solicitudes")
    public Response solicitudesCliente(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoSolicitudesCliente comandoSolicitudesCliente = new ComandoSolicitudesCliente();
            comandoSolicitudesCliente.setId( id);
            comandoSolicitudesCliente.execute();
            resultado = comandoSolicitudesCliente.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
