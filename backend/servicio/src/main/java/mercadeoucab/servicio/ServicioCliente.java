package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.usuario.ComandoSolicitudesCliente;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.SolicitudMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseSolicitud;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response solicitudesCliente(@PathParam("id") long id){
        Response resultado = null;
        try {
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
