package mercadeoucab.servicio;

import mercadeoucab.comandos.Categoria.*;
import mercadeoucab.dtos.DtoCategoria;
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
@Path( "/categorias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCategoria extends AplicacionBase{

    /**
     * Metodo para listar todas las Categorias registradas
     * @return regresa la lista de las Categorias, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarCategorias(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarCategorias comandoListarCategorias = new ComandoListarCategorias();
            comandoListarCategorias.execute();
            resultado = comandoListarCategorias.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Categoria
     * @param dtoCategoria Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarCategoria(@HeaderParam("Authorization") String token,DtoCategoria dtoCategoria){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( dtoCategoria );
            ComandoAgregarCategoria comandoAgregarCategoria = new ComandoAgregarCategoria();
            comandoAgregarCategoria.setDtoCategoria(dtoCategoria);
            comandoAgregarCategoria.execute();
            resultado = comandoAgregarCategoria.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para consultar una Categoria dado un identificador
     * @param id Identificador de la Categoria que se desea consultar
     * @return regresa la Categoria consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarCategoria(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id );
            ComandoConsultarCategoria comandoConsultarCategoria = new ComandoConsultarCategoria();
            comandoConsultarCategoria.setId( id );
            comandoConsultarCategoria.execute();
            resultado = comandoConsultarCategoria.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Categoria dado un identificador
     * @param id Identificador de la Categoria que se desea actualizar
     * @param dtoCategoria Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarCategoria(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoCategoria dtoCategoria){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoCategoria);
            ComandoActualizarCategoria comandoActualizarCategoria = new ComandoActualizarCategoria();
            comandoActualizarCategoria.setDtoCategoria(dtoCategoria);
            comandoActualizarCategoria.setId(id);
            comandoActualizarCategoria.execute();
            resultado = comandoActualizarCategoria.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Categoria dado un identificador
     * @param id Identificador de la Categoria que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarCategoria(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoEliminarCategoria comandoEliminarCategoria = new ComandoEliminarCategoria();
            comandoEliminarCategoria.setId(id);
            comandoEliminarCategoria.execute();
            resultado = comandoEliminarCategoria.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
