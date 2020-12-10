package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPresentacionSolicitud;
import mercadeoucab.dtos.DtoPresentacionSolicitud;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.entidades.PresentacionSolicitud;
import mercadeoucab.entidades.Solicitud;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/presentacionSolicitud" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPresentacionSolicitud extends AplicacionBase{
    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoPresentacionSolicitud obtenerPresentacionSolicitu(@PathParam("id") Long id){
        DtoPresentacionSolicitud resultado = new DtoPresentacionSolicitud();
        try{
            DaoPresentacionSolicitud dao = new DaoPresentacionSolicitud();
            PresentacionSolicitud resul = dao.find( id, PresentacionSolicitud.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<PresentacionSolicitud> listarPresentacionSolicitud(){
        DaoPresentacionSolicitud dao = new DaoPresentacionSolicitud();
        return dao.findAll( PresentacionSolicitud.class);
    }

    @POST
    @Path("/")
    public DtoPresentacionSolicitud registrarPresentacionSolicitud(DtoPresentacionSolicitud DTPS){
        DtoPresentacionSolicitud resultado = new DtoPresentacionSolicitud();
        try{
            DaoPresentacionSolicitud daoP = new DaoPresentacionSolicitud();
            PresentacionSolicitud PS = new PresentacionSolicitud();
            Presentacion P=new Presentacion(DTPS.getDto_presentacion().get_id());
            PS.setFk_presentacion(P);
            Solicitud S=new Solicitud(DTPS.getDto_solicitud().get_id());
            PS.setSolicitud(S);
            PS.setActivo(DTPS.getActivo());
            PS.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            PresentacionSolicitud resul = daoP.insert( PS);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoPresentacionSolicitud actualizarPresentacionSolicitud(DtoPresentacionSolicitud DTPS){
        DtoPresentacionSolicitud resultado = new DtoPresentacionSolicitud();
        try{
            DaoPresentacionSolicitud dao = new DaoPresentacionSolicitud();
            PresentacionSolicitud PS = dao.find( DTPS.get_id(), PresentacionSolicitud.class);
            if(DTPS.getDto_solicitud()!=null){
                PS.setSolicitud(new Solicitud(DTPS.getDto_solicitud().get_id()));
            }
            if(DTPS.getDto_presentacion()!=null){
                PS.setFk_presentacion(new Presentacion(DTPS.getDto_presentacion().get_id()));
            }
            if(DTPS.getActivo()!=0){
                PS.setActivo(DTPS.getActivo());
            }
            PS.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            PresentacionSolicitud resul = dao.update( PS);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoPresentacionSolicitud eliminarPresentacionSolicitud(long id){
        DtoPresentacionSolicitud resultado = new DtoPresentacionSolicitud();
        try{
            DaoPresentacionSolicitud dao = new DaoPresentacionSolicitud();
            PresentacionSolicitud PS = dao.find( id, PresentacionSolicitud.class);
            PS.setActivo( 0);
            PS.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            PresentacionSolicitud resul = dao.update( PS);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
