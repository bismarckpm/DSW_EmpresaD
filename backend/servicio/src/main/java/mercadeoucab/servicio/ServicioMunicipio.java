package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.mappers.MunicipioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseMunicipio;

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
@Path( "/municipios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMunicipio extends AplicacionBase{

    /**
     * Metodo para listar todos los Municipios registrados
     * @return regresa la lista de los Municipios registrados, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarMunicipios(){
        JsonArrayBuilder municipios = Json.createArrayBuilder();
        Response resultado = null;
        try{
            DaoMunicipio dao = new DaoMunicipio();
            List<Municipio> municipiosObtenidos = dao.findAll( Municipio.class);
            ResponseMunicipio responseMunicipio = new ResponseMunicipio();
            if ( !municipiosObtenidos.isEmpty()) {
                for (Municipio municipio : municipiosObtenidos) {
                    if (municipio.getActivo() != 0) {
                        DtoMunicipio dtoMunicipio = MunicipioMapper.mapEntitytoDto(municipio);
                        JsonObject objeto = responseMunicipio.generate(dtoMunicipio);
                        municipios.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( municipios);
            }else{
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
     * Metodo para consultar un Municipio dado un identificador
     * @param id Identificador del Municipio que se desea consultar
     * @return regresa el Municipio consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerMunicipio(@PathParam("id") long id){
        JsonObject municipio;
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio resul = dao.find(id , Municipio.class);
            ResponseMunicipio responseMunicipio = new ResponseMunicipio();
            if ( resul.getActivo()!=0 ){
                DtoMunicipio dtoMunicipio = MunicipioMapper.mapEntitytoDto( resul);
                municipio = responseMunicipio.generate( dtoMunicipio);
                resultado = ResponseGeneral.Succes( municipio);
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
     * Metodo para crear un Municipio
     * @param dtoMunicipio Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarMunicipio(DtoMunicipio dtoMunicipio){
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = new Municipio();
            municipio.setActivo(1);
            municipio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            municipio.setNombre(dtoMunicipio.getNombre());
            Estado estado = new Estado(dtoMunicipio.getFk_estado().get_id());
            municipio.setFk_estado( estado );
            Municipio resul = dao.insert(municipio);
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
     * Metodo para actualizar un Municipio dado un identificador
     * @param id Identificador del Municipio que se desea actualizar
     * @param dtoMunicipio Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarMunicipio(@PathParam("id") long id, DtoMunicipio dtoMunicipio){
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = dao.find(id, Municipio.class);
            municipio.setNombre(dtoMunicipio.getNombre());
            municipio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Municipio resul = dao.update(municipio);
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
     * Metodo para eliminar un Municipio dado un identificador
     * @param id Identificador del Municipio que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarMunicipio(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = dao.find(id, Municipio.class);
            municipio.setActivo(0);
            municipio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Municipio resul = dao.update(municipio);
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
