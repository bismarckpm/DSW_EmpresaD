package mercadeoucab.servicio;
import mercadeoucab.accesodatos.DaoTelefono;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Telefono;
import mercadeoucab.mappers.TelefonoMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseRespuesta;
import mercadeoucab.responses.ResponseTelefono;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/telefonos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTelefono extends AplicacionBase {

    /**
     * Metodo para consultar un Telefono dado un identificador
     * @param id Identificador del Telefono que se desea consultar
     * @return regresa el Telefono consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerTelefono(@PathParam("id") Long id){
        JsonObject telefono;
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono resul = dao.find( id, Telefono.class);
            ResponseTelefono responseTelefono = new ResponseTelefono();
            DtoTelefono dtoTelefono = TelefonoMapper.mapEntitytoDto( resul);
            if ( resul.getActivo() != 0 ){
                telefono = responseTelefono.generate( dtoTelefono);
                resultado = ResponseGeneral.Succes( telefono);
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
     * Metodo para crear un Telefono
     * @param dtoTelefono Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarTelefono( DtoTelefono dtoTelefono){
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = new Telefono();
            telefono.setTelefono( dtoTelefono.getTelefono());
            telefono.setActivo( 1);
            telefono.setCreado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            DatoEncuestado datoEncuestado = new DatoEncuestado(
                    dtoTelefono.getDatoEncuestado().get_id()
            );
            telefono.setDatoEncuestado( datoEncuestado);
            Telefono resul = dao.insert( telefono);
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Telefono dado un identificador
     * @param id Identificador del Telefono que se desea actualizar
     * @param dtoTelefono Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response modificarTelefono( @PathParam("id") Long id, DtoTelefono dtoTelefono){
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find( id, Telefono.class);
            telefono.setTelefono( dtoTelefono.getTelefono());
            telefono.setActivo( 1);
            telefono.setModificado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            Telefono resul = dao.update( telefono);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Telefono dado un identificador
     * @param id Identificador del Telefono que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarTelefono( @PathParam("id") Long id){
        Response resultado = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find( id, Telefono.class);
            telefono.setActivo( 0);
            telefono.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Telefono resul = dao.update( telefono);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
