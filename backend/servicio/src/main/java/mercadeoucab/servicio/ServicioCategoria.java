package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoCategoria;
import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/categorias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCategoria extends AplicacionBase{

    @GET
    @Path("/")
    public List<Categoria> listarCategorias(){
        DaoCategoria dao = new DaoCategoria();
        return dao.findAll(Categoria.class);
    }

    @POST
    @Path("/")
    public Response agregarCategoria(DtoCategoria dtoCategoria){
        Categoria resultado = new Categoria();
        try{
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria();
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setActivo(1);
            categoria.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.insert(categoria);
            Response.ok().entity(resultado).build();
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return  Response.ok(resultado).build();
    }

    @GET
    @Path("/{id}")
    public Categoria consultarCategoria(@PathParam("id") long id){
        Categoria resultado = new Categoria();
        try {
            DaoCategoria dao = new DaoCategoria();
            resultado = dao.find(id, Categoria.class);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Categoria actualizarCategoria(@PathParam("id") long id, DtoCategoria dtoCategoria){
        Categoria resultado = new Categoria();
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id , Categoria.class);
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update(categoria);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/eliminar/{id}")
    public Categoria eliminarCategoria(@PathParam("id") long id){
        Categoria resultado = new Categoria();
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id, Categoria.class);
            categoria.setActivo(0);
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update(categoria);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}
