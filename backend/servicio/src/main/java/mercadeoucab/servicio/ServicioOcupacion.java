package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.mappers.OcupacionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseOcupacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Antonio Nohra
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/ocupaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOcupacion extends AplicacionBase{

    /**
     * Metodo para consultar una Ocupacion dado un identificador
     * @param id Identificador de la Ocupacion que se desea consultar
     * @return regresa la Ocupacion consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerOcupacion(@PathParam("id") Long id){
        JsonObject ocupacion;
        Response resultado = null;
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion resul = dao.find( id, Ocupacion.class);
            ResponseOcupacion responseOcupacion = new ResponseOcupacion();
            DtoOcupacion dtoOcupacion = OcupacionMapper.mapEntitytoDto( resul);
            ocupacion = responseOcupacion.generate( dtoOcupacion);
            if ( Objects.nonNull( dtoOcupacion)){
                resultado = ResponseGeneral.Succes( ocupacion);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las ocupaciones registradas
     * @return regresa la lista de las ocupaciones, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarOcupacion(){
        JsonArrayBuilder ocupacionesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoOcupacion dao = new DaoOcupacion();
            List<Ocupacion> ocupaciones = dao.findAll(Ocupacion.class);
            if ( ocupaciones.isEmpty()) {
                for (Ocupacion ocupacion : ocupaciones) {
                    if (ocupacion.getActivo() == 1) {
                        ResponseOcupacion responseOcupacion = new ResponseOcupacion();
                        DtoOcupacion dtoOcupacion = OcupacionMapper.mapEntitytoDto(ocupacion);
                        JsonObject objeto = responseOcupacion.generate(dtoOcupacion);
                        ocupacionesList.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( ocupacionesList);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Ocupacion
     * @param DTOO Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarOcupacion(DtoOcupacion DTOO){
        JsonObject data;
        Response resultado = null;
        try{
            DaoOcupacion daoO = new DaoOcupacion();
            Ocupacion O= new Ocupacion();
            O.setNombre(DTOO.getNombre());
            O.setActivo(1);
            O.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Ocupacion resul = daoO.insert( O);
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());

        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Ocupacion dado un identificador
     * @param id Identificador de la Ocupacion que se desea actualizar
     * @param DTOO Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarOcupacion(@PathParam("id") long id,DtoOcupacion DTOO){
        Response resultado = null;
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion O = dao.find( id, Ocupacion.class);
            O.setNombre(DTOO.getNombre());
            O.setModificado_el(new Date(Calendar
                                    .getInstance()
                                    .getTime()
                                    .getTime()));
            Ocupacion resul = dao.update( O);
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Ocupacion dado un identificador
     * @param id Identificador de la Ocupacion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarOcupacion(@PathParam("id") long id){
        Response resultado = null;
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion O = dao.find( id, Ocupacion.class);
            O.setActivo( 0);
            O.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Ocupacion resul = dao.update( O);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
