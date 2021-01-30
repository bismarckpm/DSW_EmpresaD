package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoOpcion;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.responses.ResponseGeneral;

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
@Path( "/opcion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOpcion extends AplicacionBase{

    @GET
    @Path("/{id}")
    public DtoOpcion obtenerOpcion(@PathParam("id") Long id){
        DtoOpcion resultado = new DtoOpcion();
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion resul = dao.find( id, Opcion.class);
            resultado.set_id( resul.get_id());
            resultado.setNombre_opcion(resul.getNombre_opcion());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<Opcion> listarOpcion(){
        DaoOpcion dao = new DaoOpcion();
        return dao.findAll( Opcion.class);
    }

    /**
     * Metodo para crear una opcion
     * @param DTOO Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarOpcion(DtoOpcion DTOO){
        Response resultado = null;
        try{
            DaoOpcion daoO = new DaoOpcion();
            Opcion opcion = new Opcion();
            opcion.setNombre_opcion(DTOO.getNombre_opcion());
            opcion.setActivo(DTOO.getActivo());
            opcion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pregunta pregunta=new Pregunta(DTOO.get_Dtopregunta().get_id());
            opcion.setFk_pregunta(pregunta);
            Opcion resul = daoO.insert( opcion);
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }


    /**
     * Metodo para actualizar una Opcion dado un identificador
     * @param id Identificador de la Opcion que se desea actualizar
     * @param DTOO Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarOpcion(@PathParam("id") Long id, DtoOpcion DTOO){
        Response resultado = null;
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion opcion = dao.find( id, Opcion.class);
            if(DTOO.getNombre_opcion()!=null){
                opcion.setNombre_opcion(DTOO.getNombre_opcion());
            }
            opcion.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Opcion resul = dao.update( opcion);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Opcion dado un identificador
     * @param id Identificador de la Opcion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarOpcion(@PathParam("id") Long id){
        Response resultado = null;
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion opcion = dao.find( id, Opcion.class);
            opcion.setActivo( 0);
            opcion.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Opcion resul = dao.update( opcion);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
