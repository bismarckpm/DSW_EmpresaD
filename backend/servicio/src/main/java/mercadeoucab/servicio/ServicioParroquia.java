package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.mappers.ParroquiaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseParroquia;

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
@Path( "/parroquias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioParroquia {

    /**
     * Metodo para listar todas las Parroquias registradas
     * @return regresa la lista de las categorias, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarParroquias(){
        JsonArrayBuilder parroquias = Json.createArrayBuilder();
        Response resultado = null;
        try{
            DaoParroquia dao = new DaoParroquia();
            List<Parroquia> parroquiasObtenidas = dao.findAll( Parroquia.class);
            ResponseParroquia responseParroquia = new ResponseParroquia();
            if ( !parroquiasObtenidas.isEmpty()) {
                for (Parroquia parroquia : parroquiasObtenidas) {
                    if (parroquia.getActivo() != 0) {
                        DtoParroquia dtoParroquia = ParroquiaMapper.mapEntityToDto(parroquia);
                        JsonObject objeto = responseParroquia.generate(dtoParroquia);
                        parroquias.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( parroquias);
            }else {
                resultado = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Parroquia
     * @param dtoParroquia Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarParroquia(DtoParroquia dtoParroquia){
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
     * Metodo para consultar una Parroquia dado un identificador
     * @param id Identificador de la Parroquia que se desea consultar
     * @return regresa la categoria Parroquia, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarParroquia(@PathParam("id") long id){
        JsonObject parroquia;
        Response resultado = null;
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia resul = dao.find(id ,Parroquia.class);
            if ( resul.getActivo()!= 0 ){
                ResponseParroquia responseParroquia = new ResponseParroquia();
                DtoParroquia dtoParroquia = ParroquiaMapper.mapEntityToDto( resul);
                parroquia = responseParroquia.generate( dtoParroquia);
                resultado = ResponseGeneral.Succes( parroquia);
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
     * Metodo para eliminar una Parroquia dado un identificador
     * @param id Identificador de la Parroquia que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarParroquia(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(id , Parroquia.class);
            parroquia.setActivo(0);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Parroquia resul = dao.update( parroquia );
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
     * Metodo para actualizar una Parroquia dado un identificador
     * @param id Identificador de la Parroquia que se desea actualizar
     * @param dtoParroquia Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarParroquia(@PathParam("id") long id, DtoParroquia dtoParroquia){
        Response resultado = null;
        try{
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(id , Parroquia.class);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            parroquia.setNombre(dtoParroquia.getNombre());
            parroquia.setValor_socio_economico(dtoParroquia.getValor_socio_economico());
            Parroquia resul = dao.update( parroquia );
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

