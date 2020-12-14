package mercadeoucab.servicio;

import jdk.nashorn.internal.objects.annotations.Getter;
import mercadeoucab.accesodatos.DaoTelefono;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Telefono;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

@Path( "/telefonos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTelefono extends AplicacionBase {

    @GET
    @Path("/{id}")
    public Response obtenerTelefono(@PathParam("id") Long id){
        JsonObject data;
        JsonObject telefono;
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono resul = dao.find( id, Telefono.class);
            if ( resul.getActivo() != 0 ){
                telefono = Json.createObjectBuilder()
                        .add("_id", resul.get_id())
                        .add("telefono", resul.getTelefono())
                        .build();
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", telefono)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Telefono no se encuentra activo")
                        .build();
            }
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @POST
    @Path("/")
    public Response registrarTelefono( DtoTelefono dtoTelefono){
        JsonObject data;
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = new Telefono();
            telefono.setTelefono( dtoTelefono.getTelefono());
            telefono.setActivo( 1);
            telefono.setCreado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            DatoEncuestado datoEncuestado = new DatoEncuestado(
                    dtoTelefono.getDatoEncuestado().get_id()
            );
            telefono.setDatoEncuestado( datoEncuestado);
            Telefono resul = dao.insert( telefono);
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
    public Response modificarTelefono( @PathParam("id") Long id, DtoTelefono dtoTelefono){
        JsonObject data;
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find( id, Telefono.class);
            telefono.setTelefono( dtoTelefono.getTelefono());
            telefono.setActivo( 1);
            telefono.setModificado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            Telefono resul = dao.update( telefono);
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
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarTelefono( @PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find( id, Telefono.class);
            telefono.setActivo( 0);
            telefono.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Telefono resul = dao.update( telefono);
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
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
