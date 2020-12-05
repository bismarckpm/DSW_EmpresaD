package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoCategoria;
import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public DtoCategoria agregarCategoria(DtoCategoria dtoCategoria){
        DtoCategoria resultado = new DtoCategoria();
        try{
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria();
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setActivo(1);
            categoria.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.insert(categoria);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/{id}")
    public DtoCategoria consultarCategoria(@PathParam("id") long id){
        DtoCategoria resultado = new DtoCategoria();
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id, Categoria.class);
            //construyendo el objeto a retornar
            resultado.set_id(categoria.get_id());
            resultado.setNombre(categoria.getNombre());
            resultado.setActivo(categoria.getActivo());
            resultado.setCreado_el(categoria.getCreado_el());
            resultado.setModificado_el(categoria.getModificado_el());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public DtoCategoria actualizarCategoria(DtoCategoria dtoCategoria){
        DtoCategoria resultado = new DtoCategoria();
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(dtoCategoria.get_id(), Categoria.class);
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.update(categoria);
            //construyendo el objeto a retornar
            resultado.set_id(resul.get_id());
            resultado.setNombre(resul.getNombre());
            resultado.setActivo(resul.getActivo());
            resultado.setCreado_el(resul.getCreado_el());
            resultado.setModificado_el(resul.getModificado_el());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/eliminar/{id}")
    public DtoCategoria eliminarCategoria(@PathParam("id") long id){
        DtoCategoria resultado = new DtoCategoria();
        try {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(id, Categoria.class);
            categoria.setActivo(0);
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.update(categoria);
            //construyendo el objeto a retornar
            resultado.set_id(resul.get_id());
            resultado.setNombre(resul.getNombre());
            resultado.setActivo(resul.getActivo());
            resultado.setCreado_el(resul.getCreado_el());
            resultado.setModificado_el(resul.getModificado_el());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}
