package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/ocupaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOcupacion extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerOcupacion(@PathParam("id") Long id){
        JsonObject data;
        JsonObject ocupacion;
        Response resultado = null;
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion resul = dao.find( id, Ocupacion.class);
            ocupacion = Json.createObjectBuilder()
                            .add("_id", resul.get_id())
                            .add("nombre",resul.getNombre())
                            .build();
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", ocupacion)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e) {
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
    public Response listarOcupacion(){
        JsonObject data;
        JsonArrayBuilder ocupacionesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoOcupacion dao = new DaoOcupacion();
            List<Ocupacion> ocupaciones = dao.findAll(Ocupacion.class);
            for(Ocupacion ocupacion: ocupaciones){
                if(ocupacion.getActivo() == 1){
                    JsonObject objeto = Json.createObjectBuilder()
                            .add("_id", ocupacion.get_id())
                            .add("nombre",ocupacion.getNombre())
                            .build();
                    ocupacionesList.add(objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", ocupacionesList)
                    .build();
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
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();
        }
        return  resultado;
    }

    @POST
    @Path("/")
    public Response registrarOcupacion(DtoOcupacion DTOO){
        JsonObject data;
        Response resultado = null;
        try{
            DaoOcupacion daoO = new DaoOcupacion();
            Ocupacion O= new Ocupacion();
            O.setNombre(DTOO.getNombre());
            O.setActivo(1);
            O.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Ocupacion resul = daoO.insert( O);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Ocupacion creada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                                .entity(data)
                                .build();

        }
        catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(data)
                                .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public Response actualizarOcupacion(@PathParam("id") long id,DtoOcupacion DTOO){
        JsonObject data;
        Response resultado = null;
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion O = dao.find( id, Ocupacion.class);
            O.setNombre(DTOO.getNombre());
            O.setModificado_el(new Date(Calendar
                                    .getInstance()
                                    .getTime()
                                    .getTime()));
            Ocupacion resul = dao.update( O);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Ocupacion actualizada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e) {
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
    // @PathParam("id") Long id
    public Response eliminarOcupacion(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion O = dao.find( id, Ocupacion.class);
            O.setActivo( 0);
            O.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Ocupacion resul = dao.update( O);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Ocupacion eliminada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

}
