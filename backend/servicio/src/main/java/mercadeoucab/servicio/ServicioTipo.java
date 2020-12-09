package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/tipos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTipo extends AplicacionBase {

    @GET
    @Path("/")
    public List<Tipo> listarTipos(){
        DaoTipo dao = new DaoTipo();
        return dao.findAll( Tipo.class);
    }

    @GET
    @Path("/{id}")
    public DtoTipo obtenerTipo( @PathParam("id") Long id){
        DtoTipo resultado = new DtoTipo();
        try {
            DaoTipo dao = new DaoTipo();
            Tipo resul = dao.find( id , Tipo.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @POST
    @Path("/")
    public DtoTipo registrarTipo( DtoTipo dtoTipo){
        DtoTipo resultado = new DtoTipo();
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = new Tipo();
            tipo.setNombre( dtoTipo.getNombre());
            tipo.setActivo( 1);
            tipo.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.insert( tipo);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/")
    public DtoTipo actualizarTipo( DtoTipo dtoTipo){
        DtoTipo resultado = new DtoTipo();
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = dao.find( dtoTipo.get_id(), Tipo.class);
            tipo.setNombre( dtoTipo.getNombre());
            tipo.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.update( tipo);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public DtoTipo eliminarTipo( @PathParam("id") Long id){
        DtoTipo resultado = new DtoTipo();
        try {
            DaoTipo dao = new DaoTipo();
            Tipo tipo = dao.find( id, Tipo.class);
            tipo.setActivo( 0);
            tipo.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Tipo resul = dao.update( tipo);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
