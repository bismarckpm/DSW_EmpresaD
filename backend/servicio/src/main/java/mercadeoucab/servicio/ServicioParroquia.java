package mercadeoucab.servicio;

import mercadeoucab.comandos.Parroquia.*;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandoParroquia;
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
@Path( "/parroquias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioParroquia extends AplicacionBase {

    private final FabricaComandoParroquia fabricaComandoParroquia = (FabricaComandoParroquia) FabricaComandosAbstractos.getFactory(Comandos.PARROQUIA);

    /**
     * Metodo para listar todas las Parroquias registradas
     * @return regresa la lista de las categorias, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarParroquias(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try{
            validateToken(token);
            ComandoListarParroquias comandoListarParroquias = (ComandoListarParroquias) fabricaComandoParroquia.comandoListar();
            comandoListarParroquias.execute();
            resultado = comandoListarParroquias.getResult();
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
    public Response registrarParroquia(@HeaderParam("Authorization") String token, DtoParroquia dtoParroquia){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( dtoParroquia);
            ComandoRegistrarParroquia comandoRegistrarParroquia = (ComandoRegistrarParroquia) fabricaComandoParroquia.comandoCrear();
            comandoRegistrarParroquia.setDtoParroquia( dtoParroquia);
            comandoRegistrarParroquia.execute();
            resultado = comandoRegistrarParroquia.getResult();
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
    public Response consultarParroquia(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoConsultarParroquia comandoConsultarParroquia = (ComandoConsultarParroquia) fabricaComandoParroquia.comandoConsultar();
            comandoConsultarParroquia.setId( id);
            comandoConsultarParroquia.execute();
            resultado = comandoConsultarParroquia.getResult();
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
    public Response eliminarParroquia(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoEliminarParroquia comandoEliminarParroquia = (ComandoEliminarParroquia) fabricaComandoParroquia.comandoEliminar();
            comandoEliminarParroquia.setId( id);
            comandoEliminarParroquia.execute();
            resultado = comandoEliminarParroquia.getResult();
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
    public Response actualizarParroquia(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoParroquia dtoParroquia){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            verifyParams( dtoParroquia);
            ComandoActualizarParroquia comandoActualizarParroquia = (ComandoActualizarParroquia) fabricaComandoParroquia.comandoModificar();
            comandoActualizarParroquia.setDtoParroquia( dtoParroquia);
            comandoActualizarParroquia.setId( id);
            comandoActualizarParroquia.execute();
            resultado = comandoActualizarParroquia.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}

