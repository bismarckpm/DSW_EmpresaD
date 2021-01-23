package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.mappers.SubCategoriaMapper;
import mercadeoucab.responses.ResponseSubCategoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/subcategorias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSubCategoria extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerSubCategoria(@PathParam("id") Long id){
        JsonObject data;
        JsonObject subcategoria;
        Response resultado = null;
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria resul = dao.find( id, SubCategoria.class);
            ResponseSubCategoria responseSubCategoria = new ResponseSubCategoria();
            DtoSubCategoria dtoSubCategoria = SubCategoriaMapper.mapEntityToDto( resul);
            if ( dtoSubCategoria.getActivo() == 1 ){
                subcategoria = responseSubCategoria.generate( dtoSubCategoria);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", subcategoria)
                        .build();

            }else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "No se encuentra activa")
                        .build();
            }
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
    public Response listarSubCategoria(){
        JsonObject data;
        JsonArrayBuilder subcategoriasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSubCategoria dao = new DaoSubCategoria();
            List<SubCategoria> subCategorias = dao.findAll(SubCategoria.class);
            ResponseSubCategoria responseSubCategoria = new ResponseSubCategoria();
            for(SubCategoria subCategoria: subCategorias){
                if(subCategoria.getActivo() == 1){
                    DtoSubCategoria dtoSubCategoria = SubCategoriaMapper.mapEntityToDto( subCategoria);
                    JsonObject objeto = responseSubCategoria.generate( dtoSubCategoria);
                    subcategoriasList.add(objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", subcategoriasList)
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
    public Response  registrarSubCategoria(DtoSubCategoria dtoSubCategoria){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria subCategoria = new SubCategoria();
            subCategoria.setNombre( dtoSubCategoria.getNombre());
            Categoria categoria = new Categoria(
                    dtoSubCategoria.getCategoria().get_id()
            );
            subCategoria.setCategoria( categoria);
            subCategoria.setActivo( 1);
            subCategoria.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.insert( subCategoria);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "SUbcategoria creado con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                                .entity(data)
                                .build();
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
    public Response  actualizarSubCategoria(@PathParam("id") Long id,DtoSubCategoria dtoSubCategoria){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria subCategoria = dao.find( id, SubCategoria.class);
            subCategoria.setNombre( dtoSubCategoria.getNombre());
            subCategoria.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.update( subCategoria);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Subcategoria actualizado con exito")
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
    public Response  eliminarSubCategoria(@PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria subCategoria = dao.find( id, SubCategoria.class);
            subCategoria.setActivo( 0);
            subCategoria.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.update( subCategoria);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Subcategoria eliminada con exito")
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
