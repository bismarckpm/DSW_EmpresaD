package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoMarca;
import mercadeoucab.dtos.DtoMarca;
import mercadeoucab.entidades.Marca;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/marcas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMarca extends AplicacionBase{

    @GET
    @Path("/")
    public Response listarMarcas(){
        JsonObject data;
        JsonArrayBuilder marcas = Json.createArrayBuilder();
        Response resultado = null;
        try{
            DaoMarca dao = new DaoMarca();
            List<Marca> marcasObtenidas = dao.findAll( Marca.class);

            for (Marca marca: marcasObtenidas){
                if( marca.getActivo() != 0 ){
                    JsonObject objecto = Json.createObjectBuilder()
                                            .add("_id", marca.get_id())
                                            .add("nombre", marca.getNombre())
                                            .build();
                    marcas.add( objecto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", marcas)
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

    @POST
    @Path("/")
    public Response registrarMarca(DtoMarca dtoMarca){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMarca dao = new DaoMarca();
            Marca marca = new Marca();
            marca.setNombre(dtoMarca.getNombre());
            marca.setActivo(1);
            marca.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Marca resul = dao.insert(marca);
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
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return  resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarMarca(@PathParam("id") long id, DtoMarca dtoMarca){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMarca dao = new DaoMarca();
            Marca marca = dao.find(id, Marca.class);
            marca.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            marca.setNombre(dtoMarca.getNombre());
            Marca resul = dao.update(marca);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Actualizada exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
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

    @GET
    @Path("/{id}")
    public Response consultarMarca(@PathParam("id") long id){
        JsonObject data;
        JsonObject marca;
        Response resultado = null;
        try {
            DaoMarca dao = new DaoMarca();
            Marca resul = dao.find(id, Marca.class);
            if ( resul.getActivo() != 0 ){
                marca = Json.createObjectBuilder()
                        .add("_id", resul.get_id())
                        .add("nombre", resul.getNombre())
                        .build();
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", marca)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Marca no se encuentra activa")
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
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarMarca(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMarca dao = new DaoMarca();
            Marca marca = dao.find(id, Marca.class);
            marca.setActivo(0);
            marca.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Marca resul = dao.update(marca);
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
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
