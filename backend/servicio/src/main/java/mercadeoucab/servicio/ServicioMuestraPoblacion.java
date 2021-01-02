package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.entidades.Parroquia;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/muestrasPoblaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMuestraPoblacion extends AplicacionBase{

    @GET
    @Path("/")
    public List<MuestraPoblacion> listarMuestrasPoblaciones(){
        DaoMuestraPoblacion dao = new DaoMuestraPoblacion();
        return dao.findAll(MuestraPoblacion.class);
    }

    @POST
    @Path("/")
    public  Response registrarMuestraPoblacion(DtoMuestraPoblacion dtoMuestraPoblacion){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMuestraPoblacion dao = new DaoMuestraPoblacion();
            MuestraPoblacion muestraPoblacion = new MuestraPoblacion();
            muestraPoblacion.setCantidadHijos(dtoMuestraPoblacion.getCantidadHijos());
            muestraPoblacion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            muestraPoblacion.setActivo(1);
            muestraPoblacion.setGenero(dtoMuestraPoblacion.getGenero());
            muestraPoblacion.setNivelAcademico(dtoMuestraPoblacion.getNivelAcademico());
            muestraPoblacion.setNivelEconomico(dtoMuestraPoblacion.getNivelEconomico());
            muestraPoblacion.setRangoEdadInicio(dtoMuestraPoblacion.getRangoEdadInicio());
            muestraPoblacion.setRangoEdadFin(dtoMuestraPoblacion.getRangoEdadFin());
            Parroquia parroquia = new Parroquia(dtoMuestraPoblacion.getFk_lugar().get_id());
            muestraPoblacion.setFk_lugar(parroquia);
            Ocupacion ocupacion = new Ocupacion(dtoMuestraPoblacion.getDtoOcupacion().get_id());
            muestraPoblacion.setFk_ocupacion(ocupacion);
            MuestraPoblacion resul = dao.insert(muestraPoblacion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Muestra registrada con exito")
                    .add("_id", resul.get_id())
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public MuestraPoblacion consultarMuestraPoblacion(@PathParam("id") long id){
        DaoMuestraPoblacion dao = new DaoMuestraPoblacion();
        return dao.find(id, MuestraPoblacion.class);
    }

    @PUT
    @Path("/eliminar/{id}")
    public Response eliminarMuestraPoblacion(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMuestraPoblacion dao = new DaoMuestraPoblacion();
            MuestraPoblacion muestraPoblacion = dao.find(id, MuestraPoblacion.class);
            muestraPoblacion.setActivo(0);
            muestraPoblacion.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            MuestraPoblacion resul = dao.update(muestraPoblacion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Muestra eliminada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarMuestraPoblacion(@PathParam("id") long id, DtoMuestraPoblacion dtoMuestraPoblacion){
        JsonObject data;
        Response resultado = null;
        try {
            DaoMuestraPoblacion dao = new DaoMuestraPoblacion();
            MuestraPoblacion muestraPoblacion = dao.find(id, MuestraPoblacion.class);
            muestraPoblacion.setCantidadHijos(dtoMuestraPoblacion.getCantidadHijos());
            muestraPoblacion.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            muestraPoblacion.setGenero(dtoMuestraPoblacion.getGenero());
            muestraPoblacion.setNivelAcademico(dtoMuestraPoblacion.getNivelAcademico());
            muestraPoblacion.setNivelEconomico(dtoMuestraPoblacion.getNivelEconomico());
            muestraPoblacion.setRangoEdadInicio(dtoMuestraPoblacion.getRangoEdadInicio());
            muestraPoblacion.setRangoEdadFin(dtoMuestraPoblacion.getRangoEdadFin());
            MuestraPoblacion resul = dao.update(muestraPoblacion);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje","Muestra actualizada con exito")
                    .add("_id", resul.get_id())
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

}
