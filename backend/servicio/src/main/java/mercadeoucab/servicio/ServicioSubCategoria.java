package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/subcategorias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSubCategoria extends AplicacionBase{

    @GET
    @Path("/{id}")
    public DtoSubCategoria  obtenerSubCategoria(@PathParam("id") Long id){
        DtoSubCategoria resultado = new DtoSubCategoria();
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria resul = dao.find( id, SubCategoria.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<SubCategoria> listarSubCategoria(){
            DaoSubCategoria dao = new DaoSubCategoria();
           return dao.findAll( SubCategoria.class);
    }

    @POST
    @Path("/")
    public DtoSubCategoria  registrarSubCategoria(DtoSubCategoria dtoSubCategoria){
        DtoSubCategoria resultado = new DtoSubCategoria();
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
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/")
    public DtoSubCategoria  actualizarSubCategoria(DtoSubCategoria dtoSubCategoria){
        DtoSubCategoria resultado = new DtoSubCategoria();
        try{
            DaoSubCategoria dao = new DaoSubCategoria();
            SubCategoria subCategoria = dao.find( dtoSubCategoria.get_id(), SubCategoria.class);
            subCategoria.setNombre( dtoSubCategoria.getNombre());
            subCategoria.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.update( subCategoria);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public DtoSubCategoria  eliminarSubCategoria(@PathParam("id") Long id){
        DtoSubCategoria resultado = new DtoSubCategoria();
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
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

}
