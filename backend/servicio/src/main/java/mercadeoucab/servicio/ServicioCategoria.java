package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoCategoria;
import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Path( "/categorias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCategoria extends AplicacionBase{

    @GET
    @Path("/")
    public Response listarCategorias(){
        JsonObject data;
        JsonArrayBuilder categoriasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            List<Categoria> categorias = dao.findAll(Categoria.class);
            for (Categoria categoria: categorias){
                if (categoria.getActivo() == 1){
                    JsonObject objeto = Json.createObjectBuilder()
                                            .add("_id", categoria.get_id())
                                            .add("nombre", categoria.getNombre())
                                            .build();
                    categoriasList.add(objeto);}
                }
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", categoriasList)
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

    @POST
    @Path("/")
    public Response agregarCategoria(DtoCategoria dtoCategoria){
        JsonObject data;
        Response resultado = null;
        try{
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria();
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setActivo(1);
            categoria.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.insert(categoria);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Categoria creada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK).entity(data).build();
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

    @GET
    @Path("/{id}")
    public Response consultarCategoria(@PathParam("id") long id){
        JsonObject data;
        JsonObject categoria;
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria resul = dao.find(id, Categoria.class);
            categoria = Json.createObjectBuilder()
                            .add("_id", resul.get_id())
                            .add("nombre", resul.getNombre())
                            .build();
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", categoria)
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
    @Path("/{id}")
    public Response actualizarCategoria(@PathParam("id") long id, DtoCategoria dtoCategoria){
        JsonObject data;
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id , Categoria.class);
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.update(categoria);
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("mensaje","Categoria actualizada con exito")
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
    public Response eliminarCategoria(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id, Categoria.class);
            categoria.setActivo(0);
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.update(categoria);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Categoria eliminada con exito")
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
}
