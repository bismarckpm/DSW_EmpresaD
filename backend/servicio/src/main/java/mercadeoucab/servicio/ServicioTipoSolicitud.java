package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoTipoSolicitud;
import mercadeoucab.dtos.DtoTipoSolicitud;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.entidades.TipoSolicitud;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/tipoSolicitud" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTipoSolicitud extends AplicacionBase{

    @GET
    @Path("/{id}")
    public DtoTipoSolicitud obtenerTipoSolicitud(@PathParam("id") Long id){
        DtoTipoSolicitud resultado = new DtoTipoSolicitud();
        try{
            DaoTipoSolicitud dao = new DaoTipoSolicitud();
            TipoSolicitud resul = dao.find( id, TipoSolicitud.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<TipoSolicitud> listarTipoSolicitud(){
        DaoTipoSolicitud dao = new DaoTipoSolicitud();
        return dao.findAll( TipoSolicitud.class);
    }

    @POST
    @Path("/")
    public DtoTipoSolicitud registrarTipoSolicitud(DtoTipoSolicitud dtoTipoSolicitud){
        DtoTipoSolicitud resultado = new DtoTipoSolicitud();
        try{
            DaoTipoSolicitud dao = new DaoTipoSolicitud();
            TipoSolicitud tipoSolicitud = new TipoSolicitud();
            Solicitud solicitud = new Solicitud(
                    dtoTipoSolicitud.getSolicitud().get_id()
            );
            tipoSolicitud.setSolicitud( solicitud);
            Tipo tipo = new Tipo(
                    dtoTipoSolicitud.getTipo().get_id()
            );
            tipoSolicitud.setTipo( tipo);
            tipoSolicitud.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            tipoSolicitud.setActivo( 1);
            TipoSolicitud resul = dao.insert( tipoSolicitud);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public DtoTipoSolicitud eliminarTipoSolicitud(@PathParam("id") Long id){
        DtoTipoSolicitud resultado = new DtoTipoSolicitud();
        try{
            DaoTipoSolicitud dao = new DaoTipoSolicitud();
            TipoSolicitud tipoSolicitud = dao.find( id, TipoSolicitud.class);
            tipoSolicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            tipoSolicitud.setActivo( 0);
            TipoSolicitud resul = dao.update( tipoSolicitud);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
