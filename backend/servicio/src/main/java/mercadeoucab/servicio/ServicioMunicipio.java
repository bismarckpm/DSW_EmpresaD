package mercadeoucab.servicio;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.mappers.MunicipioMapper;
import mercadeoucab.responses.ResponseBase;
import mercadeoucab.responses.ResponseMunicipio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/municipios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMunicipio extends AplicacionBase{

    @GET
    @Path("/")
    public Response listarMunicipios(){
        JsonObject data;
        JsonArrayBuilder municipios = Json.createArrayBuilder();
        Response resultado = null;

        try{
            DaoMunicipio dao = new DaoMunicipio();
            List<Municipio> municipiosObtenidos = dao.findAll( Municipio.class);
            ResponseMunicipio responseMunicipio = new ResponseMunicipio();
            for(Municipio municipio: municipiosObtenidos){
                if( municipio.getActivo() != 0 ){
                    DtoMunicipio dtoMunicipio = MunicipioMapper.mapEntitytoDto( municipio);
                    JsonObject objeto = responseMunicipio.generate( dtoMunicipio);
                    municipios.add(objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", municipios)
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

    @GET
    @Path("/{id}")
    public Response obtenerMunicipio(@PathParam("id") long id){
        JsonObject data;
        JsonObject municipio;
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio resul = dao.find(id , Municipio.class);
            ResponseMunicipio responseMunicipio = new ResponseMunicipio();
            if ( resul.getActivo()!=0 ){
                DtoMunicipio dtoMunicipio = MunicipioMapper.mapEntitytoDto( resul);
                municipio = responseMunicipio.generate( dtoMunicipio);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", municipio)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Municipio no se encuentra activo")
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
    public Response registrarMunicipio(DtoMunicipio dtoMunicipio){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = new Municipio();
            municipio.setActivo(1);
            municipio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            municipio.setNombre(dtoMunicipio.getNombre());
            Estado estado = new Estado(dtoMunicipio.getFk_estado().get_id());
            municipio.setFk_estado( estado );
            Municipio resul = dao.insert(municipio);
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
    public Response actualizarMunicipio(@PathParam("id") long id, DtoMunicipio dtoMunicipio){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = dao.find(id, Municipio.class);
            municipio.setNombre(dtoMunicipio.getNombre());
            municipio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Municipio resul = dao.update(municipio);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Actualizado exitosamente")
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
    public Response eliminarMunicipio(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = dao.find(id, Municipio.class);
            municipio.setActivo(0);
            municipio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Municipio resul = dao.update(municipio);
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
}
