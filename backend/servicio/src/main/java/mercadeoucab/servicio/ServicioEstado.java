package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Pais;
import org.junit.Assert;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/estados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstado extends AplicacionBase {

    @GET
    @Path("/")
    public List<Estado> listarEstador(){
        DaoEstado dao = new DaoEstado();
        return dao.findAll(Estado.class);
    }

    @POST
    @Path("/")
    public Estado agregarEstado(DtoEstado dtoEstado){
        Estado resultado = new Estado();
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = new Estado();
            estado.setActivo(1);
            estado.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            Pais pais = new Pais(dtoEstado.getFk_pais().get_id());
            estado.setFk_pais( pais );
            resultado = dao.insert( estado );
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Estado actualizarEstado(@PathParam("id") long id, DtoEstado dtoEstado){
        Estado resultado = new Estado();
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(id, Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            resultado = dao.update(estado);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public Estado eliminarEstado(@PathParam("id") long id){
        Estado resultado = new Estado();
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(id, Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setActivo(0);
            resultado = dao.update(estado);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Estado consultarEstado(@PathParam("id") long id){
        Estado resultado = new Estado();
        try{
            DaoEstado dao = new DaoEstado();
            resultado = dao.find(id, Estado.class);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }


}
