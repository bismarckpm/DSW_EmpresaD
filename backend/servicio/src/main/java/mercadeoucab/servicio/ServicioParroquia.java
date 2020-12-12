package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.entidades.Parroquia;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.lang.model.util.ElementScanner6;
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
                    Municipio municipio = parroquia.getFk_municipio();
                    Estado estado = municipio.getFk_estado();
                    JsonObject objetoPais = Json.createObjectBuilder()
                            .add("_id", estado.getFk_pais().get_id())
                            .add("nombre", estado.getFk_pais().getNombre())
                            .build();
                    JsonObject objetoEstado = Json.createObjectBuilder()
                            .add("_id", estado.get_id())
                            .add("nombre", estado.getNombre())
                            .add("pais", objetoPais)
                            .build();
                    JsonObject objetoMunicipio = Json.createObjectBuilder()
                            .add("_id", municipio.get_id())
                            .add("nombre",municipio.getNombre())
                            .add("estado", objetoEstado)
                            .build();
                    JsonObject objeto = Json.createObjectBuilder()
                            .add("_id", parroquia.get_id())
                            .add("nombre",parroquia.getNombre())
                            .add("valorSocioEconomico", parroquia.getValor_socio_economico())
                            .add("municipio", objetoMunicipio)
                            .build();
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
                Municipio municipio = resul.getFk_municipio();
                Estado estado = municipio.getFk_estado();
                JsonObject objetoPais = Json.createObjectBuilder()
                        .add("_id", estado.getFk_pais().get_id())
                        .add("nombre", estado.getFk_pais().getNombre())
                        .build();
                JsonObject objetoEstado = Json.createObjectBuilder()
                        .add("_id", estado.get_id())
                        .add("nombre", estado.getNombre())
                        .add("pais", objetoPais)
                        .build();
                JsonObject objetoMunicipio = Json.createObjectBuilder()
                        .add("_id", municipio.get_id())
                        .add("nombre",municipio.getNombre())
                        .add("estado", objetoEstado)
                        .build();
                parroquia = Json.createObjectBuilder()
                        .add("_id", resul.get_id())
                        .add("nombre",resul.getNombre())
                        .add("valorSocioEconomico", resul.getValor_socio_economico())
                        .add("municipio", objetoMunicipio)
                        .build();
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
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}

