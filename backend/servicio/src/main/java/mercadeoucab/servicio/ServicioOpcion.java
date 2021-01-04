package mercadeoucab.servicio;

import mercadeoucab.accesodatos.*;
import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/opcion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOpcion extends AplicacionBase{

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOpcion obtenerOpcion(@PathParam("id") Long id){
        DtoOpcion resultado = new DtoOpcion();
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion resul = dao.find( id, Opcion.class);
            resultado.set_id( resul.get_id());
            resultado.setNombre_opcion(resul.getNombre_opcion());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<Opcion> listarOpcion(){
        DaoOpcion dao = new DaoOpcion();
        return dao.findAll( Opcion.class);
    }

    @POST
    @Path("/")
    public Response registrarOpcion(DtoOpcion DTOO){
        JsonObject data;
        Response resultado = null;
        try{
            DaoOpcion daoO = new DaoOpcion();
            Opcion opcion = new Opcion();
            opcion.setNombre_opcion(DTOO.getNombre_opcion());
            opcion.setActivo(DTOO.getActivo());
            opcion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pregunta pregunta=new Pregunta(DTOO.get_Dtopregunta().get_id());
            opcion.setFk_pregunta(pregunta);
            Opcion resul = daoO.insert( opcion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
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
    @Path("/{id}")
    // @PathParam("id") Long id
    public Response actualizarOpcion(@PathParam("id") Long id, DtoOpcion DTOO){
        JsonObject data;
        Response resultado = null;
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion opcion = dao.find( id, Opcion.class);
            if(DTOO.getNombre_opcion()!=null){
                opcion.setNombre_opcion(DTOO.getNombre_opcion());
            }
            opcion.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Opcion resul = dao.update( opcion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Actualizado exitosamente")
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
    public Response eliminarOpcion(@PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion opcion = dao.find( id, Opcion.class);
            opcion.setActivo( 0);
            opcion.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Opcion resul = dao.update( opcion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Eliminado exitosamente")
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
