package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.mappers.ParroquiaMapper;
import mercadeoucab.responses.ResponseParroquia;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/parroquias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioParroquia {

    @GET
    @Path("/")
    public Response listarParroquias(){
        JsonObject data;
        JsonArrayBuilder parroquias = Json.createArrayBuilder();
        Response resultado = null;
        try{
            DaoParroquia dao = new DaoParroquia();
            List<Parroquia> parroquiasObtenidas = dao.findAll( Parroquia.class);

            for( Parroquia parroquia: parroquiasObtenidas){
                if ( parroquia.getActivo() != 0 ){
                    ResponseParroquia responseParroquia = new ResponseParroquia();
                    DtoParroquia dtoParroquia = ParroquiaMapper.mapEntityToDto( parroquia);
                    JsonObject objeto = responseParroquia.generate( dtoParroquia);
                    parroquias.add( objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", parroquias)
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
    public Response registrarParroquia(DtoParroquia dtoParroquia){
        JsonObject data;
        Response resultado = null;
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = new Parroquia();
            parroquia.setNombre(dtoParroquia.getNombre());
            parroquia.setActivo(1);
            parroquia.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            parroquia.setValor_socio_economico(dtoParroquia.getValor_socio_economico());
            parroquia.setFk_municipio(new Municipio(dtoParroquia.getFk_municipio().get_id()));
            Parroquia resul = dao.insert( parroquia );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
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
    @Path("/{id}")
    public Response consultarParroquia(@PathParam("id") long id){
        JsonObject data;
        JsonObject parroquia;
        Response resultado = null;
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia resul = dao.find(id ,Parroquia.class);
            if ( resul.getActivo()!= 0 ){
                ResponseParroquia responseParroquia = new ResponseParroquia();
                DtoParroquia dtoParroquia = ParroquiaMapper.mapEntityToDto( resul);
                parroquia = responseParroquia.generate( dtoParroquia);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", parroquia)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Parroquia no se encuentra activo")
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

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarParroquia(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(id , Parroquia.class);
            parroquia.setActivo(0);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Parroquia resul = dao.update( parroquia );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Eliminado exitosamente")
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
    @Path("/{id}")
    public Response actualizarParroquia(@PathParam("id") long id, DtoParroquia dtoParroquia){
        JsonObject data;
        Response resultado = null;
        try{
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(id , Parroquia.class);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            parroquia.setNombre(dtoParroquia.getNombre());
            parroquia.setValor_socio_economico(dtoParroquia.getValor_socio_economico());
            Parroquia resul = dao.update( parroquia );
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
}

