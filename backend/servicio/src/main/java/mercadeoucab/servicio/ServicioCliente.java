package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.*;
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
        JsonObject data;
        JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud dao = new DaoSolicitud();
            List<Solicitud> solicitudes = dao.solicitudesCliente(new Usuario(id));
            ResponseSolicitud responseSolicitud = new ResponseSolicitud();
            if(!(solicitudes.isEmpty())){
                for (Solicitud resul: solicitudes){
                    if(resul.getActivo() == 1){
                        DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto( resul);
                        JsonObject object = responseSolicitud.generate( dtoSolicitud);
                        solicitudesList.add(object);
                    }
                } //final for
                resultado = ResponseGeneral.Succes( solicitudesList);
            }
            else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
