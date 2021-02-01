package mercadeoucab.servicio;

import mercadeoucab.comandos.subcategoria.*;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/subcategorias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSubCategoria extends AplicacionBase{

    /**
     * Metodo para consultar una Subcategoria dado un identificador
     * @param id Identificador de la Subcategoria que se desea consultar
     * @return regresa la Subcategoria consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerSubCategoria(@PathParam("id") Long id){
        Response resultado = null;
        try{
            verifyParams(id);
            ComandoConsultarSubcategoria comandoConsultarSubcategoria = new ComandoConsultarSubcategoria();
            comandoConsultarSubcategoria.setId(id);
            comandoConsultarSubcategoria.execute();
            resultado = comandoConsultarSubcategoria.getResult();

        }
        catch (Exception e)
        {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las Subcategorias registradas
     * @return regresa la lista de las Subcategorias, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarSubCategoria(){
        Response resultado = null;
        try {
            ComandoListarSubcategorias comandoListarSubcategorias = new ComandoListarSubcategorias();
            comandoListarSubcategorias.execute();
            resultado = comandoListarSubcategorias.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para crear una Subcategoria
     * @param dtoSubCategoria Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response  registrarSubCategoria(DtoSubCategoria dtoSubCategoria){
        Response resultado = null;
        try{
            verifyParams(dtoSubCategoria);
            ComandoRegistrarSubCategoria comandoRegistrarSubCategoria = new ComandoRegistrarSubCategoria();
            comandoRegistrarSubCategoria.setDtoSubCategoria(dtoSubCategoria);
            comandoRegistrarSubCategoria.execute();
            resultado = comandoRegistrarSubCategoria.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Subcategoria dado un identificador
     * @param id Identificador de la Subcategoria que se desea actualizar
     * @param dtoSubCategoria Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarSubCategoria(@PathParam("id") Long id,DtoSubCategoria dtoSubCategoria){
        Response resultado = null;
        try{
            verifyParams(id);
            verifyParams(dtoSubCategoria);
            ComandoActualizarSubCategoria comandoActualizarSubCategoria = new ComandoActualizarSubCategoria();
            comandoActualizarSubCategoria.setId(id);
            comandoActualizarSubCategoria.setDtoSubCategoria(dtoSubCategoria);
            comandoActualizarSubCategoria.execute();
            resultado = comandoActualizarSubCategoria.getResult();
        }
        catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Subcategoria dado un identificador
     * @param id Identificador de la Subcategoria que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response  eliminarSubCategoria(@PathParam("id") Long id){
        Response resultado = null;
        try{
            verifyParams(id);
            ComandoEliminarSubCategoria comandoEliminarSubCategoria = new ComandoEliminarSubCategoria();
            comandoEliminarSubCategoria.setId(id);
            comandoEliminarSubCategoria.execute();
            resultado = comandoEliminarSubCategoria.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
