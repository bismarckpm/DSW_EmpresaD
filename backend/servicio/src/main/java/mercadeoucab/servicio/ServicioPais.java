package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.mappers.PaisMapper;
import mercadeoucab.responses.ResponseGeneral;
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
import java.util.Objects;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/paises" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPais extends AplicacionBase{

    /**
     * Metodo para listar todos los Paises registrados
     * @return regresa la lista de los estudios, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listar_paises(){
        JsonArrayBuilder paises = Json.createArrayBuilder();
        Response resultado = null;
        ResponsePais responsePais = new ResponsePais();
        try{
            DaoPais dao = new DaoPais();
            List<Pais> paisesObtenidos = dao.findAll( Pais.class);
            if (!paisesObtenidos.isEmpty()){
                for (Pais pais: paisesObtenidos){
                    if (pais.getActivo()!= 0){
                        DtoPais dtoPais = PaisMapper.mapEntityToDto( pais);
                        JsonObject objeto = responsePais.generate( dtoPais);
                        paises.add( objeto);
                    }
                }
                resultado = ResponseGeneral.Succes(paises);
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
     * Metodo para consultar un Pais dado un identificador
     * @param id Identificador del Pais que se desea consultar
     * @return regresa el Pais consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerPais(@PathParam("id") long id){
        Response resultado = null;
        try{
            DaoPais dao = new DaoPais();
            Pais resul = dao.find(id, Pais.class);
            if ( Objects.nonNull( resul) && resul.getActivo()==1){
                ResponsePais responsePais = new ResponsePais();
                DtoPais dtoPais = PaisMapper.mapEntityToDto( resul);
                JsonObject pais = responsePais.generate( dtoPais);
                resultado = ResponseGeneral.Succes( pais);
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

    /**
     * Metodo para crear un Pais
     * @param dtoPais Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
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
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch(Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Pais dado un identificador
     * @param id Identificador del Pais que se desea actualizar
     * @param dtoPais Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarPais(@PathParam("id") long id,DtoPais dtoPais){
        Response resultado = null;
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(id, Pais.class);
            pais.setNombre(dtoPais.getNombre());
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
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
     * Metodo para eliminar un Pais dado un identificador
     * @param id Identificador del Pais que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public  Response eliminarPais(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(id , Pais.class);
            pais.setActivo(0);
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
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
