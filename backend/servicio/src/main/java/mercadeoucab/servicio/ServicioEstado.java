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

@Path( "/paises" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstado extends AplicacionBase {

    @GET
    @Path("/list")
    public List<Estado> listarEstador(){
        DaoEstado dao = new DaoEstado();
        return dao.findAll(Estado.class);
    }

    @POST
    @Path("/")
    public DtoEstado agregarEstado(DtoEstado dtoEstado){
        DtoEstado resultado = new DtoEstado();
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = new Estado();
            estado.setActivo(1);
            estado.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            Pais pais = new Pais(dtoEstado.getFk_pais().get_id());
            estado.setFk_pais( pais );
            Estado resul = dao.insert( estado );
            resultado.set_id(resul.get_id());
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public DtoEstado actualizarEstado(DtoEstado dtoEstado){
        DtoEstado resultado = new DtoEstado();
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(dtoEstado.getId(), Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setNombre(dtoEstado.getNombre());
            Pais pais = new Pais(dtoEstado.getFk_pais().get_id());
            estado.setFk_pais( pais );
            Estado resul = dao.update(estado);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public DtoEstado eliminarEstado(DtoEstado dtoEstado){
        DtoEstado resultado = new DtoEstado();
        try{
            DaoEstado dao = new DaoEstado();
            Estado estado = dao.find(dtoEstado.getId(), Estado.class);
            estado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            estado.setActivo(0);
            Estado resul = dao.update(estado);
            resultado.set_id(resul.get_id());

        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public DtoEstado consultarEstado(DtoEstado dtoEstado){
        DtoEstado resultado = new DtoEstado();
        try{
            DaoEstado dao = new DaoEstado();
            Estado resul = dao.find(dtoEstado.getId(), Estado.class);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }


}
