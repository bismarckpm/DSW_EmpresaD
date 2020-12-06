package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSubCategoriaSolicitud;
import mercadeoucab.dtos.DtoSubCategoriaSolicitud;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.entidades.SubCategoriaSolicitud;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/subCategoriaSolicitud" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSubCategoriaSolicitud extends AplicacionBase{

    @GET
    @Path("/{id}")
    public DtoSubCategoriaSolicitud obtenerSubCategoriaSolicitud (@PathParam("id") Long id){
        DtoSubCategoriaSolicitud resultado = new DtoSubCategoriaSolicitud();
        try{
            DaoSubCategoriaSolicitud dao = new DaoSubCategoriaSolicitud();
            SubCategoriaSolicitud resul = dao.find( id, SubCategoriaSolicitud.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<SubCategoriaSolicitud> listarSubCategoriaSolicitud (){
        DaoSubCategoriaSolicitud dao = new DaoSubCategoriaSolicitud();
        return dao.findAll( SubCategoriaSolicitud.class);
    }

    @POST
    @Path("/")
    public DtoSubCategoriaSolicitud registrarSubCategoriaSolicitud (DtoSubCategoriaSolicitud dtoSubCategoriaSolicitud){
        DtoSubCategoriaSolicitud resultado = new DtoSubCategoriaSolicitud();
        try{
            DaoSubCategoriaSolicitud dao = new DaoSubCategoriaSolicitud();
            SubCategoriaSolicitud subCategoriaSolicitud = new SubCategoriaSolicitud();
            Solicitud solicitud = new Solicitud(
                    dtoSubCategoriaSolicitud.getSolicitud().get_id()
            );
            subCategoriaSolicitud.setSolicitud( solicitud);
            SubCategoria subCategoria = new SubCategoria(
                    dtoSubCategoriaSolicitud.getSubCategoria().get_id()
            );
            subCategoriaSolicitud.setSubCategoria( subCategoria);
            subCategoriaSolicitud.setActivo( 1);
            subCategoriaSolicitud.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoriaSolicitud resul = dao.insert( subCategoriaSolicitud);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public DtoSubCategoriaSolicitud eliminarSubCategoriaSolicitud (@PathParam("id") Long id){
        DtoSubCategoriaSolicitud resultado = new DtoSubCategoriaSolicitud();
        try{
            DaoSubCategoriaSolicitud dao = new DaoSubCategoriaSolicitud();
            SubCategoriaSolicitud subCategoriaSolicitud = dao.find( id, SubCategoriaSolicitud.class);
            subCategoriaSolicitud.setActivo( 0);
            subCategoriaSolicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoriaSolicitud resul = dao.update( subCategoriaSolicitud);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
