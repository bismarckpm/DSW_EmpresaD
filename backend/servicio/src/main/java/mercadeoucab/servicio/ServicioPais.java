package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.mappers.PaisMapper;
import mercadeoucab.responses.ResponsePais;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/paises" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPais extends AplicacionBase{

    @GET
    @Path("/")
    public Response listar_paises(){
        JsonObject data;
        JsonArrayBuilder paises = Json.createArrayBuilder();
        Response resultado = null;
        try{
            DaoPais dao = new DaoPais();
            List<Pais> paisesObtenidos = dao.findAll( Pais.class);

            for (Pais pais: paisesObtenidos){
                if (pais.getActivo()!= 0){
                    ResponsePais responsePais = new ResponsePais();
                    DtoPais dtoPais = PaisMapper.mapEntityToDto( pais);
                    JsonObject objeto = responsePais.generate( dtoPais);
                    paises.add( objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", paises)
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

    @GET
    @Path("/{id}")
    public Response obtenerPais(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoPais dao = new DaoPais();
            Pais resul = dao.find(id, Pais.class);
            if ( resul.getActivo() != 0){
                ResponsePais responsePais = new ResponsePais();
                DtoPais dtoPais = PaisMapper.mapEntityToDto( resul);
                JsonObject pais = responsePais.generate( dtoPais);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", pais)
                        .build();
            }else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Pais no se encuentra activo")
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

    @POST
    @Path("/")
    public Response agregarPais(DtoPais dtoPais){
        JsonObject data;
        Response resultado = null;
        try{
            DaoPais dao = new DaoPais();
            Pais pais = new Pais();
            pais.setActivo(1);
            pais.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            pais.setNombre(dtoPais.getNombre());
            Pais resul = dao.insert( pais );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch(Exception e){
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
    public Response actualizarPais(@PathParam("id") long id,DtoPais dtoPais){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(id, Pais.class);
            pais.setNombre(dtoPais.getNombre());
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
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
    public  Response eliminarPais(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(id , Pais.class);
            pais.setActivo(0);
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
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

}
