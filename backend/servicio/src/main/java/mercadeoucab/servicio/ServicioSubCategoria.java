package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.mappers.SubCategoriaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseSubCategoria;

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
        JsonObject subcategoria;
        Response resultado = null;
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria resul = dao.find( id, SubCategoria.class);
            ResponseSubCategoria responseSubCategoria = new ResponseSubCategoria();
            DtoSubCategoria dtoSubCategoria = SubCategoriaMapper.mapEntityToDto( resul);
            if ( dtoSubCategoria.getActivo() == 1 ){
                subcategoria = responseSubCategoria.generate( dtoSubCategoria);
                resultado = ResponseGeneral.Succes( subcategoria);

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
     * Metodo para listar todas las Subcategorias registradas
     * @return regresa la lista de las Subcategorias, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarSubCategoria(){
        JsonArrayBuilder subcategoriasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSubCategoria dao = new DaoSubCategoria();
            List<SubCategoria> subCategorias = dao.findAll(SubCategoria.class);
            ResponseSubCategoria responseSubCategoria = new ResponseSubCategoria();
            if ( !subCategorias.isEmpty()) {
                for (SubCategoria subCategoria : subCategorias) {
                    if (subCategoria.getActivo() == 1) {
                        DtoSubCategoria dtoSubCategoria = SubCategoriaMapper.mapEntityToDto(subCategoria);
                        JsonObject objeto = responseSubCategoria.generate(dtoSubCategoria);
                        subcategoriasList.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( subcategoriasList);
            }else{
                resultado = ResponseGeneral.NoData();
            }
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
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria subCategoria = new SubCategoria();
            subCategoria.setNombre( dtoSubCategoria.getNombre());
            Categoria categoria = new Categoria(
                    dtoSubCategoria.getCategoria().get_id()
            );
            subCategoria.setCategoria( categoria);
            subCategoria.setActivo( 1);
            subCategoria.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.insert( subCategoria);
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
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
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria subCategoria = dao.find( id, SubCategoria.class);
            subCategoria.setNombre( dtoSubCategoria.getNombre());
            subCategoria.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.update( subCategoria);
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
     * Metodo para eliminar una Subcategoria dado un identificador
     * @param id Identificador de la Subcategoria que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response  eliminarSubCategoria(@PathParam("id") Long id){
        Response resultado = null;
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria subCategoria = dao.find( id, SubCategoria.class);
            subCategoria.setActivo( 0);
            subCategoria.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.update( subCategoria);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
