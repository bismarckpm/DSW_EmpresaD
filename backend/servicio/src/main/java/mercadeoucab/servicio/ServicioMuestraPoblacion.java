package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
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

    /**
     * Metodo para crear una Muestra Poblacion
     * @param dtoMuestraPoblacion Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public  Response registrarMuestraPoblacion(DtoMuestraPoblacion dtoMuestraPoblacion){
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
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public MuestraPoblacion consultarMuestraPoblacion(@PathParam("id") long id){
        DaoMuestraPoblacion dao = new DaoMuestraPoblacion();
        return dao.find(id, MuestraPoblacion.class);
    }

    /**
     * Metodo para eliminar una Muestra Poblacion dado un identificador
     * @param id Identificador de la Muestra Poblacion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/eliminar/{id}")
    public Response eliminarMuestraPoblacion(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoMuestraPoblacion dao = new DaoMuestraPoblacion();
            MuestraPoblacion muestraPoblacion = dao.find(id, MuestraPoblacion.class);
            muestraPoblacion.setActivo(0);
            muestraPoblacion.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            MuestraPoblacion resul = dao.update(muestraPoblacion);
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Muestra Poblacion dado un identificador
     * @param id Identificador de la Muestra Poblacion que se desea actualizar
     * @param dtoMuestraPoblacion Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarMuestraPoblacion(@PathParam("id") long id, DtoMuestraPoblacion dtoMuestraPoblacion){
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
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
