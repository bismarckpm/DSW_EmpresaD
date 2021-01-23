package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoCategoria;
import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.mappers.CategoriaMapper;
import mercadeoucab.responses.ResponseCategoria;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

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
    public Response listarCategorias(){
        JsonArrayBuilder categoriasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            List<Categoria> categorias = dao.findAll(Categoria.class);
            if ( !categorias.isEmpty()) {
                for (Categoria categoria : categorias) {
                    if (categoria.getActivo() == 1) {
                        ResponseCategoria responseCategoria = new ResponseCategoria();
                        DtoCategoria dtoCategoria = CategoriaMapper.mapEntitytoDto(categoria);
                        JsonObject objeto = responseCategoria.generate(dtoCategoria);
                        categoriasList.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes(categoriasList);
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
     * Metodo para crear una Categoria
     * @param dtoCategoria Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarCategoria(DtoCategoria dtoCategoria){
        Response resultado = null;
        try{
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria();
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setActivo(1);
            categoria.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.insert(categoria);
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
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
    public Response consultarCategoria(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria resul = dao.find(id, Categoria.class);
            ResponseCategoria responseCategoria = new ResponseCategoria();
            DtoCategoria dtoCategoria = CategoriaMapper.mapEntitytoDto( resul);
            if (Objects.nonNull( dtoCategoria)){

                JsonObject categoria = responseCategoria.generate( dtoCategoria);
                resultado = ResponseGeneral.Succes( categoria);
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
     * Metodo para actualizar una Categoria dado un identificador
     * @param id Identificador de la Categoria que se desea actualizar
     * @param dtoCategoria Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarCategoria(@PathParam("id") long id, DtoCategoria dtoCategoria){
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id , Categoria.class);
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.update(categoria);
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
     * Metodo para eliminar una Categoria dado un identificador
     * @param id Identificador de la Categoria que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarCategoria(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id, Categoria.class);
            categoria.setActivo(0);
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.update(categoria);
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
