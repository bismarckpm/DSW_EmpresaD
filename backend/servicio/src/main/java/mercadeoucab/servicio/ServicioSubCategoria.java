package mercadeoucab.servicio;

import mercadeoucab.comandos.SubCategoria.*;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosSubCategoria;
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

    private final FabricaComandosSubCategoria fabricaComandosSubCategoria = (FabricaComandosSubCategoria) FabricaComandosAbstractos.getFactory(Comandos.SUBCATEGORIA);

    /**
     * Metodo para consultar una Subcategoria dado un identificador
     * @param id Identificador de la Subcategoria que se desea consultar
     * @return regresa la Subcategoria consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerSubCategoria(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoConsultarSubcategoria comandoConsultarSubcategoria = (ComandoConsultarSubcategoria) fabricaComandosSubCategoria.comandoConsultar();
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
    public Response listarSubCategoria(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarSubcategorias comandoListarSubcategorias = (ComandoListarSubcategorias) fabricaComandosSubCategoria.comandoListar();
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
    public Response  registrarSubCategoria(@HeaderParam("Authorization") String token, DtoSubCategoria dtoSubCategoria){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(dtoSubCategoria);
            ComandoRegistrarSubCategoria comandoRegistrarSubCategoria = (ComandoRegistrarSubCategoria) fabricaComandosSubCategoria.comandoCrear();
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
    public Response actualizarSubCategoria(@HeaderParam("Authorization") String token, @PathParam("id") Long id,DtoSubCategoria dtoSubCategoria){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoSubCategoria);
            ComandoActualizarSubCategoria comandoActualizarSubCategoria = (ComandoActualizarSubCategoria) fabricaComandosSubCategoria.comandoModificar();
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
    public Response  eliminarSubCategoria(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoEliminarSubCategoria comandoEliminarSubCategoria = (ComandoEliminarSubCategoria) fabricaComandosSubCategoria.comandoEliminar();
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
