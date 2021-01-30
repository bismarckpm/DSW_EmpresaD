package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Pais;
import mercadeoucab.mappers.EstadoMapper;
import mercadeoucab.responses.ResponseEstado;
import mercadeoucab.responses.ResponseGeneral;

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
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/estados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstado extends AplicacionBase {

    /**
     * Metodo para listar todos los estados registrados
     * @return regresa la lista de los estados, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarEstador(){
        JsonArrayBuilder estados = Json.createArrayBuilder();
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            List<Estado> estadosObtenidos = dao.findAll( Estado.class);
            ResponseEstado responseEstado = new ResponseEstado();
            for (Estado estado: estadosObtenidos){
                if( estado.getActivo() != 0 ){
                    DtoEstado dtoEstado = EstadoMapper.mapentitytoDto( estado);
                    JsonObject objeto = responseEstado.generate( dtoEstado);
                    estados.add(objeto);
                }
            }
            resultado = ResponseGeneral.Succes( estados);
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear un Estado
     * @param dtoEstado Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarEstado(DtoEstado dtoEstado){
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = new Estado();
            estado.setActivo(1);
            estado.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            Pais pais = new Pais(dtoEstado.getFk_pais().get_id());
            estado.setFk_pais( pais );
            Estado resul = dao.insert( estado );
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Estado
     * @param dtoEstado Objeto que se desea actualizar
     * @param id Identificador del Estado a actualizar
     * @return regresa mensaje de exito o mensaje de error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarEstado(@PathParam("id") long id, DtoEstado dtoEstado){
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(id, Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            Estado resul = dao.update(estado);
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
     * Metodo para eliminar un Estado dado un identificador
     * @param id Identificador del Estado que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarEstado(@PathParam("id") long id){
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(id, Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setActivo(0);
            Estado resul = dao.update(estado);
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
     * Metodo para consultar un Estado dado un identificador
     * @param id Identificador del Estado que se desea consultar
     * @return regresa el Estado consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarEstado(@PathParam("id") long id){
        Response resultado = null;
        try{
            DaoEstado dao = new DaoEstado();
            Estado resul = dao.find(id, Estado.class);
            ResponseEstado responseEstado = new ResponseEstado();
            if (resul.getActivo()!= 0) {
                DtoEstado dtoEstado = EstadoMapper.mapentitytoDto( resul);
                JsonObject estado = responseEstado.generate( dtoEstado);
                resultado = ResponseGeneral.Succes( estado);
            }else{
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


}
