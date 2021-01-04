package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Pais;
import org.junit.Assert;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/estados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstado extends AplicacionBase {

    @GET
    @Path("/")
    public Response listarEstador(){
        JsonObject data;
        JsonArrayBuilder estados = Json.createArrayBuilder();
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            List<Estado> estadosObtenidos = dao.findAll( Estado.class);

            for (Estado estado: estadosObtenidos){
                if( estado.getActivo() != 0 ){
                    JsonObject objetoPais = Json.createObjectBuilder()
                                                .add("_id", estado.getFk_pais().get_id())
                                                .add("nombre", estado.getFk_pais().getNombre())
                                                .build();
                    JsonObject objeto = Json.createObjectBuilder()
                            .add("_id", estado.get_id())
                            .add("nombre", estado.getNombre())
                            .add("pais", objetoPais)
                            .build();
                    estados.add(objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", estados)
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
        return  resultado;
    }

    @POST
    @Path("/")
    public Response agregarEstado(DtoEstado dtoEstado){
        JsonObject data;
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = new Estado();
            estado.setActivo(1);
            estado.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            Pais pais = new Pais(dtoEstado.getFk_pais().get_id());
            estado.setFk_pais( pais );
            Estado resul = dao.insert( estado );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
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
    public Response actualizarEstado(@PathParam("id") long id, DtoEstado dtoEstado){
        JsonObject data;
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(id, Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            Estado resul = dao.update(estado);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Actualizado exitosamente")
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
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarEstado(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(id, Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setActivo(0);
            Estado resul = dao.update(estado);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Eliminado exitosamente")
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
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Response consultarEstado(@PathParam("id") long id){
        JsonObject data;
        JsonObject estado;
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado resul = dao.find(id, Estado.class);
            if (resul.getActivo()!= 0) {
                JsonObject objetoPais = Json.createObjectBuilder()
                        .add("_id", resul.getFk_pais().get_id())
                        .add("nombre", resul.getFk_pais().getNombre())
                        .build();
                estado = Json.createObjectBuilder()
                        .add("_id", resul.get_id())
                        .add("nombre", resul.getNombre())
                        .add("pais", objetoPais)
                        .build();
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", estado)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Estado no se encuentra activo")
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
