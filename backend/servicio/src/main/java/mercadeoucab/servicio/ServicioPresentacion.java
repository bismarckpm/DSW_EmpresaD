package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.mappers.PresentacionMapper;
import mercadeoucab.mappers.TipoMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePresentacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Antonio Nohra
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/presentaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPresentacion extends AplicacionBase{

    /**
     * Metodo para consultar una Presentacion dado un identificador
     * @param id Identificador de la Presentacion que se desea consultar
     * @return regresa la Presentacion consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerPresentacion(@PathParam("id") Long id){
        JsonObject presentacion;
        Response resultado = null;
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion resul = dao.find( id, Presentacion.class);
            ResponsePresentacion responsePresentacion = new ResponsePresentacion();
            DtoPresentacion dtoPresentacion = PresentacionMapper.mapEntityToDto( resul);
            presentacion = responsePresentacion.generate( dtoPresentacion);
            if ( resul.getActivo() == 1){
                resultado = ResponseGeneral.Succes( presentacion);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las Presentaciones registradas
     * @return regresa la lista de las Presentaciones, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarPresentacion(){
        JsonArrayBuilder presentacionesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoPresentacion dao = new DaoPresentacion();
            List<Presentacion> presentaciones = dao.findAll(Presentacion.class);
            ResponsePresentacion responsePresentacion = new ResponsePresentacion();
            if ( !presentaciones.isEmpty()) {
                for (Presentacion presentacion : presentaciones) {
                    if (presentacion.getActivo() == 1) {
                        DtoPresentacion dtoPresentacion = PresentacionMapper.mapEntityToDto(presentacion);
                        JsonObject objeto = responsePresentacion.generate(dtoPresentacion);
                        presentacionesList.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( presentacionesList);
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
     * Metodo para crear una Presentacion
     * @param DTOP Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarPresentacion(DtoPresentacion DTOP){
        Response resultado = null;
        try{
            DaoPresentacion daoP = new DaoPresentacion();
            Presentacion presentacion = new Presentacion();
            presentacion.setFk_tipo(TipoMapper.mapDtoToEntity(DTOP.getFk_tipo()));
            presentacion.setCantidad(DTOP.getCantidad());
            presentacion.setTipo(DTOP.getTipo());
            presentacion.setActivo(1);
            presentacion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Presentacion resul = daoP.insert( presentacion);
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
     * Metodo para actualizar una Presentacion dado un identificador
     * @param id Identificador de la Presentacion que se desea actualizar
     * @param DTOP Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarPresentacion(@PathParam("id") long id,DtoPresentacion DTOP){
        Response resultado = null;
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find( id, Presentacion.class);
            presentacion.setCantidad(DTOP.getCantidad());
            presentacion.setTipo(DTOP.getTipo());
            presentacion.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Presentacion resul = dao.update( presentacion);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Presentacion dado un identificador
     * @param id Identificador de la Presentacion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarPresentacion(@PathParam("id") long id){
        Response resultado = null;
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find( id, Presentacion.class);
            presentacion.setActivo( 0);
            presentacion.setModificado_el(
                            new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Presentacion resul = dao.update( presentacion);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
