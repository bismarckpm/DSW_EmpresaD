package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.mappers.TipoMapper;
import mercadeoucab.responses.ResponseTipo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/tipos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTipo extends AplicacionBase {

    @GET
    @Path("/")
    public Response listarTipos(){
        JsonObject data;
        JsonArrayBuilder tiposList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            List<Tipo> tipos = dao.findAll(Tipo.class);
            ResponseTipo responseTipo = new ResponseTipo();
            for(Tipo tipo: tipos){
                if(tipo.getActivo() == 1){
                    DtoTipo dtoTipo = TipoMapper.mapEntityToDto( tipo);
                    JsonObject objeto = responseTipo.generate( dtoTipo);
                    tiposList.add(objeto);
                }
            }
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", tiposList)
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
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Response obtenerTipo( @PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            Tipo resul = dao.find( id , Tipo.class);
            ResponseTipo responseTipo = new ResponseTipo();
            DtoTipo dtoTipo = TipoMapper.mapEntityToDto( resul);
            if( resul.getActivo() == 1){
                JsonObject tipo = responseTipo.generate( dtoTipo);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", tipo)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "No se encuentra activo")
                        .build();
            }
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

    @POST
    @Path("/")
    public Response registrarTipo( DtoTipo dtoTipo){
        JsonObject data;
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = new Tipo();
            tipo.setNombre( dtoTipo.getNombre());
            tipo.setActivo( 1);
            tipo.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.insert( tipo);
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("mensaje", "Tipo creado con exito")
                        .build();
            resultado = Response.status(Response.Status.OK).entity(data).build();
        }catch (Exception e) {
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
    public Response actualizarTipo( @PathParam("id") long id, DtoTipo dtoTipo){
        JsonObject data;
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = dao.find( id, Tipo.class);
            tipo.setNombre( dtoTipo.getNombre());
            tipo.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.update( tipo );
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("mensaje", "Tipo actualizado con exito")
                        .build();
            resultado = Response.status(Response.Status.OK)
                                .entity(data)
                                .header("Access-Control-Allow-Origin", "*")
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
    public Response eliminarTipo( @PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = dao.find( id, Tipo.class);
            tipo.setActivo( 0 );
            tipo.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.update( tipo);
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("mensaje", "Tipo eliminado con exito")
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
