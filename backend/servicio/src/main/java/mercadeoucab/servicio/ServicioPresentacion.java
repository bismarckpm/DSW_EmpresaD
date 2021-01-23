package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.mappers.PresentacionMapper;
import mercadeoucab.responses.ResponsePresentacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/presentaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPresentacion extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerPresentacion(@PathParam("id") Long id){
        JsonObject data;
        JsonObject presentacion;
        Response resultado = null;
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion resul = dao.find( id, Presentacion.class);
            ResponsePresentacion responsePresentacion = new ResponsePresentacion();
            DtoPresentacion dtoPresentacion = PresentacionMapper.mapEntityToDto( resul);
            presentacion = responsePresentacion.generate( dtoPresentacion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", presentacion)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();

        }catch (Exception e) {
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

    @GET
    @Path("/")
    public Response listarPresentacion(){
        JsonObject data;
        JsonArrayBuilder presentacionesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoPresentacion dao = new DaoPresentacion();
            List<Presentacion> presentaciones = dao.findAll(Presentacion.class);
            ResponsePresentacion responsePresentacion = new ResponsePresentacion();
            for(Presentacion presentacion: presentaciones){
                if(presentacion.getActivo() == 1){
                    DtoPresentacion dtoPresentacion = PresentacionMapper.mapEntityToDto( presentacion);
                    JsonObject objeto = responsePresentacion.generate( dtoPresentacion);
                    presentacionesList.add(objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", presentacionesList)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();

        }
        return  resultado;
    }

    @POST
    @Path("/")
    public Response registrarPresentacion(DtoPresentacion DTOP){
        JsonObject data;
        Response resultado = null;
        try{
            DaoPresentacion daoP = new DaoPresentacion();
            Presentacion presentacion = new Presentacion();
            presentacion.setCantidad(DTOP.getCantidad());
            presentacion.setTipo(DTOP.getTipo());
            presentacion.setActivo(1);
            presentacion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Presentacion resul = daoP.insert( presentacion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "presentacion creada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarPresentacion(@PathParam("id") long id,DtoPresentacion DTOP){
        JsonObject data;
        Response resultado = null;
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find( id, Presentacion.class);
            presentacion.setCantidad(DTOP.getCantidad());
            presentacion.setTipo(DTOP.getTipo());
            presentacion.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Presentacion resul = dao.update( presentacion);
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("mensaje", "Presentacion actualizada con exito")
                        .build();
            resultado = Response.status(Response.Status.OK)
                                .entity(data)
                                .build();
        }catch (Exception e) {
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

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarPresentacion(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find( id, Presentacion.class);
            presentacion.setActivo( 0);
            presentacion.setModificado_el(
                            new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Presentacion resul = dao.update( presentacion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "presentacion eliminada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
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
