package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/solicitudes" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSolicitud extends AplicacionBase{

    @GET
    @Path("/{id}")
    public DtoSolicitud obtenerSolicitud(@PathParam("id") Long id){
        DtoSolicitud resultado = new DtoSolicitud();
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud resul = dao.find( id, Solicitud.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<Solicitud> listarSolicitud(){
        DaoSolicitud dao = new DaoSolicitud();
        return dao.findAll( Solicitud.class);
    }


    @POST
    @Path("/")
    public DtoSolicitud registrarSolicitud(DtoSolicitud dtoSolicitud){
        DtoSolicitud resultado = new DtoSolicitud();
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = new Solicitud();
            solicitud.setEstado( dtoSolicitud.getEstado() );
            solicitud.setActivo( 1 );
            solicitud.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Usuario usuario = new Usuario(
                    dtoSolicitud.getUsuario().get_id()
            );
            solicitud.setUsuario( usuario);
            Marca marca = new Marca(
                    dtoSolicitud.getMarca().get_id()
            );
            solicitud.setMarca( marca );
            Tipo tipo = new Tipo(dtoSolicitud.getTipo().get_id());
            solicitud.addTipo(tipo);
            SubCategoria subCategoria = new SubCategoria(dtoSolicitud.getSubCategoria().get_id());
            solicitud.addSubCategoria(subCategoria);
            Solicitud resul = dao.insert( solicitud );
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public DtoSolicitud actualizarSolicitud(@PathParam("id") Long id, DtoSolicitud dtoSolicitud){
        DtoSolicitud resultado = new DtoSolicitud();
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setEstado( dtoSolicitud.getEstado());
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Solicitud resul = dao.update( solicitud);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoSolicitud eliminarSolicitud(@PathParam("id") Long id){
        DtoSolicitud resultado = new DtoSolicitud();
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setActivo( 0);
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Solicitud resul = dao.update( solicitud);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
