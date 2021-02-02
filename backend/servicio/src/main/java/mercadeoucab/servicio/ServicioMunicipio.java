package mercadeoucab.servicio;

import mercadeoucab.comandos.Municipio.*;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        Response resultado = null;
        try{
            ComandoListarMunicipios comandoListarMunicipios = new ComandoListarMunicipios();
            comandoListarMunicipios.execute();
            resultado = comandoListarMunicipios.getResult();
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
        Response resultado = null;
        try {
            verifyParams( id);
            ComandoObtenerMunicipio comandoObtenerMunicipio = new ComandoObtenerMunicipio();
            comandoObtenerMunicipio.setId( id);
            comandoObtenerMunicipio.execute();
            resultado = comandoObtenerMunicipio.getResult();
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
            verifyParams( dtoMunicipio);
            ComandoRegistrarMunicipio comandoRegistrarMunicipio = new ComandoRegistrarMunicipio();
            comandoRegistrarMunicipio.setDtoMunicipio( dtoMunicipio);
            comandoRegistrarMunicipio.execute();
            resultado = comandoRegistrarMunicipio.getResult();
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
            verifyParams( id);
            verifyParams( dtoMunicipio);
            ComandoActualizarMunicipio comandoActualizarMunicipio = new ComandoActualizarMunicipio();
            comandoActualizarMunicipio.setDtoMunicipio( dtoMunicipio);
            comandoActualizarMunicipio.setId( id);
            comandoActualizarMunicipio.execute();
            resultado = comandoActualizarMunicipio.getResult();
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
            verifyParams( id);
            ComandoEliminarMunicipio comandoEliminarMunicipio = new ComandoEliminarMunicipio();
            comandoEliminarMunicipio.setId( id);
            comandoEliminarMunicipio.execute();
            resultado = comandoEliminarMunicipio.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
