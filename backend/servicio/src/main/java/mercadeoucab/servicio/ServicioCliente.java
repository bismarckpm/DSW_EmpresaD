package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.SolicitudMapper;
import mercadeoucab.responses.ResponseSolicitud;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/cliente" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCliente extends AplicacionBase{

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
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", solicitudesList)
                        .build();
            }
            else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "No posee solicitudes asociadas")
                        .build();
            }

            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
