package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.DatoEncuestadoMapper;
import mercadeoucab.responses.ResponseDatoEncuestado;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

@Path( "/datos_encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioDatoEncuestado extends AplicacionBase{
    
    @GET
    @Path("/")
    public Response listarDatosEncuestado(){
        JsonObject data;
        JsonArrayBuilder datoEncuestados = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            List<DatoEncuestado> datosEncuestadosObtenidos = dao.findAll( DatoEncuestado.class);
            for (DatoEncuestado datoEncuestado: datosEncuestadosObtenidos){
                if ( datoEncuestado.getActivo() != 0 ){
                    ResponseDatoEncuestado responseDatoEncuestado = new ResponseDatoEncuestado();
                    DtoDatoEncuestado dtoDatoEncuestado = DatoEncuestadoMapper.mapEntitytoDto( datoEncuestado);
                    JsonObject objeto = responseDatoEncuestado.generate( dtoDatoEncuestado);
                    datoEncuestados.add( objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", datoEncuestados)
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
    public Response registrarDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado){
        JsonObject data;
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = new DatoEncuestado();
            datoEncuestado.setSegundoNombre(dtoDatoEncuestado.getSegundoNombre());
            datoEncuestado.setSegundoapellido(dtoDatoEncuestado.getSegundoapellido());
            datoEncuestado.setEdad(dtoDatoEncuestado.getEdad());
            datoEncuestado.setGenero(dtoDatoEncuestado.getGenero());
            datoEncuestado.setMedioConexion(dtoDatoEncuestado.getMedioConexion());
            datoEncuestado.setNive_economico(dtoDatoEncuestado.getNive_economico());
            datoEncuestado.setNivelAcademico(dtoDatoEncuestado.getNivelAcademico());
            datoEncuestado.setPersonasHogar(dtoDatoEncuestado.getPersonasHogar());
            datoEncuestado.setCedula(dtoDatoEncuestado.getCedula());
            datoEncuestado.setActivo(1);
            datoEncuestado.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Parroquia parroquia = new Parroquia(dtoDatoEncuestado.getFk_lugar().get_id());
            datoEncuestado.setFk_lugar(parroquia);
            Usuario usuario = new Usuario(dtoDatoEncuestado.getUsuario().get_id());
            datoEncuestado.setUsuario( usuario );
            Ocupacion ocupacion = new Ocupacion(dtoDatoEncuestado.getOcupacion().get_id());
            datoEncuestado.setOcupacion( ocupacion );
            for ( DtoTelefono telefono: dtoDatoEncuestado.getTelefonos()){
                Telefono paraInsertar = new Telefono();
                paraInsertar.setTelefono( telefono.getTelefono());
                paraInsertar.setActivo( 1);
                paraInsertar.setCreado_el( new Date(Calendar.getInstance().getTime().getTime()));
                datoEncuestado.addTelefono( paraInsertar);
            }
            for (DtoHijo hijo: dtoDatoEncuestado.getHijos()){
                Hijo paraInsertar = new Hijo();
                paraInsertar.setEdad( hijo.getEdad());
                paraInsertar.setGenero( hijo.getGenero());
                paraInsertar.setActivo( 1);
                paraInsertar.setCreado_el( new Date(Calendar.getInstance().getTime().getTime()));
                datoEncuestado.addHijo( paraInsertar);
            }
            DatoEncuestado resul = dao.insert(datoEncuestado);
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
    public Response consultarDatoEncuestado(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado resul = dao.find(id, DatoEncuestado.class);
            if ( resul.getActivo() !=0 ){
                ResponseDatoEncuestado responseDatoEncuestado = new ResponseDatoEncuestado();
                DtoDatoEncuestado dtoDatoEncuestado = DatoEncuestadoMapper.mapEntitytoDto( resul);
                JsonObject objeto = responseDatoEncuestado.generate( dtoDatoEncuestado);
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", objeto)
                        .build();
            }
            else
                {
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Dato Encuestado no se encuentra activo")
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

    @PUT
    @Path("/{id}")
    public Response actualizarDatoEncuestado(@PathParam("id") long id, DtoDatoEncuestado dtoDatoEncuestado){
        JsonObject data;
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = dao.find(id, DatoEncuestado.class);
            datoEncuestado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            datoEncuestado.setSegundoNombre(dtoDatoEncuestado.getSegundoNombre());
            datoEncuestado.setSegundoapellido(dtoDatoEncuestado.getSegundoapellido());
            datoEncuestado.setEdad(dtoDatoEncuestado.getEdad());
            datoEncuestado.setGenero(dtoDatoEncuestado.getGenero());
            datoEncuestado.setMedioConexion(dtoDatoEncuestado.getMedioConexion());
            datoEncuestado.setNive_economico(dtoDatoEncuestado.getNive_economico());
            datoEncuestado.setNivelAcademico(dtoDatoEncuestado.getNivelAcademico());
            datoEncuestado.setPersonasHogar(dtoDatoEncuestado.getPersonasHogar());
            DatoEncuestado resul = dao.update(datoEncuestado);
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
    @Path("/eliminar/{id}")
    public Response eliminarDatoEncuestado(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = dao.find(id, DatoEncuestado.class);
            datoEncuestado.setActivo(0);
            datoEncuestado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            DatoEncuestado resul = dao.update(datoEncuestado);
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
                    .add("message",problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return  resultado;
    }
}
