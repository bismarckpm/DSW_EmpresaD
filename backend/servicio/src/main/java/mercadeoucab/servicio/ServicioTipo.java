package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.mappers.SubCategoriaMapper;
import mercadeoucab.mappers.TipoMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseTipo;

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
 * @author Oscar Marquez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/tipos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTipo extends AplicacionBase {

    /**
     * Metodo para listar todos los Tipos registrados
     * @return regresa la lista de los Tipos, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarTipos(){
        JsonArrayBuilder tiposList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            List<Tipo> tipos = dao.findAll(Tipo.class);
            ResponseTipo responseTipo = new ResponseTipo();
            if ( tipos.size() > 0) {
                for (Tipo tipo : tipos) {
                    if (tipo.getActivo() == 1) {
                        DtoTipo dtoTipo = TipoMapper.mapEntityToDto(tipo);
                        JsonObject objeto = responseTipo.generate(dtoTipo);
                        tiposList.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( tiposList);
            }else {
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar un Tipo dado un identificador
     * @param id Identificador del Tipo que se desea consultar
     * @return regresa el Tipo consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerTipo( @PathParam("id") Long id){
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            Tipo resul = dao.find( id , Tipo.class);
            ResponseTipo responseTipo = new ResponseTipo();
            DtoTipo dtoTipo = TipoMapper.mapEntityToDto( resul);
            if( resul.getActivo() == 1){
                JsonObject tipo = responseTipo.generate( dtoTipo);
                resultado = ResponseGeneral.Succes( tipo);
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
     * Metodo para crear un Tipo
     * @param dtoTipo Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarTipo( DtoTipo dtoTipo){
        Response resultado = null;
        try {
            //TODO ESTO PASA AL COMANDO
            DaoTipo dao = new DaoTipo();
            Tipo tipo = new Tipo();
            tipo.setSubCategoria(SubCategoriaMapper.mapDtoToEntity(dtoTipo.getSubCategoria()));
            tipo.setNombre( dtoTipo.getNombre());
            tipo.setActivo( 1);
            tipo.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.insert( tipo);
            //TODO ESTO PASA AL COMANDO
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Tipo dado un identificador
     * @param id Identificador del Tipo que se desea actualizar
     * @param dtoTipo Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarTipo( @PathParam("id") long id, DtoTipo dtoTipo){
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = dao.find( id, Tipo.class);
            tipo.setNombre( dtoTipo.getNombre());
            tipo.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.update( tipo );
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
     * Metodo para eliminar un Tipo dado un identificador
     * @param id Identificador del Tipo que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarTipo( @PathParam("id") Long id){
        Response resultado = null;
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = dao.find( id, Tipo.class);
            tipo.setActivo( 0 );
            tipo.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.update( tipo);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
